package com.satish.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity 
@Table(name="mybooks",schema = "jlcbooksdb") 
@ApiModel(value="Book contains bookId,bookName,author,publications,category") 
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(value="Contains bookId")
	@Column(name="book_id") 
	private Integer bookId;
	
	@ApiModelProperty(value="Contains bookName")
	@Column(name="book_name") 
	private String bookName;
	
	@ApiModelProperty(value="Contains author")
	@Column(name="author") 
	private String author;
	
	@ApiModelProperty(value="Contains publications")
	@Column(name="publications") 
	private String publications;
	
	@ApiModelProperty(value="Contains category")
	@Column(name="category") 
	private String category;
	
	public Book() {}
	public Book(String bookName, String author, String publications, String category) {
		this.bookName = bookName;
		this.author = author;
		this.publications = publications;
		this.category = category;
	}
	public Book(Integer bookId, String bookName, String author, String publications, String category) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.publications = publications;
		this.category = category;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublications() {
		return publications;
	}
	public void setPublications(String publications) {
		this.publications = publications;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return  bookId + "," + bookName + "," + author + ","+ publications + "," + category;
	}
	
}
