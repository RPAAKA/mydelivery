package com.java.mydelivery.repository;

import com.java.mydelivery.domain.OrderFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodRepository extends JpaRepository<OrderFood, Long>{
}
