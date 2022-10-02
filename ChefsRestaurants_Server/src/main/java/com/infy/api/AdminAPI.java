package com.infy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.dto.AdminDTOImpl;
import com.infy.model.Restaurant;
import com.infy.utility.ChefsRestaurantException;

@RequestMapping("/adminAPI")
@RestController
@CrossOrigin
public class AdminAPI {

	@Autowired
	private Environment environment;
	
	@Autowired
	private AdminDTOImpl adminDTO;
	
	@GetMapping("/getRestaurantList")
	public ResponseEntity<List<Restaurant>> getRestaurantsList() throws ChefsRestaurantException{
		try {
			List<Restaurant> restaurants = adminDTO.getRestaurantsList();
			return new ResponseEntity<List<Restaurant>>(restaurants, HttpStatus.OK);
		}catch (ChefsRestaurantException e) {
			throw new ChefsRestaurantException(environment.getProperty(e.getMessage()));
		}
	}
	
	@PutMapping("/updateRestaurantApprovalStatus/{restaurantId}")
	public ResponseEntity<List<Restaurant>> updateRestaurantApprovalStatus(@PathVariable Integer restaurantId, @RequestBody Restaurant restaurant) throws ChefsRestaurantException{
		try {
			List<Restaurant> restaurants = adminDTO.updateRestaurantApprovalStatus(restaurantId, restaurant.getApprovalStatus());
			return new ResponseEntity<List<Restaurant>>(restaurants, HttpStatus.OK);
		}catch (ChefsRestaurantException e) {
			throw new ChefsRestaurantException(environment.getProperty(e.getMessage()));
		}
	}
}
