package com.satish.dto;

public class BookInfo {
	private Integer bookId;  
	private String bookName;  
	private String author;  
	private String publications;  
	private String category;  
	private double price;  
	private double offer; 
	private double avgRating;  
	private int numberOfSearches;               
	private int booksAvailable; 
	
	public BookInfo() {}

	public BookInfo(Integer bookId, String bookName, String author, String publications, String category, double price,
			double offer, double avgRating, int numberOfSearches, int booksAvailable) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.publications = publications;
		this.category = category;
		this.price = price;
		this.offer = offer;
		this.avgRating = avgRating;
		this.numberOfSearches = numberOfSearches;
		this.booksAvailable = booksAvailable;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getOffer() {
		return offer;
	}

	public void setOffer(double offer) {
		this.offer = offer;
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

	public int getBooksAvailable() {
		return booksAvailable;
	}

	public void setBooksAvailable(int booksAvailable) {
		this.booksAvailable = booksAvailable;
	}
	
}
