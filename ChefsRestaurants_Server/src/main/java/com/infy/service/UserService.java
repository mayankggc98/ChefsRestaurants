package com.infy.service;

import java.util.List;

import com.infy.entity.OrdersEntity;
import com.infy.model.Users;
import com.infy.utility.ChefsRestaurantException;

public interface UserService {
	Users findUser(String contactNumber) throws ChefsRestaurantException;
	String saveUser(Users user) throws ChefsRestaurantException;
	Users getOrders(Integer userId) throws ChefsRestaurantException;
}
