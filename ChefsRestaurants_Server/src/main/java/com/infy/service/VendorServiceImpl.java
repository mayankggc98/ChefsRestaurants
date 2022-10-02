package com.infy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.entity.DishEntity;
import com.infy.entity.DishRatingEntity;
import com.infy.entity.RestaurantEntity;
import com.infy.entity.UsersEntity;
import com.infy.model.Dish;
import com.infy.model.Restaurant;
import com.infy.repository.DishRatingRepository;
import com.infy.repository.DishRepository;
import com.infy.repository.RestaurantRepository;
import com.infy.repository.UserRepository;
import com.infy.utility.ChefsRestaurantException;

@Service(value = "vendorService")
@Transactional
public class VendorServiceImpl implements VendorService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	DishRepository dishRepository;
	
	@Autowired
	private DishRatingRepository dishRatingRepository;

	@Override
	public List<RestaurantEntity> getRegisteredRestaurants(String userId) throws ChefsRestaurantException {
		Integer userID = 0;
		try{
			userID= Integer.parseInt(userId);
			System.out.println(userID);
		}catch(NumberFormatException ex) {
			throw new ChefsRestaurantException("VendorService.INVALID_USER_ID");
		}
		UsersEntity userfromDB  = userRepository.findByUserId(userID);
		if(userfromDB == null) {
			throw new ChefsRestaurantException("VendorService.INVALID_USER");
		}
		List<RestaurantEntity> restaurantEntities = userfromDB.getRestaurants();
		return restaurantEntities;
	}

	@Override
	public RestaurantEntity modifyRestaurantDetails(Restaurant restaurant) throws ChefsRestaurantException {
		Integer restaurntId = restaurant.getRestaurantId();
		Optional<RestaurantEntity> optional = restaurantRepository.findById(restaurntId);
		RestaurantEntity restaurantEntity = optional.orElseThrow(()-> new ChefsRestaurantException(""));
		restaurantEntity.setRestaurantName(restaurant.getRestaurantName());
		restaurantEntity.setRestaurantContact(restaurant.getRestaurantContact());
		restaurantEntity.setRestaurantType(restaurant.getRestaurantType());
		restaurantEntity.setAddressLine1(restaurant.getAddressLine1());
		restaurantEntity.setArea(restaurant.getArea());
		restaurantEntity.setCity(restaurant.getCity());
		restaurantEntity.setResState(restaurant.getResState());
		restaurantRepository.save(restaurantEntity).getRestaurantId();
		return restaurantEntity;
	}

	@Override
	public List<DishEntity> modyDishDetails(Dish dish, Integer restaurantId) throws ChefsRestaurantException {
		Integer dishId = dish.getDishId();
		Optional<DishEntity> optional = dishRepository.findById(dishId);
		DishEntity dishEntity = optional.orElseThrow(() -> new ChefsRestaurantException(""));
		dishEntity.setDishName(dish.getDishName());
		dishEntity.setDishCuisine(dish.getDishCuisine());
		dishEntity.setDishDescription(dish.getDishDescription());
		dishEntity.setDishType(dish.getDishType());
		dishEntity.setImageUrl(dish.getImageUrl());
		dishEntity.setPrice(dish.getPrice());
		dishEntity.setSpeciality(dish.getSpeciality());
		dishRepository.save(dishEntity);
		
		Optional<RestaurantEntity> restaurantOptional = restaurantRepository.findById(restaurantId);
		RestaurantEntity restaurantEntity = restaurantOptional.orElseThrow(()->new ChefsRestaurantException(""));
		List<DishEntity> dishEntities = restaurantEntity.getDishes();
		
		return dishEntities;
		
	}

	@Override
	public List<DishEntity> deleteDish(Integer dishId, Integer restaurantId) throws ChefsRestaurantException {
		Optional<RestaurantEntity> restaurantOptional = restaurantRepository.findById(restaurantId);
		RestaurantEntity restaurantEntity = restaurantOptional.orElseThrow(()->new ChefsRestaurantException(""));
		List<DishEntity> dishEntities = restaurantEntity.getDishes();
		List<DishEntity> updatedDishes = new ArrayList<DishEntity>();
		for (DishEntity dishEntity : dishEntities) {
			if(dishEntity.getDishId().equals(dishId)) {
				System.out.println(dishEntity.getDishId()+"   "+dishId);
				continue;
			}else {
				updatedDishes.add(dishEntity);
			}
		}
		restaurantEntity.setDishes(updatedDishes);
		restaurantRepository.save(restaurantEntity);
		return updatedDishes;
	}

	@Override
	public List<DishEntity> addDish(Dish dish, Integer restaurantId) throws ChefsRestaurantException {
		Optional<RestaurantEntity> optional = restaurantRepository.findById(restaurantId);
		RestaurantEntity restaurantEntity = optional.orElseThrow(() -> new ChefsRestaurantException(""));
		List<DishEntity> dishEntities = restaurantEntity.getDishes();
		
		DishEntity dishEntity = new DishEntity();
		dishEntity.setDishName(dish.getDishName());
		dishEntity.setDishCuisine(dish.getDishCuisine());
		dishEntity.setDishDescription(dish.getDishDescription());
		dishEntity.setDishType(dish.getDishType());
		dishEntity.setImageUrl(dish.getImageUrl());
		dishEntity.setPrice(dish.getPrice());
		dishEntity.setSpeciality(dish.getSpeciality());
		dishEntity.setAvgRating(0.0);
		
		dishEntities.add(dishRepository.save(dishEntity));
		restaurantEntity.setDishes(dishEntities);
		restaurantRepository.save(restaurantEntity);
		
		Optional<RestaurantEntity> restaurantOptional = restaurantRepository.findById(restaurantId);
		RestaurantEntity restaurantEntityFromDB = restaurantOptional.orElseThrow(()->new ChefsRestaurantException(""));
		List<DishEntity> dishEntitiesFromDB = restaurantEntityFromDB.getDishes();
		
		return dishEntitiesFromDB;
	}

	@Override
	public String addRestaurant(Restaurant restaurant, Integer userId) throws ChefsRestaurantException {
		RestaurantEntity restaurantEntity = new RestaurantEntity();
		restaurantEntity.setRestaurantName(restaurant.getRestaurantName());
		restaurantEntity.setRestaurantContact(restaurant.getRestaurantContact());
		restaurantEntity.setRestaurantType(restaurant.getRestaurantType());
		restaurantEntity.setAddressLine1(restaurant.getAddressLine1());
		restaurantEntity.setArea(restaurant.getArea());
		restaurantEntity.setCity(restaurant.getCity());
		restaurantEntity.setResState(restaurant.getResState());
		restaurantEntity.setPincode(restaurant.getPincode());
		String photos = "";
		for (String photo : restaurant.getPhotoUrls()) {
			if(photos=="") {
				photos += photo;
			}else{
				photos += "-"+photo;
			}
		}
		restaurantEntity.setPhotoUrls(photos);
		restaurantEntity.setAvgRating(0.0);
		restaurantEntity.setApprovalStatus("Waiting for Approval");
		
		RestaurantEntity restaurantEntity2 = restaurantRepository.save(restaurantEntity);
		
		UsersEntity usersEntity = userRepository.findByUserId(userId);
		List<RestaurantEntity> restaurantEntities = usersEntity.getRestaurants();
		restaurantEntities.add(restaurantEntity2);
		usersEntity.setRestaurants(restaurantEntities);
		userRepository.save(usersEntity);
		String message = "You have successfully registered Restaurant "+restaurant.getRestaurantName();
		return message;
	}

	@Override
	public RestaurantEntity deactivateRestaurant(Integer restaurantId) throws ChefsRestaurantException {
		Optional<RestaurantEntity> optional = restaurantRepository.findById(restaurantId);
		RestaurantEntity restaurantEntity = optional.orElseThrow(()-> new ChefsRestaurantException(""));
		restaurantEntity.setApprovalStatus("Deactivated");
		restaurantRepository.save(restaurantEntity);
		return restaurantEntity;
	}
	@Override
	public RestaurantEntity activateRestaurant(Integer restaurantId) throws ChefsRestaurantException {
		Optional<RestaurantEntity> optional = restaurantRepository.findById(restaurantId);
		RestaurantEntity restaurantEntity = optional.orElseThrow(()-> new ChefsRestaurantException(""));
		restaurantEntity.setApprovalStatus("Waiting for Approval");
		restaurantRepository.save(restaurantEntity);
		return restaurantEntity;
	}

}
