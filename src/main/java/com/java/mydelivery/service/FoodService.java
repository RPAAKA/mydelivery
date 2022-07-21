package com.java.mydelivery.service;

import com.java.mydelivery.Dto.FoodDto;
import com.java.mydelivery.Dto.response.FoodResponseDto;
import com.java.mydelivery.domain.Food;
import com.java.mydelivery.domain.Restaurant;
import com.java.mydelivery.repository.FoodRepository;
import com.java.mydelivery.repository.RestaurantRepository;
import com.java.mydelivery.validator.FoodDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service // Service 명시(@Component 포함) : Bean 등록

public class FoodService {
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    // 음식명 등록
    @Transactional // 트랜잭션 처리
    public void saveFood(Long restaurantId, List<FoodDto> foodDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NullPointerException("해당 음식점이 존재하지 않습니다."));

        //같은 음식점 음식명 중복 검사
        for(int i=0; i<foodDto.size(); i++) {
            Optional<Food> checkFoodName = foodRepository.findByRestaurantAndName(restaurant, foodDto.get(i).getName());
            if(checkFoodName.isPresent()) {
                throw new IllegalArgumentException("중복된 음식명이 존재합니다.");
            }
            // 음식 정보 유효성 검사(가격)
            FoodDtoValidator.validateFoodDto(foodDto.get(i));

            //음식 등록
            Food food = new Food(restaurant, foodDto.get(i));
            foodRepository.save(food);
        }
    }

    public List<FoodResponseDto> listFoods(Long restaurantId) {
        //음식점별 음식명 조회
        List<Food> foodList = foodRepository.findByRestaurantId(restaurantId);

        // 음식명 Response List 생성 (restaurantId 제외하고 넣어야 함)
        List<FoodResponseDto> foodResponseDtoList = new ArrayList<>();
        for(int i=0; i<foodList.size(); i++) {
            foodResponseDtoList.add(new FoodResponseDto(foodList.get(i).getId(), foodList.get(i).getName(), foodList.get(i).getPrice()));
        }

        return foodResponseDtoList;
    }
}
