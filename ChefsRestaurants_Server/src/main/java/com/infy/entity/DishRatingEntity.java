package com.infy.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;

@Entity(name = "dishRating")
@Table(name="dish_rating")
public class DishRatingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer dishRatingId;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dish_id", unique = true)
	private DishEntity dish;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID", unique = true)
	private UsersEntity user;
	private Integer rating;
	
	
	public DishRatingEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DishRatingEntity(Integer dishRatingId, DishEntity dish, UsersEntity user, Integer rating) {
		super();
		this.dishRatingId = dishRatingId;
		this.dish = dish;
		this.user = user;
		this.rating = rating;
	}
	public Integer getDishRatingId() {
		return dishRatingId;
	}
	public void setDishRatingId(Integer dishRatingId) {
		this.dishRatingId = dishRatingId;
	}
	
	public DishEntity getDish() {
		return dish;
	}
	public void setDish(DishEntity dish) {
		this.dish = dish;
	}
	public UsersEntity getUser() {
		return user;
	}
	public void setUser(UsersEntity user) {
		this.user = user;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}

}
