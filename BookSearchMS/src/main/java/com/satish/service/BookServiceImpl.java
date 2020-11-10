package com.satish.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.satish.config.BookSearchConfig;
import com.satish.dao.BookDAO;
import com.satish.dao.BookInventoryDAO;
import com.satish.dao.BookRatingDAO;
import com.satish.dto.BookInfo;
import com.satish.dto.BookPriceInfo;
import com.satish.entity.Book;
import com.satish.entity.BookInventory;
import com.satish.entity.BookRating;
import com.satish.feign.BookPriceProxy;
import com.satish.rabbitmq.BookInventoryInfo;
import com.satish.rabbitmq.BookRatingInfo;

@Service
@Transactional
public class BookServiceImpl implements BookService{

	@Autowired
	private BookDAO bookDAO;

	@Autowired
	private BookRatingDAO bookRatingDAO;

	@Autowired
	private BookInventoryDAO bookInventoryDAO;
	
	@Autowired
	private BookPriceProxy bookPriceProxy;

	@Override
	public List<Book> getBooks(String author, String category) {
		List<Book> mybooks = new ArrayList<Book>();
		if(author.equals("All Authors") && category.equals("All Categories")) {
			mybooks = bookDAO.findAll();
		}else if(author.equals("All Authors") && !category.equals("All Categories")) {
			mybooks = bookDAO.getBooksByCategory(category);
		}else if(!author.equals("All Authors") && category.equals("All Categories")) {
			mybooks = bookDAO.getBooksByAuthor(author);
		}else {
			mybooks = bookDAO.getBooksByAuthorAndCategory(author, category);
		}
		return mybooks;
	}

	@Override
	public BookInfo getBookInfo(Integer bookId) {
		BookInfo bookInfo = new BookInfo();

		//1. Book Details
		Book book = bookDAO.findById(bookId).get(); 
		bookInfo.setBookId(book.getBookId());
		bookInfo.setBookName(book.getBookName());
		bookInfo.setAuthor(book.getAuthor());
		bookInfo.setPublications(book.getPublications());
		bookInfo.setCategory(book.getCategory());

		//2. Book Rating Details
		BookRating bookRating = bookRatingDAO.findById(bookId).get();
		bookInfo.setAvgRating(bookRating.getAvgRating());
		bookInfo.setNumberOfSearches(bookRating.getNumberOfSearches());

		//3.Book Inventory Details
		BookInventory bookInventory = bookInventoryDAO.findById(bookId).get();
		bookInfo.setBooksAvailable(bookInventory.getBooksAvailable());


		//4.Invoking BookPrice with Feign 
		BookPriceInfo bpInfo = bookPriceProxy.getBookPrice(bookId);
		bookInfo.setPrice(bpInfo.getPrice());
		bookInfo.setOffer(bpInfo.getOffer());

		return bookInfo;
	}

	@RabbitListener(queues = BookSearchConfig.RATINGS_QUEUE)
	public void updateBookRating(BookRatingInfo bookRatingInfo) {
		System.out.println("---4.BookServiceImpl --  updateBookRating()---");
		BookRating bookRating = new BookRating();
		bookRating.setBookId(bookRatingInfo.getBookId());
		bookRating.setAvgRating(bookRatingInfo.getAvgRating());
		bookRating.setNumberOfSearches(bookRatingInfo.getNumberOfSearches());
		bookRatingDAO.save(bookRating);
	}

	@RabbitListener(queues = BookSearchConfig.INVENTORY_QUEUE)
	public void updateBookInventory(BookInventoryInfo bookInventoryInfo) {
		System.out.println("---4.BookServiceImpl --  updateBookInventory()---");
		BookInventory bookInventory = new BookInventory();
		bookInventory.setBookId(bookInventoryInfo.getBookId());
		bookInventory.setBooksAvailable(bookInventoryInfo.getBooksAvailable());
		bookInventoryDAO.save(bookInventory);
	}

}
