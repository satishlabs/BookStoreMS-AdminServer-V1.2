package com.placeorder.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placeorder.entity.MyOrder;

public interface OrderDAO extends JpaRepository<MyOrder, Integer>{

	List<MyOrder> getOrdersByUserId(String userId);

}
