package com.satish.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="mybookratings",schema = "jlcbooksdb") 
@ApiModel(value="BookInventory contains bookId,avgRating,numberOfSearches") 
public class BookRating {
	@Id  
	@Column(name="book_id")  
	@ApiModelProperty(value="Contains bookId")
	private Integer bookId; 

	@ApiModelProperty(value="Contains avgRating")
	@Column(name="avg_rating")  
	private double avgRating; 

	@ApiModelProperty(value="Contains numberOfSearches")
	@Column(name="number_of_searches")  
	private int numberOfSearches;

	public BookRating() {}
	public BookRating(Integer bookId, double avgRating, int numberOfSearches) {
		super();
		this.bookId = bookId;
		this.avgRating = avgRating;
		this.numberOfSearches = numberOfSearches;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}

	public int getNumberOfSearches() {
		return numberOfSearches;
	}

	public void setNumberOfSearches(int numberOfSearches) {
		this.numberOfSearches = numberOfSearches;
	}
	@Override
	public String toString() {
		return bookId + "," + avgRating + "," + numberOfSearches;
	} 

}
