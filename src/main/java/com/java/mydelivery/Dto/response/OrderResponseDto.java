package com.java.mydelivery.Dto.response;

import com.java.mydelivery.Dto.FoodOrderDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder

public class OrderResponseDto {
    private String restaurantName; // 주문 음식점 이름
    private List<FoodOrderDto> foods; // 주문 음식 정보
    private int deliveryFee; // 배달비
    private int totalPrice; // 최종 결제 금액

    public OrderResponseDto(String name, List<FoodOrderDto> foods, int deliveryFee, int totalPrice) {

    }
}
