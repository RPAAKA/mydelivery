package com.java.mydelivery.domain;

// 내가 주문 할 음식점 + 음식 리스트 (주문서)

import lombok.Getter;
import javax.persistence.*;

@Entity
@Getter

public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 인덱스 증가
    private Long id;

    @ManyToOne // 다대일 (하나의 음식을 주문 목록 여러개에 집어 넣을 수 있음)
    @JoinColumn(name = "food_id", nullable = false) // 열 설정(외래키 이름, 무조건 입력)
    private Food food; // 조인하면 Food 테이블의 열 참조 가능 (restaurantID, foodID 등)

    @Column(nullable = false) // 열 설정(무조건 입력)
    private int quantity; // 주문 수량

    @Column(nullable = false)  //열 설정(무조건 입력)
    private int price; // 주문 가격


    //기본 생성자
    public OrderList() {

    }

    //선택한 음식과 수량에 따른 생성자
    public OrderList(Food food, int quantity) {
        this.food = food;
        this.quantity = quantity;
        this.price = food.getPrice() * quantity;
    }
}
