package com.bookstoreweb.service;

import java.util.List;
import java.util.Map;

import com.bookstoreweb.dto.Book;
import com.bookstoreweb.dto.BookInfo;
import com.bookstoreweb.dto.UserRating;

public interface BookStoreService {
	public List<Book> getMyBooks(String author, String category);

	public Book getBookByBookId(Integer bookId);

	public BookInfo getBookInfoByBookId(Integer bookId);

	public void placeOrder(Map<Integer, Book> mycartMap);

	public void addUserRating(UserRating userRating);

	public List<UserRating> getMyRatings(String userId);

	public List<String> getAuthorsList();

	public List<String> getCategoryList();
}
