package com.satish.rabbitmq;

import java.io.Serializable;

public class BookInventoryInfo implements Serializable {
	private Integer bookId;
	private int booksAvailable;
	
	public BookInventoryInfo() {}
	public BookInventoryInfo(Integer bookId, int booksAvailable) {
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
		return "BookInventoryInfo [bookId=" + bookId + ", booksAvailable=" + booksAvailable + "]";
	}

}
