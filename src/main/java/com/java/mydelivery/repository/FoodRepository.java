package com.java.mydelivery.repository;

import com.java.mydelivery.domain.Food;
import com.java.mydelivery.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findByRestaurantAndName(Restaurant restaurant, String name); // 같은 음식점 중복 음식명 불가, 다른 음식점은 가능
    List<Food> findByRestaurantId(Long restaurantId);
    Food findByIdAndRestaurant(Long id, Restaurant restaurant);
}
