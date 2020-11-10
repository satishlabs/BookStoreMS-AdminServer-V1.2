package com.bookstoreweb.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookstoreweb.config.BookStoreWebConfig;
import com.bookstoreweb.dto.Book;
import com.bookstoreweb.dto.BookInfo;
import com.bookstoreweb.dto.UserRating;
import com.bookstoreweb.feign.BookPriceProxy;
import com.bookstoreweb.feign.BookSearchProxy;
import com.bookstoreweb.feign.PlaceOrderProxy;
import com.bookstoreweb.feign.UserRatingProxy;
import com.satish.rabbitmq.Order;
import com.satish.rabbitmq.OrderInfo;
import com.satish.rabbitmq.OrderItem;
import com.satish.rabbitmq.UserRatingInfo;

@Service
public class BookStoreServiceImpl implements BookStoreService {
	static Logger log = LoggerFactory.getLogger(BookStoreServiceImpl.class);

	Map<Integer, Book> booksMap = new LinkedHashMap<>();

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private BookPriceProxy bookPriceProxy;
	
	@Autowired
	private BookSearchProxy bookSearchProxy;

	@Autowired
	private PlaceOrderProxy placeOrderProxy;
	
	@Autowired
	private UserRatingProxy userRatingProxy;
	
	public List<String> getAuthorsList() {
		List<String> authorsList = new ArrayList<>();
		authorsList.add("All Authors");
		authorsList.add("Srinivas");
		authorsList.add("Vas");
		authorsList.add("Sri");
		authorsList.add("Satish");
		return authorsList;
	}

	public List<String> getCategoryList() {
		List<String> catList = new ArrayList<>();
		catList.add("All Categories");
		catList.add("Web");
		catList.add("Spring");
		return catList;
	}

	@Override
	public List<Book> getMyBooks(String author, String category) {
		System.out.println("BookStoreServiceImpl - getBooks()");
		if (author == null || author.length() == 0) {
			author = "All Authors";
		}
		if (category == null || category.length() == 0) {
			category = "All Categories";
		}

		// Invoke BookSearchMS
		//RestTemplate bookSearchRest = new RestTemplate();
		//String endpoint = "http://localhost:8000/mybooks/" + author + "/" + category;

		//List<Map> list = bookSearchRest.getForObject(endpoint, List.class);
		
		
		List<Book> bookList = bookSearchProxy.getBooks(author, category);

	/*	for (Book mybook : list) {
			Book mybook = convertMapToBook(mymap);
			bookList.add(mybook);
			booksMap.put(mybook.getBookId(), mybook);

		}*/
		for(Book mybook: bookList) {
			booksMap.put(mybook.getBookId(), mybook);
		}
		return bookList;
	}

	@Override
	public BookInfo getBookInfoByBookId(Integer bookId) {
		System.out.println("BookStoreServiceImpl - getBookInfoByBookId()");
		//RestTemplate bookInfoRest = new RestTemplate();

		//String endpoint = "http://localhost:8000/mybook/" + bookId;
		//BookInfo bookInfo = bookInfoRest.getForObject(endpoint, BookInfo.class);
		BookInfo bookInfo = bookSearchProxy.getBookById(bookId);
		return bookInfo;
	}

	@Override
	public Book getBookByBookId(Integer bookId) {
		System.out.println("BookStoreServiceImpl - getBookByBookId()");
		System.out.println(bookId);
		Book mybook = booksMap.get(bookId);
		return mybook;
	}

	public void placeOrder(Map<Integer, Book> mycartMap) {
		System.out.println("--2.BookStoreServiceImpl - placeOrder()--");
		List<OrderItem> itemList = new ArrayList<>();
		double totalPrice = 0.0;
		int totalQuantity = 0;

		for (Book mybook : mycartMap.values()) {
			Integer bookId = mybook.getBookId();

			// Invoke BookPrice Controller
			//RestTemplate bookPriceRest = new RestTemplate();
			//String priceEndpoint = "http://localhost:9000/offeredPrice/" + bookId;
			
			
			//double offerPrice = bookPriceRest.getForObject(priceEndpoint, Double.class);
			
			double offerPrice = bookPriceProxy.getOfferedPrice(bookId);
			OrderItem item = new OrderItem(0, bookId, 1, offerPrice);
			itemList.add(item);

			totalPrice = totalPrice + offerPrice;
			totalQuantity = totalQuantity + 1;
		}
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy  hh:mm");
		String orderDate = formatter.format(today);
		System.out.println(orderDate);
		Order order = new Order(orderDate, "U-111", totalQuantity, totalPrice, "NEW");

		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrder(order);
		orderInfo.setItemsList(itemList);
		System.out.println(orderInfo);

		// Send Order Message to RabbitMQ
		rabbitTemplate.convertAndSend(BookStoreWebConfig.ORDER_QUEUE, orderInfo);
		System.out.println("Order Placed...");
	}


	public void addUserRating(UserRating userRating) {
		System.out.println("---2. BookStoreServiceImpl -- addUserRating()");
		UserRatingInfo userRatingInfo = new UserRatingInfo(userRating.getUserId(), userRating.getBookId(), userRating.getRating(),userRating.getReview());
		rabbitTemplate.convertAndSend(BookStoreWebConfig.USER_RATING_QUEUE, userRatingInfo);
		System.out.println("Rating Added...");

	}

	@Override
	public List<UserRating> getMyRatings(String userId) {
		List<UserRating> ratingList = userRatingProxy.getUserRatingByUserId(userId);
			
		/*
		String ratingEndpoint = "http://localhost:6500/userRatings/" + userId;
		RestTemplate ratingRest = new RestTemplate();

		List<Map> mymap = ratingRest.getForObject(ratingEndpoint, List.class);
		for (Map map : mymap) {
			UserRating userRating = convertMapToUserRating(map);
			ratingList.add(userRating);
			System.out.println(map);
		}*/
		return ratingList;
	}
/*
	private UserRating convertMapToUserRating(Map map) {
		UserRating rating = new UserRating();
		rating.setRatingId(new Integer(map.get("ratingId").toString()));
		rating.setUserId(map.get("userId").toString());
		rating.setBookId(new Integer(map.get("bookId").toString()));
		rating.setRating(new Double(map.get("rating").toString()));
		rating.setReview(map.get("review").toString());
		return rating;
	}
*
*/
	/*
	private Book convertMapToBook(Map map) {
		Book mybook = new Book();
		mybook.setBookId(Integer.parseInt(map.get("bookId").toString()));
		mybook.setBookName((map.get("bookName").toString()));
		mybook.setAuthor((map.get("author").toString()));
		mybook.setPublications(map.get("publications").toString());
		mybook.setCategory(map.get("category").toString());

		return mybook;
	}
*/
}
