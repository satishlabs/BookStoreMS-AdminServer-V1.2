package com.satish.dto;

public class BookPriceInfo {
	private Integer bookId;  
	private double price;  
	private double offer;
	
	public BookPriceInfo() {}
	public BookPriceInfo(Integer bookId, double price, double offer) {
		super();
		this.bookId = bookId;
		this.price = price;
		this.offer = offer;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
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
	@Override
	public String toString() {
		return "BookPriceInfo [bookId=" + bookId + ", price=" + price + ", offer=" + offer + "]";
	} 
	
	
}
