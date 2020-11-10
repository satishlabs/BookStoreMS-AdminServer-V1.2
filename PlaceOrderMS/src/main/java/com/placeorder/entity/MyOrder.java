package com.placeorder.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "myorders",schema = "jlcordersdb")
@ApiModel(value="Order contains orderId,orderDate,userId,totalQty,totalCost,status") 
public class MyOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderId_generator")
	@SequenceGenerator(name = "orderId_generator",sequenceName = "myorderId_gen",initialValue = 1002, allocationSize = 1)
	@ApiModelProperty(value="Contains orderId")
	@Column(name = "order_Id")
	private Integer orderId;
	
	@ApiModelProperty(value="Contains orderDate")
	@Column(name = "order_date")
	private String orderDate;
	
	@ApiModelProperty(value="Contains userId")
	@Column(name = "user_id")
	private String userId;
	
	@ApiModelProperty(value="Contains totalQty")
	@Column(name = "total_qty")
	private int totalQty;
	
	@ApiModelProperty(value="Contains totalCost")
	@Column(name = "total_cost")
	private double totalCost;
	
	@ApiModelProperty(value="Contains status")
	@Column(name = "status")
	private String status;

	public MyOrder() {}
	public MyOrder(String orderDate, String userId, int totalQty, double totalCost, String status) {
		this.orderDate = orderDate;
		this.userId = userId;
		this.totalQty = totalQty;
		this.totalCost = totalCost;
		this.status = status;
	}
	public MyOrder(Integer orderId, String orderDate, String userId, int totalQty, double totalCost, String status) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.userId = userId;
		this.totalQty = totalQty;
		this.totalCost = totalCost;
		this.status = status;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", userId=" + userId + ", totalQty="
				+ totalQty + ", totalCost=" + totalCost + ", status=" + status + "]";
	}
	
	
}
