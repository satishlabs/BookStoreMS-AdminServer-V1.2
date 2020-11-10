package com.placeorder.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placeorder.entity.BookInventory;

public interface BookInventoryDAO extends JpaRepository<BookInventory, Integer>{

}
