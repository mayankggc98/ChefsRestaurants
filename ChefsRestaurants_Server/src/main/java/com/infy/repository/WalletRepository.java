package com.infy.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.entity.WalletEntity;

public interface WalletRepository extends CrudRepository<WalletEntity, Integer> {
	
	WalletEntity findByUserId(Integer userId);
}
