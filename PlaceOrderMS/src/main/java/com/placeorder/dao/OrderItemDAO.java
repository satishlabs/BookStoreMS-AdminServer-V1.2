package com.placeorder.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placeorder.entity.MyOrderItem;

public interface OrderItemDAO extends JpaRepository<MyOrderItem, Integer>{

}
