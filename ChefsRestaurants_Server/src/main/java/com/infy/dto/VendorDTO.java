package com.infy.dto;

import java.util.List;

import com.infy.model.Dish;
import com.infy.model.Restaurant;
import com.infy.utility.ChefsRestaurantException;

public interface VendorDTO {
	List<Restaurant> getRegisteredRestaurants(String userId) throws ChefsRestaurantException;
	Restaurant modifyRestaurantDetails(Restaurant restaurant, String userId) throws ChefsRestaurantException;
	List<Dish> modifyDishDetails(Dish dish, Integer restaurantId) throws ChefsRestaurantException;
	List<Dish> deleteDish(Integer dishId, Integer restaurantId) throws ChefsRestaurantException;
	List<Dish> addDish(Dish dish, Integer restaurantId) throws ChefsRestaurantException;
	String addRestaurant(Restaurant restaurant,Integer userId) throws ChefsRestaurantException;
	Restaurant deactivateRestaurant(Integer userId, Integer restaurantId) throws ChefsRestaurantException;
	Restaurant activateRestaurant(Integer userId, Integer restaurantId) throws ChefsRestaurantException;
}
