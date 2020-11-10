package com.satish.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.satish.dto.BookInfo;
import com.satish.entity.Book;
import com.satish.entity.BookInventory;
import com.satish.entity.BookRating;
import com.satish.service.BookService;
/*
BookSearchMS is calling the Rest Endpoints of BookPriceMS . So we need to provide Feign With BookSearchMS
* */
@CrossOrigin
@RestController
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/mybooks/{author}/{category}")
	public List<Book> getBooks(@PathVariable String author, @PathVariable String category){
		System.out.println(author+"\t"+category);
		return bookService.getBooks(author, category);
	}
	
	@GetMapping("/mybook/{bookId}")
	public BookInfo getBookById(@PathVariable Integer bookId) {
		System.out.println("--BookController--- getBookById()---");
		return bookService.getBookInfo(bookId);
	}
	
}
