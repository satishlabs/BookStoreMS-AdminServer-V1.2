package com.userratings.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.userratings.entity.BookRating;
import com.userratings.entity.UserRating;
import com.userratings.service.RatingService;

@CrossOrigin
@RestController
public class RatingController {
	static Logger log=LoggerFactory.getLogger(RatingController.class); 
	
	@Autowired
	private RatingService ratingService;
	
	@GetMapping("/userRatings/{userId}")
	public List<UserRating> getUserRatingByUserId(@PathVariable String userId){
		log.info("---RatingController---getUserRatingByUserId()-----"); 
		return ratingService.getUserRatingByUserId(userId);
	}
	
	@GetMapping("/bookRating/{bookId}")
	public BookRating getBookRatingByBookId(@PathVariable Integer bookId){
		log.info("---RatingController---getBookRatingByBookId()-----"); 
		return ratingService.getBookRatingByBookId(new Integer(bookId));
	}
	
}
