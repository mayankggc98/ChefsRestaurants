package com.infy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.entity.RestaurantEntity;
import com.infy.repository.RestaurantRepository;
import com.infy.utility.ChefsRestaurantException;

@Service(value = "/adminService")
@Transactional
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public List<RestaurantEntity> getRestaurantsList() throws ChefsRestaurantException {
		Iterable<RestaurantEntity> iterable =  restaurantRepository.findAll();
		
		List<RestaurantEntity> restaurantEntities = new ArrayList<RestaurantEntity>();
		for (RestaurantEntity restaurantEntity : iterable) {
			restaurantEntities.add(restaurantEntity);
		}
		
		return restaurantEntities;
	}

	@Override
	public void updateRestaurantApprovalStatus(Integer restaurantId, String status) throws ChefsRestaurantException {
		Optional<RestaurantEntity> optional = restaurantRepository.findById(restaurantId);
		RestaurantEntity restaurantEntity = optional.orElseThrow(()-> new ChefsRestaurantException(""));
		restaurantEntity.setApprovalStatus(status);
		restaurantRepository.save(restaurantEntity);
	}

}
