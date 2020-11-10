package com.bookstoreweb.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.satish.rabbitmq.Order;



@FeignClient(value="MyPlaceOrderMS",url = "http://localhost:7000")
public interface PlaceOrderProxy {
	
	@GetMapping("/myorders/{userId}")
	public List<Order> getOrdersByUserId(@PathVariable String userId);

	@GetMapping("/myorder/{orderId}")
	public Order getOrderByOrderId(@PathVariable Integer orderId);
}
