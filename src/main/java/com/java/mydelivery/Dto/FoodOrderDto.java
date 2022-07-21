package com.java.mydelivery.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor

public class FoodOrderDto {
    String name; //음식명
    int quantity; // 수량
    int price; // 주문가격
}
