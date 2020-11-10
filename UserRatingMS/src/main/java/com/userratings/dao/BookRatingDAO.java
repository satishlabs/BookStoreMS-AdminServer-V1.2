package com.userratings.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userratings.entity.BookRating;

public interface BookRatingDAO extends JpaRepository<BookRating, Integer>{

	BookRating getBookRatingByBookId(Integer bookId);

}
