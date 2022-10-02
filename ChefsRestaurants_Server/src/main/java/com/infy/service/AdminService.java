package com.infy.service;

import java.util.List;

import com.infy.entity.RestaurantEntity;
import com.infy.utility.ChefsRestaurantException;

public interface AdminService {
	
	List<RestaurantEntity> getRestaurantsList() throws ChefsRestaurantException;
	void updateRestaurantApprovalStatus(Integer restaurantId, String status) throws ChefsRestaurantException;

}
