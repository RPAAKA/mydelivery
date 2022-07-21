package com.java.mydelivery.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter //정보 수정할 때 필요
@Getter // 데이터 가져올 때 필요
@AllArgsConstructor
@Builder

public class FoodDto {
    private String name; //음식명
    private int price; //가격
}
