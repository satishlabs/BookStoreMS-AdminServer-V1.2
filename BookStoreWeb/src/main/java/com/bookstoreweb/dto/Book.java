	package com.bookstoreweb.dto;
	
	public class Book {
		private Integer bookId; 
		private String bookName;  
		private String author;  
		private String publications;  
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
			return "Book [bookId=" + bookId + ", bookName=" + bookName + ", author=" + author + ", publications="
					+ publications + ", category=" + category + "]";
		} 
		
		
	}
