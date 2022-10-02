package com.infy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.model.Restaurant;
import com.infy.service.SearchService;

@CrossOrigin
@RestController
@RequestMapping("searchAPI")
public class SearchAPI {

	@Autowired
	private SearchService searchService;
	
	@Autowired
	private Environment environment;
	
	//View all the restaurants registered to the application
	@GetMapping(value = "/viewAllRestaurants")
	public ResponseEntity<List<Restaurant>> viewAllRestaurants(){
		
		try{
			List<Restaurant> restaurants = searchService.viewAllRestaurants();
			return new ResponseEntity<List<Restaurant>>(restaurants, HttpStatus.OK);
			}
			catch(Exception e){

				throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()));
			}
	}
	
		
}

