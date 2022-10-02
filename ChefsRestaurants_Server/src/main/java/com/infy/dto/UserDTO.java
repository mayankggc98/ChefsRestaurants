package com.infy.dto;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.infy.model.Orders;
import com.infy.model.Users;
import com.infy.utility.ChefsRestaurantException;

public interface UserDTO {
	Users authenticator(String contactNumber, String password) throws ChefsRestaurantException, NoSuchAlgorithmException;
	String registerUser(Users user) throws ChefsRestaurantException, NoSuchAlgorithmException;
	Users getOrders(Integer userId) throws ChefsRestaurantException;
}
