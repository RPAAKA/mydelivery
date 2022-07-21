package com.java.mydelivery.Dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder

public class OrderRequestDto {
    private Long restaurantId; // 음식점 ID
    private List<FoodOrderRequestDto> foods; // 음식 주문 정보

}
