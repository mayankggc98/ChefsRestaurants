package com.infy.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UsersEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String userName;
	private String emailId;
	private String contactNumber;
	private String password;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private List<RolesEntity> roles;
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private List<RestaurantEntity> restaurants;
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private List<UserAddressEntity> addressList;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID", unique = true)
	private WalletEntity wallet;
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private List<UserLikesEntity> userLikesList;
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private List<OrdersEntity> ordersList;
	

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
	
	public List<RolesEntity> getRoles() {
		return roles;
	}
	public void setRoles(List<RolesEntity> roles) {
		this.roles = roles;
	}
	public List<RestaurantEntity> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<RestaurantEntity> restaurants) {
		this.restaurants = restaurants;
	}
	public List<UserAddressEntity> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<UserAddressEntity> addressList) {
		this.addressList = addressList;
	}
	public WalletEntity getWallet() {
		return wallet;
	}
	public void setWallet(WalletEntity wallet) {
		this.wallet = wallet;
	}
	public List<UserLikesEntity> getUserLikesList() {
		return userLikesList;
	}
	public void setUserLikesList(List<UserLikesEntity> userLikesList) {
		this.userLikesList = userLikesList;
	}
	public List<OrdersEntity> getOrdersList() {
		return ordersList;
	}
	public void setOrdersList(List<OrdersEntity> ordersList) {
		this.ordersList = ordersList;
	}
	
	
}
