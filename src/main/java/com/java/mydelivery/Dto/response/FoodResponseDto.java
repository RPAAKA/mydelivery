package com.java.mydelivery.Dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder

public class FoodResponseDto {
    Long id;
    private String name; //음식명
    private int price; // 가격

    //기본 생성자
    public FoodResponseDto(Long id, String name, int price) {

    }

}
