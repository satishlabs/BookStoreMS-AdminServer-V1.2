package com.satish.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satish.entity.BookInventory;

public interface BookInventoryDAO extends JpaRepository<BookInventory, Integer>{

}
