package com.userratings.service;

import java.util.List;

import com.userratings.entity.BookRating;
import com.userratings.entity.UserRating;

public interface RatingService {
	public List<UserRating> getUserRatingByUserId(String userId);
	public void updateBookRating(BookRating bookRating);
	public BookRating getBookRatingByBookId(Integer bookId);
}
