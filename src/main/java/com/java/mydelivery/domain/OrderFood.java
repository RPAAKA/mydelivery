package com.java.mydelivery.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity

//음식주문하기!
//들어갈 정보는
//1)음식점 이름
//2)음식 정보 (주문음식명/수량/가격)
//3)배달비
//4)최종결제금액

public class OrderFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne // 하나의 음식점에서 여러개의 주문을 할 수 받을 수 있다.
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @OneToMany //하나의 주문에 여러개의 음식 리스트를 넣을 수 있다.
    @Column(nullable = false)
    private List<OrderList> foods;

    @Column(nullable = false)
    private int totalPrice;

    //기본 생성자
    public OrderFood() {

    }

    public OrderFood(Restaurant restaurant, List<OrderList> foods, int totalPrice) {
        this.restaurant = restaurant;
        this.foods = foods;
        this.totalPrice = totalPrice + restaurant.getDeliveryFee();


    }




}
