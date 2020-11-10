package com.userratings.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="myuserratings",schema = "jlcratingsdb") 
@ApiModel(value="UserRating contains ratingId,bookId,userId,rating,review") 
public class UserRating {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	@ApiModelProperty(value="Contains ratingId")
	@Column(name = "rating_id")
	private Integer ratingId;
	
	@ApiModelProperty(value="Contains bookId")
	@Column(name = "book_id")
	private Integer bookId;
	
	@ApiModelProperty(value="Contains userId")
	@Column(name = "user_id")
	private String userId;
	
	@ApiModelProperty(value="Contains bookId")
	@Column(name = "rating")
	private double rating;
	
	@ApiModelProperty(value="Contains review")
	@Column(name = "review")
	private String review;
	
	public UserRating() {}
	public UserRating(Integer bookId, String userId, double rating, String review) {
		this.bookId = bookId;
		this.userId = userId;
		this.rating = rating;
		this.review = review;
	}
	
	public UserRating(Integer ratingId, Integer bookId, String userId, double rating, String review) {
		super();
		this.ratingId = ratingId;
		this.bookId = bookId;
		this.userId = userId;
		this.rating = rating;
		this.review = review;
	}
	public Integer getRatingId() {
		return ratingId;
	}
	public void setRatingId(Integer ratingId) {
		this.ratingId = ratingId;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	@Override
	public String toString() {
		return "UserRating [ratingId=" + ratingId + ", bookId=" + bookId + ", userId=" + userId + ", rating=" + rating
				+ ", review=" + review + "]";
	}
	
	
}
