package com.java.mydelivery.repository;

import com.java.mydelivery.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByName(String name); //음식점 이름 중복 검사
}
