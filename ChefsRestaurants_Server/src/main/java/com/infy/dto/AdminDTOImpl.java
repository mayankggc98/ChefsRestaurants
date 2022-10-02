package com.infy.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.infy.entity.DishEntity;
import com.infy.entity.RestaurantEntity;
import com.infy.model.Dish;
import com.infy.model.Restaurant;
import com.infy.model.RestaurantTransaction;
import com.infy.service.AdminServiceImpl;
import com.infy.utility.ChefsRestaurantException;

@Controller(value = "/adminDTO")
public class AdminDTOImpl implements AdminDTO {
	
	@Autowired
	private AdminServiceImpl adminService;

	@Override
	public List<Restaurant> getRestaurantsList() throws ChefsRestaurantException {
		List<RestaurantEntity> restaurantEntities = adminService.getRestaurantsList();
		
		List<Restaurant> restaurants = new ArrayList<>();
		if (restaurantEntities != null && !restaurantEntities.isEmpty()) {
			for (RestaurantEntity re : restaurantEntities) {
				Restaurant r = new Restaurant();
				r.setRestaurantId(re.getRestaurantId());
				r.setRestaurantName(re.getRestaurantName());
				r.setRestaurantContact(re.getRestaurantContact());
				r.setRestaurantType(re.getRestaurantType());
				r.setAddressLine1(re.getAddressLine1());
				r.setArea(re.getArea());
				r.setCity(re.getCity());
				r.setResState(re.getResState());
				r.setPincode(re.getPincode());
				r.setApprovalStatus(re.getApprovalStatus());
				r.setAvgRating(re.getAvgRating());
				String[] photos=re.getPhotoUrls().split("-");
				r.setPhotoUrls(Arrays.asList(photos));
				if(!re.getDishes().isEmpty()) {
					List<Dish> dishList=new ArrayList<>();
					for(DishEntity de:re.getDishes()) {
						Dish d=new Dish();
						d.setDishId(de.getDishId());
						d.setDishName(de.getDishName());
						d.setDishType(de.getDishType());
						d.setDishCuisine(de.getDishCuisine());
						d.setDishDescription(de.getDishDescription());
						d.setAvgRating(de.getAvgRating());
						d.setPrice(de.getPrice());
						d.setSpeciality(de.getSpeciality());
						d.setImageUrl(de.getImageUrl());
						dishList.add(d);
					}
					r.setDishes(dishList);
				}
				if(re.getTransaction()!=null) {
					RestaurantTransaction rt=new RestaurantTransaction();
					rt.setRestaurantTransactionId(re.getTransaction().getRestaurantTransactionId());
					rt.setRestaurantStatus(re.getTransaction().getRestaurantStatus());
					rt.setRestaurantApproxCost(re.getTransaction().getRestaurantApproxCost());
					rt.setRestaurantOrderCounter(re.getTransaction().getRestaurantOrderCounter());
					r.setTransaction(rt);
				}
				restaurants.add(r);
			}
		}
		return restaurants;
	}

	@Override
	public List<Restaurant> updateRestaurantApprovalStatus(Integer restaurantId, String status)
			throws ChefsRestaurantException {
		adminService.updateRestaurantApprovalStatus(restaurantId, status);
		List<Restaurant> restaurants = getRestaurantsList();
		return restaurants;
	}

}
