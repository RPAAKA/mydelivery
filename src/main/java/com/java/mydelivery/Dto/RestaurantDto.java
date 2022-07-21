package com.java.mydelivery.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter //정보 수정할 때 필요
@Getter // 데이터 가져올 때 필요
@Builder
@AllArgsConstructor

//Dto -> 데이터 전송 객체
//getter와 setter의 메소드만 가진다.

public class RestaurantDto {
    private String name;
    private int minOrderPrice;
    private int deliveryFee;
}

