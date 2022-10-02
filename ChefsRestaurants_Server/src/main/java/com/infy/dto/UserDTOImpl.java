package com.infy.dto;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.infy.model.Orders;
import com.infy.model.Users;
import com.infy.service.UserServiceImpl;
import com.infy.utility.ChefsRestaurantException;
import com.infy.utility.HashingUtility;

@Controller(value = "userDTO")
public class UserDTOImpl implements UserDTO {
	
	@Autowired
	UserServiceImpl userService;
	
	@Override
	public Users authenticator(String contactNumber, String password) throws ChefsRestaurantException, NoSuchAlgorithmException {
		Users user = userService.findUser(contactNumber);
		String passwordHash = HashingUtility.getHashValue(password);
		if(user.getPassword().equals(passwordHash)) {
			return user;
		}else {			
			throw new ChefsRestaurantException("UserService.INVALID_CREDENTIALS");
		}
	}

	@Override
	public String registerUser(Users user) throws ChefsRestaurantException, NoSuchAlgorithmException {
		user.setPassword(HashingUtility.getHashValue(user.getPassword()));
		String message = userService.saveUser(user);
		return message;
	}

	@Override
	public Users getOrders(Integer userId) throws ChefsRestaurantException {
		return userService.getOrders(userId);
	}
	
}
