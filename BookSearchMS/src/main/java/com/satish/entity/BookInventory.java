package com.satish.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="mybookinventory",schema = "jlcbooksdb") 
@ApiModel(value="BookInventory contains bookId,booksAvailable") 
public class BookInventory {
	@Id  
	@Column(name="book_id")  
	@ApiModelProperty(value="Contains bookId")
	private Integer bookId; 
	
	@Column(name="books_available")  
	@ApiModelProperty(value="Contains booksAvailable")
	private int booksAvailable;
	
	public BookInventory() {}
	public BookInventory(Integer bookId, int booksAvailable) {
		super();
		this.bookId = bookId;
		this.booksAvailable = booksAvailable;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public int getBooksAvailable() {
		return booksAvailable;
	}

	public void setBooksAvailable(int booksAvailable) {
		this.booksAvailable = booksAvailable;
	}
	@Override
	public String toString() {
		return bookId + "," + booksAvailable;
	} 
	 
	 
}
