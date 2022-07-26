package com.java.mydelivery.validator;

import org.springframework.stereotype.Component;

@Component // Bean 등록

public class OrderFoodValidator {
    public static void validateOrderFood(int quantity, int price) {
        if(quantity < 1 || quantity > 100) {
            throw new IllegalArgumentException("수량은 1~100을 입력해주세요.");
        }
    }

    public static void validateOrderTotalPrice(int minOrderPrice, int totalPrice) {
        if(minOrderPrice > totalPrice) {
            throw new IllegalArgumentException("주문 금액이 최소 주문 금액보다 커야합니다.");
        }
    }
}
