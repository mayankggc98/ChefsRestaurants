package com.infy.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_likes")
public class UserLikesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer likeId;
	private String vegNonveg;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dish_id", unique = true)
	private DishEntity dish;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "restaurant_id", unique = true)
	private RestaurantEntity restaurant;
	public Integer getLikeId() {
		return likeId;
	}
	public void setLikeId(Integer likeId) {
		this.likeId = likeId;
	}
	public String getVegNonveg() {
		return vegNonveg;
	}
	public void setVegNonveg(String vegNonveg) {
		this.vegNonveg = vegNonveg;
	}
	public DishEntity getDish() {
		return dish;
	}
	public void setDish(DishEntity dish) {
		this.dish = dish;
	}
	public RestaurantEntity getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(RestaurantEntity restaurant) {
		this.restaurant = restaurant;
	}
	
	
}
