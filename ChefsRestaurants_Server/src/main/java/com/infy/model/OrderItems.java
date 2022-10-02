package com.infy.model;

public class OrderItems {
	private Integer orderItemsId;
	private Dish dish;
	private Integer qty;
	public Integer getOrderItemsId() {
		return orderItemsId;
	}
	public void setOrderItemsId(Integer orderItemsId) {
		this.orderItemsId = orderItemsId;
	}
	public Dish getDish() {
		return dish;
	}
	public void setDish(Dish dish) {
		this.dish = dish;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
}
