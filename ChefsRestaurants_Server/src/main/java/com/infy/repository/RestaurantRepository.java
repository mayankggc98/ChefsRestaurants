package com.infy.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infy.entity.RestaurantEntity;

@Repository(value = "restaurantRepository")
public interface RestaurantRepository extends CrudRepository<RestaurantEntity, Integer> {
	
	Optional<RestaurantEntity> findByRestaurantId(Integer restaurantId);
}
