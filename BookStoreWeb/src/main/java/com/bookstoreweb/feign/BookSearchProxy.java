package com.bookstoreweb.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bookstoreweb.dto.Book;
import com.bookstoreweb.dto.BookInfo;



@FeignClient(value="MyBookSearchMS",url = "http://localhost:8000")
public interface BookSearchProxy {
	
	@GetMapping("/mybooks/{author}/{category}")
	public List<Book> getBooks(@PathVariable String author, @PathVariable String category);
	
	@GetMapping("/mybook/{bookId}")
	public BookInfo getBookById(@PathVariable Integer bookId) ;
}
