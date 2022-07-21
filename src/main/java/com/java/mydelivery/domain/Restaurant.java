package com.java.mydelivery.domain;

import com.java.mydelivery.Dto.RestaurantDto;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity // DB 테이블 설정 -> 이 파일은 DB에 들어갈겁니다. 클래스 아니에요!!

public class Restaurant {
    @Id // primary key 설정 (순번(인덱스) 같은 느낌?)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 인덱스 번호 증가
    private Long id;

    //음식점 이름
    @Column (nullable = false) // 열 설정. 무조건 입력해야 한다는 뜻
    private String name;

    //최소 주문 가격
    @Column (nullable = false) //열 설정. 무조건 입력해야 한다는 뜻
    private int minOrderPrice;

    //배달비
    @Column (nullable = false) //열 설정. 무조건 입력해야 한다는 뜻
    private int deliveryFee;

    //기본 생성자
    public Restaurant() {

    }

    // RestaurantDTO 생성자
    public Restaurant(RestaurantDto restaurantDto) {
        this.name = restaurantDto.getName();
        this.minOrderPrice = restaurantDto.getMinOrderPrice();
        this.deliveryFee = restaurantDto.getDeliveryFee();
    }


}
