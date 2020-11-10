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
@Table(name="myorderitems",schema = "jlcordersdb") 
@ApiModel(value="OrderItem contains orderItemId,orderId,bookId,qty,cost") 
public class MyOrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderItemId_generator")
	@SequenceGenerator(name = "orderItemId_generator", initialValue = 1, allocationSize = 1)
	@ApiModelProperty(value="Contains orderItemId")
	@Column(name = "order_item_id")
	private Integer orderItemId;
	
	@ApiModelProperty(value="Contains orderId")
	@Column(name="order_id") 
	private Integer orderId; 
	 
	@ApiModelProperty(value="Contains bookId")
	@Column(name="book_id") 
	private Integer bookId; 
	 
	@ApiModelProperty(value="Contains qty")
	@Column(name="qty") 
	private int qty; 
	 
	@ApiModelProperty(value="Contains cost")
	@Column(name="cost") 
	private double cost;
	
	public MyOrderItem() {}
	public MyOrderItem(Integer orderId, Integer bookId, int qty, double cost) {
		this.orderId = orderId;
		this.bookId = bookId;
		this.qty = qty;
		this.cost = cost;
	}
	public MyOrderItem(Integer orderItemId, Integer orderId, Integer bookId, int qty, double cost) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.bookId = bookId;
		this.qty = qty;
		this.cost = cost;
	}
	public Integer getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", orderId=" + orderId + ", bookId=" + bookId + ", qty=" + qty
				+ ", cost=" + cost + "]";
	}
	
	
	 
}
