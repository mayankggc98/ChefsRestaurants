package com.infy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infy.entity.DishEntity;

@Repository(value = "dishRepository")
public interface DishRepository extends CrudRepository<DishEntity, Integer> {
	Optional<DishEntity> findByDishId(Integer dishId);
}
