package com.infy.model;

import java.util.List;

public class Orders {

	private Integer orderId;
	private Integer orderBill;
	private String orderStatus;
	private List<OrderItems> orderItemsList;
	
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public Integer getOrderBill() {
		return orderBill;
	}
	public void setOrderBill(Integer orderBill) {
		this.orderBill = orderBill;
	}
	public List<OrderItems> getOrderItemsList() {
		return orderItemsList;
	}
	public void setOrderItemsList(List<OrderItems> orderItemsList) {
		this.orderItemsList = orderItemsList;
	}
	

}

