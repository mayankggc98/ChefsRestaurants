package com.infy.dto;

import java.util.List;

import com.infy.model.Restaurant;
import com.infy.utility.ChefsRestaurantException;

public interface AdminDTO {
	List<Restaurant> getRestaurantsList() throws ChefsRestaurantException;
	List<Restaurant> updateRestaurantApprovalStatus(Integer restaurantId, String status) throws ChefsRestaurantException;
}
