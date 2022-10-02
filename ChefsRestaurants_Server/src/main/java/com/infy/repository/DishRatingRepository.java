package com.infy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infy.entity.DishRatingEntity;

@Repository(value = "dishRatingRepository")
public interface DishRatingRepository extends CrudRepository<DishRatingEntity, Integer> {
	
	@Query("select dr from dishRating dr where dr.dish= (select dish from com.infy.entity.DishEntity dish where dish.dishId = :dishId)")
	List<DishRatingEntity> findAllByDishId(@Param("dishId") Integer dishId);

}
