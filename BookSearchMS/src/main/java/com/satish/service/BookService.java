package com.satish.service;

import java.util.List;

import com.satish.dto.BookInfo;
import com.satish.entity.Book;

public interface BookService {
	public List<Book> getBooks(String author, String category);
	public BookInfo getBookInfo(Integer bookId);
}
