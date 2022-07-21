package com.java.mydelivery.domain;

import com.java.mydelivery.Dto.FoodDto;
import lombok.Getter;

import javax.persistence.*;

@Getter //get 메소드 자동 생성, 데이터 가져올 때 필요함!
@Entity // DB 테이블 설정

public class Food {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 인덱스 증가
    private Long id; //인덱스 값, 순번

    @ManyToOne // 다대일 (하나의 음식점에 여러 개의 음식들이 있다)
    @JoinColumn(name = "restaurant_id", nullable = false) // 열 설정 (외래키 이름, 무조건 입력해야 한다는 뜻)
    Restaurant restaurant; // 음식점

    @Column(nullable = false)
    private String name; //음식명

    @Column(nullable = false)
    private int price; // 가격

    //기본 생성자
    public Food() {

    }

    // FoodDto 생성자
    public Food(Restaurant restaurant, FoodDto foodDto) {
        this.restaurant = restaurant;
        this.name = foodDto.getName();
        this.price = foodDto.getPrice();
    }
}
