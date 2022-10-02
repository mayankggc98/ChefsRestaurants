package com.infy.model;

import java.util.List;

public class Users {
	private Integer userId;
	private String userName;
	private String contactNumber;
	private String emailId;
	private String password;
	private List<Roles> roles;
	private List<Restaurant> restaurants;
	private List<UserAddress> addressList;
	private Wallet wallet;
	private List<UserLikes> userLikesList;
	private List<Orders> ordersList;

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Roles> getRoles() {
		return roles;
	}
	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	public List<UserAddress> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<UserAddress> addressList) {
		this.addressList = addressList;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	public List<UserLikes> getUserLikesList() {
		return userLikesList;
	}
	public void setUserLikesList(List<UserLikes> userLikesList) {
		this.userLikesList = userLikesList;
	}
	public List<Orders> getOrdersList() {
		return ordersList;
	}
	public void setOrdersList(List<Orders> ordersList) {
		this.ordersList = ordersList;
	}
	
}
