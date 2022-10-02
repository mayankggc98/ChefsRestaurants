package com.infy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.entity.DishEntity;
import com.infy.entity.OrderItemsEntity;
import com.infy.entity.OrdersEntity;
import com.infy.entity.RestaurantEntity;
import com.infy.entity.RestaurantTransactionEntity;
import com.infy.entity.RolesEntity;
import com.infy.entity.UserAddressEntity;
import com.infy.entity.UserLikesEntity;
import com.infy.entity.UsersEntity;
import com.infy.entity.WalletEntity;
import com.infy.model.Dish;
import com.infy.model.OrderItems;
import com.infy.model.Orders;
import com.infy.model.Restaurant;
import com.infy.model.RestaurantTransaction;
import com.infy.model.Role;
import com.infy.model.Roles;
import com.infy.model.UserAddress;
import com.infy.model.UserLikes;
import com.infy.model.Users;
import com.infy.model.Wallet;
import com.infy.repository.UserRepository;
import com.infy.repository.WalletRepository;
import com.infy.utility.ChefsRestaurantException;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	WalletRepository walletRepository;

	@Override
	public Users findUser(String contactNumber) throws ChefsRestaurantException {
		UsersEntity userEntity = userRepository.findByContactNumber(contactNumber);
		if (userEntity == null) {
			throw new ChefsRestaurantException("UserService.INVALID_CREDENTIALS");
		}
		Users users = convertEntityToModel(userEntity);
		users.setPassword(userEntity.getPassword());
		return users;
		
	}

	@Override
	public String saveUser(Users user) throws ChefsRestaurantException {
		UsersEntity userFromDB = userRepository.findByContactNumber(user.getContactNumber());

		if (userFromDB == null) {
			UsersEntity usersEntity = new UsersEntity();
			List<RolesEntity> rolesEntities = new ArrayList<>();
			usersEntity.setContactNumber(user.getContactNumber());
			usersEntity.setPassword(user.getPassword());
			usersEntity.setEmailId(user.getEmailId());
			usersEntity.setUserName(user.getUserName());
			List<UserAddressEntity> userAddressEntities = new ArrayList<UserAddressEntity>();
			for (Roles role : user.getRoles()) {
				RolesEntity roleEntity = new RolesEntity();
				if (role.getRoleType().equals(Role.CUSTOMER)) {
					if (user.getAddressList() == null || user.getAddressList().isEmpty()) {
						throw new ChefsRestaurantException("UserService.ADDRESS_NOT_FOUND");
					} else {
						roleEntity.setRoleType(role.getRoleType());
						List<UserAddress> userAddress = user.getAddressList();
						for (UserAddress address : userAddress) {
							System.out.println(address);
							UserAddressEntity addressEntity = new UserAddressEntity();
							addressEntity.setUserAddressName(address.getUserAddressName());
							addressEntity.setAddressLine1(address.getAddressLine1());
							addressEntity.setAddressLine2(address.getAddressLine2());
							addressEntity.setArea(address.getArea());
							addressEntity.setCity(address.getCity());
							addressEntity.setUserState(address.getUserState());
							addressEntity.setPincode(address.getPincode());
							userAddressEntities.add(addressEntity);
						}
						usersEntity.setAddressList(userAddressEntities);
					}
				} else {
					roleEntity.setRoleType(role.getRoleType());
				}
				rolesEntities.add(roleEntity);
			}
			usersEntity.setRoles(rolesEntities);
			
			return userRepository.save(usersEntity).getUserName();
		} else {
			if (!userFromDB.getPassword().equals(user.getPassword())) {
				throw new ChefsRestaurantException("UserService.INVALID_CREDENTIALS_USER_ALREADY_EXISTS");
			}
			userFromDB.setEmailId(user.getEmailId());
			userFromDB.setUserName(user.getUserName());

			List<RolesEntity> rolesFromDB = userFromDB.getRoles();
			List<RolesEntity> roles = new ArrayList<RolesEntity>();
			List<UserAddressEntity> userAddressEntities = userFromDB.getAddressList();

			for (Roles role : user.getRoles()) {
				if (!rolesFromDB.isEmpty()) {
					System.out.println(role.getRoleType());
					for (RolesEntity roleFromDB : rolesFromDB) {
						if (role.getRoleType().equals(roleFromDB.getRoleType())) {
							System.out.println(roleFromDB.getRoleType());
							throw new ChefsRestaurantException("UserService.USER_ALREADY_EXISTS");
						}
					}
					RolesEntity roleEntity = new RolesEntity();
					if (role.getRoleType().equals(Role.CUSTOMER)) {
						if (user.getAddressList() == null || user.getAddressList().isEmpty()) {
							throw new ChefsRestaurantException("UserService.ADDRESS_NOT_FOUND");
						} else {
							roleEntity.setRoleType(role.getRoleType());
							List<UserAddress> userAddress = user.getAddressList();
							for (UserAddress address : userAddress) {
								System.out.println(address);
								UserAddressEntity addressEntity = new UserAddressEntity();
								addressEntity.setUserAddressName(address.getUserAddressName());
								addressEntity.setAddressLine1(address.getAddressLine1());
								addressEntity.setAddressLine2(address.getAddressLine2());
								addressEntity.setArea(address.getArea());
								addressEntity.setCity(address.getCity());
								addressEntity.setUserState(address.getUserState());
								addressEntity.setPincode(address.getPincode());
								userAddressEntities.add(addressEntity);
							}
						}
					} else {
						roleEntity.setRoleType(role.getRoleType());
					}
					roles.add(roleEntity);
				} else {
					RolesEntity roleEntity = new RolesEntity();
					if (role.getRoleType().equals(Role.CUSTOMER)) {
						if (user.getAddressList() == null || user.getAddressList().isEmpty()) {
							throw new ChefsRestaurantException("UserService.ADDRESS_NOT_FOUND");
						} else {
							roleEntity.setRoleType(role.getRoleType());
							List<UserAddress> userAddress = user.getAddressList();
							for (UserAddress address : userAddress) {
								UserAddressEntity addressEntity = new UserAddressEntity();
								addressEntity.setUserAddressName(address.getUserAddressName());
								addressEntity.setAddressLine1(address.getAddressLine1());
								addressEntity.setAddressLine2(address.getAddressLine2());
								addressEntity.setArea(address.getArea());
								addressEntity.setCity(address.getCity());
								addressEntity.setUserState(address.getUserState());
								addressEntity.setPincode(address.getPincode());
								userAddressEntities.add(addressEntity);
							}
						}
					} else {
						roleEntity.setRoleType(role.getRoleType());
					}
					roles.add(roleEntity);
				}
			}
			for (RolesEntity rolesEntity : roles) {
				rolesFromDB.add(rolesEntity);
			}
			userFromDB.setRoles(rolesFromDB);
			return (userFromDB).getUserName();
		}
	}

	@Override
	public Users getOrders(Integer userId) throws ChefsRestaurantException {
		Optional<UsersEntity> optional = userRepository.findById(userId);
		UsersEntity usersEntity = optional.orElseThrow(()-> new ChefsRestaurantException(""));
		return convertEntityToModel(usersEntity);
	}
	
	public Users convertEntityToModel(UsersEntity userEntity) throws ChefsRestaurantException{
		Users user = new Users();

		user.setUserId(userEntity.getUserId());
		user.setContactNumber(userEntity.getContactNumber());
		user.setEmailId(userEntity.getEmailId());
		user.setUserName(userEntity.getUserName());

		List<Restaurant> restaurants = new ArrayList<>();
		if (userEntity.getRestaurants() != null) {
			List<RestaurantEntity> restaurantEntities = userEntity.getRestaurants();
			for (RestaurantEntity restaurantEntity : restaurantEntities) {
				Restaurant restaurant = new Restaurant();
				restaurant.setRestaurantName(restaurantEntity.getRestaurantName());
				restaurant.setRestaurantId(restaurantEntity.getRestaurantId());
				restaurant.setRestaurantContact(restaurantEntity.getRestaurantContact());
				restaurant.setRestaurantType(restaurantEntity.getRestaurantType());
				restaurant.setResState(restaurantEntity.getResState());
				restaurant.setAvgRating(restaurantEntity.getAvgRating());
				restaurant.setApprovalStatus(restaurantEntity.getApprovalStatus());
				restaurant.setPincode(restaurantEntity.getPincode());
				restaurant.setAddressLine1(restaurantEntity.getAddressLine1());
				restaurant.setArea(restaurantEntity.getArea());
				restaurant.setCity(restaurantEntity.getCity());

				RestaurantTransactionEntity restaurantTransactionEntity = restaurantEntity.getTransaction();
				RestaurantTransaction restaurantTransaction = new RestaurantTransaction();
				if (restaurantTransactionEntity != null) {
					restaurantTransaction
							.setRestaurantApproxCost(restaurantTransactionEntity.getRestaurantApproxCost());
					restaurantTransaction
							.setRestaurantOrderCounter(restaurantTransactionEntity.getRestaurantOrderCounter());
					restaurantTransaction.setRestaurantStatus(restaurantTransactionEntity.getRestaurantStatus());
					restaurantTransaction
							.setRestaurantTransactionId(restaurantTransactionEntity.getRestaurantTransactionId());
				}
				restaurant.setTransaction(restaurantTransaction);

				List<Dish> dishs = new ArrayList<Dish>();
				if (restaurantEntity.getDishes() != null) {
					List<DishEntity> dishEntities = restaurantEntity.getDishes();
					for (DishEntity dishEntity : dishEntities) {
						Dish dish = new Dish();
						dish.setAvgRating(dishEntity.getAvgRating());
						dish.setDishCuisine(dishEntity.getDishCuisine());
						dish.setDishDescription(dishEntity.getDishDescription());
						dish.setDishId(dishEntity.getDishId());
						dish.setDishName(dishEntity.getDishName());
						dish.setDishType(dishEntity.getDishType());
						dish.setImageUrl(dishEntity.getImageUrl());
						dish.setPrice(dishEntity.getPrice());
						dish.setSpeciality(dishEntity.getSpeciality());
						dishs.add(dish);
					}
				}
				restaurant.setDishes(dishs);
				String[] photoUrls = restaurantEntity.getPhotoUrls().split("-");
				List<String> urls = new ArrayList<String>();
				for (String url : photoUrls) {
					urls.add(url);
				}
				restaurant.setPhotoUrls(urls);
				restaurants.add(restaurant);
			}
		}
		user.setRestaurants(restaurants);

		List<Roles> roles = new ArrayList<>();
		if (userEntity.getRoles() != null) {
			for (RolesEntity rolesEntity : userEntity.getRoles()) {
				Roles role = new Roles();
				role.setRoleId(rolesEntity.getRoleId());
				role.setRoleType(rolesEntity.getRoleType());
				roles.add(role);
			}
		}
		user.setRoles(roles);

		List<UserAddress> addresses = new ArrayList<>();
		if (userEntity.getAddressList() != null) {
			for (UserAddressEntity userAddressEntity : userEntity.getAddressList()) {
				UserAddress userAddress = new UserAddress();
				userAddress.setAddressLine1(userAddressEntity.getAddressLine1());
				userAddress.setAddressLine2(userAddressEntity.getAddressLine2());
				userAddress.setArea(userAddressEntity.getArea());
				userAddress.setCity(userAddressEntity.getCity());
				userAddress.setPincode(userAddressEntity.getPincode());
				userAddress.setUserAddressId(userAddressEntity.getUserAddressId());
				userAddress.setUserAddressName(userAddressEntity.getUserAddressName());
				userAddress.setUserState(userAddressEntity.getUserState());
				addresses.add(userAddress);
			}
		}
		user.setAddressList(addresses);

		Wallet wallet = new Wallet();
		WalletEntity walletEntity = walletRepository.findByUserId(userEntity.getUserId());
		if (walletEntity != null) {
			wallet.setAvailableAmount(walletEntity.getAvailableAmount());
			wallet.setWalletId(walletEntity.getWalletId());
		}
		user.setWallet(wallet);

		List<UserLikes> userLikes = new ArrayList<UserLikes>();
		if (userEntity.getUserLikesList() != null) {
			List<UserLikesEntity> userLikesEntities = userEntity.getUserLikesList();
			for (UserLikesEntity userLikesEntity : userLikesEntities) {
				UserLikes userLike = new UserLikes();

				DishEntity dishEntity = userLikesEntity.getDish();
				Dish dish = new Dish();
				dish.setAvgRating(dishEntity.getAvgRating());
				dish.setDishCuisine(dishEntity.getDishCuisine());
				dish.setDishDescription(dishEntity.getDishDescription());
				dish.setDishId(dishEntity.getDishId());
				dish.setDishName(dishEntity.getDishName());
				dish.setDishType(dishEntity.getDishType());
				dish.setImageUrl(dishEntity.getImageUrl());
				dish.setPrice(dishEntity.getPrice());
				dish.setSpeciality(dishEntity.getSpeciality());

				userLike.setDish(dish);
				userLike.setLikeId(userLikesEntity.getLikeId());

				RestaurantEntity restaurantEntity = userLikesEntity.getRestaurant();
				Restaurant restaurant = new Restaurant();
				restaurant.setRestaurantName(restaurantEntity.getRestaurantName());
				restaurant.setRestaurantId(restaurantEntity.getRestaurantId());
				restaurant.setRestaurantContact(restaurantEntity.getRestaurantContact());
				restaurant.setRestaurantType(restaurantEntity.getRestaurantType());
				restaurant.setResState(restaurantEntity.getResState());
				restaurant.setAvgRating(restaurantEntity.getAvgRating());
				restaurant.setApprovalStatus(restaurantEntity.getApprovalStatus());
				restaurant.setPincode(restaurantEntity.getPincode());
				restaurant.setAddressLine1(restaurantEntity.getAddressLine1());
				restaurant.setArea(restaurantEntity.getArea());
				restaurant.setCity(restaurantEntity.getCity());

				RestaurantTransactionEntity restaurantTransactionEntity = restaurantEntity.getTransaction();
				RestaurantTransaction restaurantTransaction = new RestaurantTransaction();
				if (restaurantTransactionEntity != null) {
					restaurantTransaction
							.setRestaurantApproxCost(restaurantTransactionEntity.getRestaurantApproxCost());
					restaurantTransaction
							.setRestaurantOrderCounter(restaurantTransactionEntity.getRestaurantOrderCounter());
					restaurantTransaction.setRestaurantStatus(restaurantTransactionEntity.getRestaurantStatus());
					restaurantTransaction
							.setRestaurantTransactionId(restaurantTransactionEntity.getRestaurantTransactionId());
				}
				restaurant.setTransaction(restaurantTransaction);

				List<DishEntity> dishEntities = restaurantEntity.getDishes();
				List<Dish> dishs = new ArrayList<Dish>();
				if (!dishEntities.isEmpty()) {
					for (DishEntity dishEntity1 : dishEntities) {
						Dish dish1 = new Dish();
						dish1.setAvgRating(dishEntity1.getAvgRating());
						dish1.setDishCuisine(dishEntity1.getDishCuisine());
						dish1.setDishDescription(dishEntity1.getDishDescription());
						dish1.setDishId(dishEntity1.getDishId());
						dish1.setDishName(dishEntity1.getDishName());
						dish1.setDishType(dishEntity1.getDishType());
						dish1.setImageUrl(dishEntity1.getImageUrl());
						dish1.setPrice(dishEntity1.getPrice());
						dish1.setSpeciality(dishEntity1.getSpeciality());
						dishs.add(dish1);
					}
				}
				restaurant.setDishes(dishs);
				String[] photoUrls = restaurantEntity.getPhotoUrls().split("-");
				List<String> urls = new ArrayList<String>();
				for (String url : photoUrls) {
					urls.add(url);
				}
				restaurant.setPhotoUrls(urls);
				userLike.setRestaurant(restaurant);

				userLike.setVegNonveg(userLikesEntity.getVegNonveg());
				userLikes.add(userLike);
			}
		}
		user.setUserLikesList(userLikes);

		List<Orders> orders = new ArrayList<>();
		if (!userEntity.getOrdersList().isEmpty() || userEntity.getOrdersList() != null) {
			List<OrdersEntity> ordersEntities = userEntity.getOrdersList();
			for (OrdersEntity ordersEntity : ordersEntities) {
				Orders order = new Orders();
				order.setOrderId(ordersEntity.getOrderId());
				order.setOrderBill(ordersEntity.getOrderBill());
				order.setOrderStatus(ordersEntity.getOrderStatus());

				List<OrderItemsEntity> orderItemsEntities = ordersEntity.getOrderItemsList();
				List<OrderItems> orderItemsList = new ArrayList<OrderItems>();
				for (OrderItemsEntity orderItemsEntity : orderItemsEntities) {
					OrderItems orderItems = new OrderItems();
					orderItems.setOrderItemsId(orderItemsEntity.getOrderItemsId());
					orderItems.setQty(orderItemsEntity.getQty());

					DishEntity dishEntity = orderItemsEntity.getDish();
					Dish dish2 = new Dish();
					dish2.setAvgRating(dishEntity.getAvgRating());
					dish2.setDishCuisine(dishEntity.getDishCuisine());
					dish2.setDishDescription(dishEntity.getDishDescription());
					dish2.setDishId(dishEntity.getDishId());
					dish2.setDishName(dishEntity.getDishName());
					dish2.setDishType(dishEntity.getDishType());
					dish2.setImageUrl(dishEntity.getImageUrl());
					dish2.setPrice(dishEntity.getPrice());
					dish2.setSpeciality(dishEntity.getSpeciality());

					orderItems.setDish(dish2);
					orderItemsList.add(orderItems);
				}
				order.setOrderItemsList(orderItemsList);
				orders.add(order);
			}
		}
		user.setOrdersList(orders);
//		user.getOrdersList().forEach((order)->System.out.println(order.getOrderId()));
		return user;
	}
}
