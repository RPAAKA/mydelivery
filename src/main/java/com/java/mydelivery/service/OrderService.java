package com.java.mydelivery.service;

import com.java.mydelivery.Dto.FoodOrderDto;
import com.java.mydelivery.Dto.request.FoodOrderRequestDto;
import com.java.mydelivery.Dto.request.OrderRequestDto;
import com.java.mydelivery.Dto.response.OrderResponseDto;
import com.java.mydelivery.domain.Food;
import com.java.mydelivery.domain.OrderFood;
import com.java.mydelivery.domain.OrderList;
import com.java.mydelivery.domain.Restaurant;
import com.java.mydelivery.repository.FoodRepository;
import com.java.mydelivery.repository.OrderFoodRepository;
import com.java.mydelivery.repository.OrderListRepository;
import com.java.mydelivery.repository.RestaurantRepository;
import com.java.mydelivery.validator.OrderFoodValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service

public class OrderService {
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderFoodRepository orderFoodRepository;
    private final OrderListRepository orderListRepository;

    @Transactional
    public OrderResponseDto saveOrder(OrderRequestDto orderRequestDto) {
        //음식점 존재하는지 확인
        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId())
                .orElseThrow(() -> new NullPointerException("해당 음식점이 존재하지 않습니다."));

        List<FoodOrderDto> foods = new ArrayList<>(); //반환으로 줄 DTO에 담을 주문 foods
        List<OrderList> orderFoods = new ArrayList<>(); //DB에 저장할 주문 foods

        int totalPrice = 0; // 총 가격

        for(FoodOrderRequestDto foodOrderRequestDto : orderRequestDto.getFoods()) {
            //주문한 음식이 존재하는지 확인
            Food food = foodRepository.findByIdAndRestaurant(foodOrderRequestDto.getId(), restaurant);
            if(food == null) {
                throw new IllegalArgumentException("주문한 음식이 존재하지 않습니다.");
            }

            //음식별 수량 가져오기
            int quantity = foodOrderRequestDto.getQuantity();
            OrderFoodValidator.validateOrderFood(quantity, food.getPrice());

            //음식 주문 목록 생성, DB 저장
            OrderList orderList = new OrderList(food, quantity);
            orderListRepository.save(orderList);

            int price = orderList.getPrice(); //수량 * 음식 가격

            //주문 음식 DTO 생성
            FoodOrderDto foodOrderDto = new FoodOrderDto(food.getName(), quantity, price);

            totalPrice = totalPrice + price; // 총 가격 계산

            orderFoods.add(orderList); // DB 저장할 LIST
            foods.add(foodOrderDto); // 반환 음식 목록 List

        }
        OrderFoodValidator.validateOrderTotalPrice(restaurant.getMinOrderPrice(), totalPrice);
        OrderFood orderFood = new OrderFood(restaurant, orderFoods, totalPrice);
        orderFoodRepository.save(orderFood);

        //반환 DTO
        return new OrderResponseDto(restaurant.getName(), foods, orderFood.getRestaurant().getDeliveryFee(), orderFood.getTotalPrice());
    }

    public List<OrderResponseDto> findOrder() {
        List<OrderFood> orders = orderFoodRepository.findAll();
        List<OrderResponseDto> orderList = new ArrayList<>();
        for(OrderFood order : orders) {
            List<FoodOrderDto> foodOrderDtoList = new ArrayList<>();
            for(OrderList orderFood : order.getFoods()) {
                Food food = orderFood.getFood();
                FoodOrderDto foodOrderDto = new FoodOrderDto(food.getName(), orderFood.getQuantity(),orderFood.getPrice());
                foodOrderDtoList.add(foodOrderDto);
            }
            Restaurant restaurant = order.getRestaurant();
            OrderResponseDto orderResponseDto = new OrderResponseDto(restaurant.getName(), foodOrderDtoList, restaurant.getDeliveryFee(), order.getTotalPrice());
            orderList.add(orderResponseDto);
        }
        return orderList;
    }
}
