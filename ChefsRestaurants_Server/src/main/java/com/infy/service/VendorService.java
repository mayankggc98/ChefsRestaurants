package com.infy.service;

import java.util.List;

import com.infy.entity.DishEntity;
import com.infy.entity.RestaurantEntity;
import com.infy.model.Dish;
import com.infy.model.Restaurant;
import com.infy.utility.ChefsRestaurantException;

public interface VendorService {
	List<RestaurantEntity> getRegisteredRestaurants(String userId) throws ChefsRestaurantException;
	RestaurantEntity modifyRestaurantDetails(Restaurant restaurant) throws ChefsRestaurantException;
	List<DishEntity> modyDishDetails(Dish dish, Integer restaurantId) throws ChefsRestaurantException;
	List<DishEntity> deleteDish(Integer dishId, Integer restaurantId) throws ChefsRestaurantException;
	List<DishEntity> addDish(Dish dish, Integer restaurantId) throws ChefsRestaurantException;
	String addRestaurant(Restaurant restaurant,Integer userId) throws ChefsRestaurantException;
	RestaurantEntity deactivateRestaurant(Integer restaurantId) throws ChefsRestaurantException;
	RestaurantEntity activateRestaurant(Integer restaurantId) throws ChefsRestaurantException;
	
}
