package com.infy.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.infy.entity.DishEntity;
import com.infy.entity.RestaurantEntity;
import com.infy.entity.RestaurantTransactionEntity;
import com.infy.model.Dish;
import com.infy.model.Restaurant;
import com.infy.model.RestaurantTransaction;
import com.infy.service.VendorServiceImpl;
import com.infy.utility.ChefsRestaurantException;

@Controller(value = "vendorDTO")
public class VendorDTOImpl implements VendorDTO {
	
	@Autowired 
	private VendorServiceImpl vendorService;

	@Override
	public List<Restaurant> getRegisteredRestaurants(String userId) throws ChefsRestaurantException {
		List<RestaurantEntity> restaurantEntities = vendorService.getRegisteredRestaurants(userId);
		
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		
		for (RestaurantEntity restaurantEntity : restaurantEntities) {
			Restaurant restaurant = convertRestaurantEntityToModel(restaurantEntity);
			restaurants.add(restaurant);
		}
		return restaurants;
	}

	@Override
	public Restaurant modifyRestaurantDetails(Restaurant restaurantBody, String userId)
			throws ChefsRestaurantException {
		  	RestaurantEntity restaurantEntity =  vendorService.modifyRestaurantDetails(restaurantBody);
		return convertRestaurantEntityToModel(restaurantEntity);
	}

	@Override
	public List<Dish> modifyDishDetails(Dish dishBody, Integer restaurantId) throws ChefsRestaurantException {
		List<DishEntity> dishEntities =  vendorService.modyDishDetails(dishBody, restaurantId);
		List<Dish> dishes = new ArrayList<Dish>();
		
		for (DishEntity dishEntity : dishEntities) {
			Dish dish = new Dish();
			dish.setAvgRating(dishEntity.getAvgRating());
			dish.setDishCuisine(dishEntity.getDishCuisine());
			dish.setDishDescription(dishEntity.getDishDescription());
			dish.setDishId(dishEntity.getDishId());
			dish.setDishName(dishEntity.getDishName());
			dish.setDishType(dishEntity.getDishType());
			dish.setImageUrl(dishEntity.getImageUrl());
			dish.setPrice(dishEntity.getPrice());
			dish.setSpeciality(dishEntity.getSpeciality());
			dishes.add(dish);
		}
		return dishes;
	}

	@Override
	public List<Dish> deleteDish(Integer dishId, Integer restaurantId) throws ChefsRestaurantException {
		List<DishEntity> dishEntities =  vendorService.deleteDish(dishId, restaurantId);
		List<Dish> dishes = new ArrayList<Dish>();
		
		for (DishEntity dishEntity : dishEntities) {
			Dish dish = new Dish();
			dish.setAvgRating(dishEntity.getAvgRating());
			dish.setDishCuisine(dishEntity.getDishCuisine());
			dish.setDishDescription(dishEntity.getDishDescription());
			dish.setDishId(dishEntity.getDishId());
			dish.setDishName(dishEntity.getDishName());
			dish.setDishType(dishEntity.getDishType());
			dish.setImageUrl(dishEntity.getImageUrl());
			dish.setPrice(dishEntity.getPrice());
			dish.setSpeciality(dishEntity.getSpeciality());
			dishes.add(dish);
		}
		return dishes;
	}

	@Override
	public List<Dish> addDish(Dish newDish, Integer restaurantId) throws ChefsRestaurantException {
		List<DishEntity> dishEntities =  vendorService.addDish(newDish, restaurantId);
		List<Dish> dishes = new ArrayList<Dish>();
		
		for (DishEntity dishEntity : dishEntities) {
			Dish dish = new Dish();
			dish.setAvgRating(dishEntity.getAvgRating());
			dish.setDishCuisine(dishEntity.getDishCuisine());
			dish.setDishDescription(dishEntity.getDishDescription());
			dish.setDishId(dishEntity.getDishId());
			dish.setDishName(dishEntity.getDishName());
			dish.setDishType(dishEntity.getDishType());
			dish.setImageUrl(dishEntity.getImageUrl());
			dish.setPrice(dishEntity.getPrice());
			dish.setSpeciality(dishEntity.getSpeciality());
			dishes.add(dish);
		}
		return dishes;
	}

	@Override
	public String addRestaurant(Restaurant restaurant, Integer userId) throws ChefsRestaurantException {
		String response = vendorService.addRestaurant(restaurant, userId);
		return response;
	}

	@Override
	public Restaurant deactivateRestaurant(Integer userId, Integer restaurantId) throws ChefsRestaurantException {
		RestaurantEntity restaurantEntity =  vendorService.deactivateRestaurant(restaurantId);
		return convertRestaurantEntityToModel(restaurantEntity);
	}
	@Override
	public Restaurant activateRestaurant(Integer userId, Integer restaurantId) throws ChefsRestaurantException {
		RestaurantEntity restaurantEntity =  vendorService.activateRestaurant(restaurantId);
		return convertRestaurantEntityToModel(restaurantEntity);
	}
	
	public Restaurant convertRestaurantEntityToModel(RestaurantEntity restaurantEntity) throws ChefsRestaurantException {
	  	Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantName(restaurantEntity.getRestaurantName());
		restaurant.setRestaurantId(restaurantEntity.getRestaurantId());
		restaurant.setRestaurantContact(restaurantEntity.getRestaurantContact());
		restaurant.setRestaurantType(restaurantEntity.getRestaurantType());
		restaurant.setResState(restaurantEntity.getResState());
		restaurant.setAvgRating(restaurantEntity.getAvgRating());
		restaurant.setApprovalStatus(restaurantEntity.getApprovalStatus());
		restaurant.setPincode(restaurantEntity.getPincode());
		restaurant.setAddressLine1(restaurantEntity.getAddressLine1());
		restaurant.setArea(restaurantEntity.getArea());
		restaurant.setCity(restaurantEntity.getCity());

		RestaurantTransactionEntity restaurantTransactionEntity = restaurantEntity.getTransaction();
		RestaurantTransaction restaurantTransaction = new RestaurantTransaction();
		if (restaurantTransactionEntity != null) {
			restaurantTransaction
					.setRestaurantApproxCost(restaurantTransactionEntity.getRestaurantApproxCost());
			restaurantTransaction
					.setRestaurantOrderCounter(restaurantTransactionEntity.getRestaurantOrderCounter());
			restaurantTransaction.setRestaurantStatus(restaurantTransactionEntity.getRestaurantStatus());
			restaurantTransaction
					.setRestaurantTransactionId(restaurantTransactionEntity.getRestaurantTransactionId());
		}
		restaurant.setTransaction(restaurantTransaction);

		List<Dish> dishs = new ArrayList<Dish>();
		if (restaurantEntity.getDishes() != null) {
			List<DishEntity> dishEntities = restaurantEntity.getDishes();
			for (DishEntity dishEntity : dishEntities) {
				Dish dish = new Dish();
				dish.setAvgRating(dishEntity.getAvgRating());
				dish.setDishCuisine(dishEntity.getDishCuisine());
				dish.setDishDescription(dishEntity.getDishDescription());
				dish.setDishId(dishEntity.getDishId());
				dish.setDishName(dishEntity.getDishName());
				dish.setDishType(dishEntity.getDishType());
				dish.setImageUrl(dishEntity.getImageUrl());
				dish.setPrice(dishEntity.getPrice());
				dish.setSpeciality(dishEntity.getSpeciality());
				dishs.add(dish);
			}
		}
		restaurant.setDishes(dishs);
		String[] photoUrls = restaurantEntity.getPhotoUrls().split("-");
		List<String> urls = new ArrayList<String>();
		for (String url : photoUrls) {
			urls.add(url);
		}
		restaurant.setPhotoUrls(urls);

		return restaurant;
	}

}
