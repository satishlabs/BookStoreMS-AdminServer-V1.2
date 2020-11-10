package com.satish.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satish.entity.BookPrice;

public interface BookPriceDAO extends JpaRepository<BookPrice, Integer>{

}
