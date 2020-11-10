package com.userratings.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userratings.entity.UserRating;

public interface UserRatingDAO extends JpaRepository<UserRating, Integer>{

	List<UserRating> getUserRatingByUserId(String userId);

	List<UserRating> getUserRatingByBookId(Integer bookId);

}
