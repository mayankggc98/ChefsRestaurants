package com.infy.dao.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.infy.repository.SearchDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class SearchDAOTest {
	@Autowired
	SearchDAO searchDAO;
	
	//Valid View All Restaurants
	@Test
	public void validviewAllRestaurants()throws Exception{
		Assert.assertFalse(searchDAO.viewAllRestaurants().isEmpty());
	}
	
}








	
