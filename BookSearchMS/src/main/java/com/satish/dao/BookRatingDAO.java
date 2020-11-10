package com.satish.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satish.entity.BookRating;

public interface BookRatingDAO extends JpaRepository<BookRating, Integer>{
	
}
