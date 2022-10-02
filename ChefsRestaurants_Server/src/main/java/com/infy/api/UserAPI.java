package com.infy.api;

import java.security.NoSuchAlgorithmException;
import java.util.List;

//import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infy.dto.UserDTOImpl;
import com.infy.model.Orders;
import com.infy.model.Users;
import com.infy.utility.ChefsRestaurantException;

@RestController
@RequestMapping("userAPI")
@CrossOrigin
public class UserAPI {
	
	@Autowired
	UserDTOImpl userDTO;
	
	@Autowired
	private Environment environment;
	
	@PostMapping("/login")
	private ResponseEntity<Users> login(@RequestBody Users user) throws ChefsRestaurantException, NoSuchAlgorithmException{
			try {
				Users userFromDB = userDTO.authenticator(user.getContactNumber(), user.getPassword());
				return new ResponseEntity<Users>(userFromDB, HttpStatus.OK);
			}catch (ChefsRestaurantException e) {
				throw new ChefsRestaurantException(environment.getProperty(e.getMessage()));
			}
		
	}
	
	@PostMapping("/register")
	private ResponseEntity<String> register(@RequestBody Users user) throws ChefsRestaurantException, NoSuchAlgorithmException{
		try {
			
		String userName = userDTO.registerUser(user);
		String message = environment.getProperty("UserAPI.REGISTER_USER_SUCCESS1")+userName+environment.getProperty("UserAPI.REGISTER_USER_SUCCESS2");
		return new ResponseEntity<String>(message, HttpStatus.OK);
		}catch (ChefsRestaurantException e) {
			throw new ChefsRestaurantException(environment.getProperty(e.getMessage()));
		}
	}
	
	@GetMapping("/getOrders/{userId}")
	private ResponseEntity<Users> getOrders(@PathVariable Integer userId) throws ChefsRestaurantException{
		try {
//			Integer userID = Integer.parseInt(userId);
			Users userFromDB = userDTO.getOrders(userId);
			return new ResponseEntity<Users>(userFromDB, HttpStatus.OK);
		}catch (ChefsRestaurantException e) {
			throw new ChefsRestaurantException(environment.getProperty(e.getMessage()));
		}
	}
}
