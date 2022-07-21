package com.java.mydelivery.repository;

import com.java.mydelivery.domain.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderListRepository extends JpaRepository<OrderList, Long> {
}
