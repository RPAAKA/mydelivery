package com.java.mydelivery.controller;

import com.java.mydelivery.Dto.request.OrderRequestDto;
import com.java.mydelivery.Dto.response.OrderResponseDto;
import com.java.mydelivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController

public class OrderController {
    private final OrderService orderService;

    //주문
    @PostMapping("/order/request")
    public OrderResponseDto registerOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.saveOrder(orderRequestDto);
    }

    //주문 조회
    @GetMapping("/orders")
    private List<OrderResponseDto> findOrderList() {return orderService.findOrder();}
}
