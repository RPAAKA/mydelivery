package com.java.mydelivery.Dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FoodOrderRequestDto {
    private Long id; // 음식 ID
    private int quantity; //음식 주문 수량

    //기본 생성자
    public FoodOrderRequestDto() {

    }
}
