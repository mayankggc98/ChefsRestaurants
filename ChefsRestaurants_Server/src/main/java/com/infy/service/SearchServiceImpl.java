package com.infy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.repository.SearchDAO;

import com.infy.model.Restaurant;

@Service(value="searchService")
@Transactional
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private SearchDAO searchDAO;

	
	@Override
	public List<Restaurant> viewAllRestaurants() throws Exception {
		List<Restaurant> list1=searchDAO.viewAllRestaurants();
		List<Restaurant> list2=new ArrayList<Restaurant>();
		
		list2 = list1.stream().filter(restaurant -> restaurant.getApprovalStatus().equals("Accepted")).
				collect(Collectors.toList());
		
		if(list2.isEmpty()) {
			throw new Exception("SearchService.NO_RESTAURANTS_FOUND");
		}
		return list2;
	}
	
}
