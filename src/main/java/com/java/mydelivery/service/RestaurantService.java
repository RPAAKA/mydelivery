package com.java.mydelivery.service;

import com.java.mydelivery.Dto.RestaurantDto;
import com.java.mydelivery.domain.Restaurant;
import com.java.mydelivery.repository.RestaurantRepository;
import com.java.mydelivery.validator.RestaurantDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant saveRestaurant(RestaurantDto restaurantDto) {
        //음식점 이름 중복 검사
        Optional<Restaurant> checkName = restaurantRepository.findByName(restaurantDto.getName());
        if(checkName.isPresent()) {
            throw new IllegalArgumentException("중복된 음식점 이름입니다.");
        }

        // 음식점 정보 유효성 검사(최소주문 가격, 기본 배달비)
        RestaurantDtoValidator.validateRestaurantDto(restaurantDto);

        //음식점 등록
        Restaurant restaurant = new Restaurant(restaurantDto);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public List<Restaurant> listRestaurants() {
        //모든 음식점 조회
        return restaurantRepository.findAll();
    }
}
