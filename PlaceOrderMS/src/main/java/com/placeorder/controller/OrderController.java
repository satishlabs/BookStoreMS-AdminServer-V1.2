package com.placeorder.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.placeorder.entity.MyOrder;
import com.placeorder.service.OrderService;

@CrossOrigin
@RestController
public class OrderController {
	static Logger log = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@GetMapping("/myorders/{userId}")
	public List<MyOrder> getOrdersByUserId(@PathVariable String userId) {
		List<MyOrder> orderList = orderService.getOrdersByUserId(userId);
		return orderList;
	}

	@GetMapping("/myorder/{orderId}")
	public MyOrder getOrderByOrderId(@PathVariable Integer orderId) {
		MyOrder myorder = orderService.getOrderByOrderId(orderId);
		return myorder;
	}
}
