package com.infy.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.infy.entity.UsersEntity;

public interface UserRepository extends CrudRepository<UsersEntity, Integer>{
	
	UsersEntity findByContactNumber(String contactNumber);
	UsersEntity findByUserId(Integer userId);
}
