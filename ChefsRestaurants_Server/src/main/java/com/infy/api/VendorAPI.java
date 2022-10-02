package com.infy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.dto.VendorDTOImpl;
import com.infy.model.Dish;
import com.infy.model.Restaurant;
import com.infy.utility.ChefsRestaurantException;

@RestController
@RequestMapping("/vendorAPI")
@CrossOrigin
public class VendorAPI {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private VendorDTOImpl vendorDTO;
	
	@GetMapping("/getRestaurants/{userId}")
	private ResponseEntity<List<Restaurant>> getRegisteredRestaurants(@PathVariable String userId) throws ChefsRestaurantException {
		try {
			List<Restaurant> restaurants = vendorDTO.getRegisteredRestaurants(userId);			
			return new ResponseEntity<List<Restaurant>>(restaurants, HttpStatus.OK);
		}catch (ChefsRestaurantException e) {
			throw new ChefsRestaurantException(environment.getProperty(e.getMessage()));
		}
	}
	
	@PutMapping("/modifyRestaurant/{userId}")
	private ResponseEntity<Restaurant> modifyRestaurantDetails(@RequestBody Restaurant restaurantBody, @PathVariable String userId) throws ChefsRestaurantException{
		try {
			Restaurant restaurant = vendorDTO.modifyRestaurantDetails(restaurantBody, userId);			
			return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
		}catch (ChefsRestaurantException e) {
			throw new ChefsRestaurantException(environment.getProperty(e.getMessage()));
		}
	}
	
	@PutMapping("/modifyDish/{restaurantId}")
	private ResponseEntity<List<Dish>> modifyDishDetails(@RequestBody Dish dish, @PathVariable Integer restaurantId) throws ChefsRestaurantException{
		try {
			List<Dish> dishes = vendorDTO.modifyDishDetails(dish, restaurantId);			
			return new ResponseEntity<List<Dish>>(dishes, HttpStatus.OK);
		}catch (ChefsRestaurantException e) {
			throw new ChefsRestaurantException(environment.getProperty(e.getMessage()));
		}
	}
	
	@DeleteMapping("/deleteDish/{restaurantId}/{dishId}")
	private ResponseEntity<List<Dish>> modifyRestaurantDetails(@PathVariable Integer restaurantId, @PathVariable Integer dishId) throws ChefsRestaurantException{
		try {
			System.out.println(restaurantId+"  "+dishId);
			List<Dish> dishes = vendorDTO.deleteDish(dishId, restaurantId);			
			return new ResponseEntity<List<Dish>>(dishes, HttpStatus.OK);
		}catch (ChefsRestaurantException e) {
			throw new ChefsRestaurantException(environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping("/addDish/{restaurantId}")
	private ResponseEntity<List<Dish>> addDish(@RequestBody Dish dish, @PathVariable Integer restaurantId) throws ChefsRestaurantException{
		try {
			List<Dish> dishes = vendorDTO.addDish(dish, restaurantId);			
			return new ResponseEntity<List<Dish>>(dishes, HttpStatus.OK);
		}catch (ChefsRestaurantException e) {
			throw new ChefsRestaurantException(environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping("/addRestaurant/{userId}")
	private ResponseEntity<String> addRestaurant(@RequestBody Restaurant restaurant, @PathVariable Integer userId) throws ChefsRestaurantException{
		try {
			String response = vendorDTO.addRestaurant(restaurant, userId);			
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}catch (ChefsRestaurantException e) {
			throw new ChefsRestaurantException(environment.getProperty(e.getMessage()));
		}
	}
	
	@PutMapping("/deactivateRestaurant/{userId}/{restaurantId}")
	private ResponseEntity<Restaurant> deactivateRestaurant(@PathVariable Integer userId, @PathVariable Integer restaurantId) throws ChefsRestaurantException{
		try {
			Restaurant response = vendorDTO.deactivateRestaurant(userId, restaurantId);			
			return new ResponseEntity<Restaurant>(response, HttpStatus.OK);
		}catch (ChefsRestaurantException e) {
			throw new ChefsRestaurantException(environment.getProperty(e.getMessage()));
		}
	}	
	@PutMapping("/activateRestaurant/{userId}/{restaurantId}")
	private ResponseEntity<Restaurant> activateRestaurant(@PathVariable Integer userId, @PathVariable Integer restaurantId) throws ChefsRestaurantException{
		try {
			Restaurant response = vendorDTO.activateRestaurant(userId, restaurantId);			
			return new ResponseEntity<Restaurant>(response, HttpStatus.OK);
		}catch (ChefsRestaurantException e) {
			throw new ChefsRestaurantException(environment.getProperty(e.getMessage()));
		}
	}	
}
