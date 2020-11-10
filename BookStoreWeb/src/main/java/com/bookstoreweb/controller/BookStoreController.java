package com.bookstoreweb.controller;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstoreweb.dto.Book;
import com.bookstoreweb.dto.BookInfo;
import com.bookstoreweb.dto.UserRating;
import com.bookstoreweb.service.BookStoreService;

@CrossOrigin
@Controller
public class BookStoreController {
	static Logger log = LoggerFactory.getLogger(BookStoreController.class);

	@Autowired
	private BookStoreService bookStoreService;

	private Map<Integer, Book> mycartMap = new LinkedHashMap<Integer, Book>();

	@GetMapping("/")
	public String showIndexPage(Model model, HttpSession session) {
		System.out.println("** showIndexPage 1**");
		List<String> authorsList = bookStoreService.getAuthorsList();
		List<String> catList = bookStoreService.getCategoryList();
		session.setAttribute("MyAuthorList", authorsList);
		session.setAttribute("MyCatList", catList);
		System.out.println("Hello@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		return "redirect:/showAllBooks";
	}

	@GetMapping("/showAllBooks") 
	public String showBooksList(HttpServletRequest request, HttpSession session) { 
		System.out.println("-------BookStoreController--showBooksList()---------");
		String author=request.getParameter("author"); 
		String category=request.getParameter("category"); 
		System.out.println("Authorsss222"+author);
		System.out.println(category);

		Collection<Book> blist=bookStoreService.getMyBooks(author,category); 
		session.setAttribute("MyBooksList", blist);
		session.setAttribute("MyCart", mycartMap); 

		return "showBooksList";
	}

	@GetMapping("/showBookInfo")
	public String showBookFullInfo(@RequestParam("bookId") String bookId, HttpSession session,
			HttpServletRequest request) {
		System.out.println("-------BookStoreController--showBookFullInfo()---------");
		BookInfo bookInfo = bookStoreService.getBookInfoByBookId(new Integer(bookId));

		request.setAttribute("MyBookInfo", bookInfo);
		return "showBookInfo";
	}

	@PostMapping("/addToCart")
	public String addBookToCart(@RequestParam("bookId") String bookId, HttpSession session) {
		System.out.println("-------BookStoreController--addBookToCart()---------");
		System.out.println(bookId);
		Book mybook = bookStoreService.getBookByBookId(new Integer(bookId));
		mycartMap.put(new Integer(bookId), mybook);

		session.setAttribute("MyCart", mycartMap);
		return "showBooksList";
	}

	@GetMapping("/showMyCart")
	public String showBooksInMyCart(HttpServletRequest request, HttpSession session) {
		System.out.println("-------BookStoreController--showBooksInMyCart()---------");

		Object obj = session.getAttribute("MyCart");
		Map<Integer, Book> cartMap = (Map<Integer, Book>) obj;

		Collection<Book> cartBookList = (Collection<Book>) cartMap.values();
		session.setAttribute("MyCartItems", cartBookList);
		if (cartBookList.size() == 0) {
			request.setAttribute("CartEmptyMSG", "No Books Cart- Please Continue Shopping");
		}
		return "showCart";
	}

	@PostMapping("/removeFromCart")
	public String removeBookFromCart(@RequestParam("bookId") String bookId, HttpSession session) {
		System.out.println("-------BookStoreController--removeBookFromCart()---------");
		System.out.println(bookId);

		Object obj = session.getAttribute("MyCart");
		Map<Integer, Book> cartMap = (Map<Integer, Book>) obj;
		cartMap.remove(new Integer(bookId));

		return "redirect:/showMyCart";
	}

	@GetMapping("/continueShopping")
	public String continueShopping() {
		System.out.println("-------BookStoreController--continueShopping()---------");

		return "redirect:/showAllBooks";
	}

	@GetMapping("/placeOrder")
	public String placeMyOrder(HttpSession session) {
		System.out.println("-------1. BookStoreController--placeMyOrder()---------");

		if (mycartMap.size() > 0) {
			bookStoreService.placeOrder(mycartMap);
			mycartMap.clear();
		}
		return "orderSuccess";
	}

	@GetMapping("/showRatingsForm")
	public String showRatingsForm(Model model) {
		System.out.println("-------BookStoreController--showRatingsForm()---------");

		UserRating userRating = new UserRating();
		userRating.setUserId("U-12");

		model.addAttribute("myUserRating", userRating);

		return "addRating";
	}

	@PostMapping("/addMyRating")
	public String addMyRating(@ModelAttribute("") UserRating userRating) {
		System.out.println("-------1. BookStoreController--addMyRating()---------");
		
		bookStoreService.addUserRating(userRating); 
		 
		return "ratingSuccess"; 
	}
	
	@GetMapping("/showMyRatings") 
	public String showMyRatingsList(Model model,HttpSession session) { 
		System.out.println("-------BookStoreController--showMyRatingsList()---------"); 
		
		List<UserRating> userRatingList=bookStoreService.getMyRatings("U-12"); 
		 
		session.setAttribute("MyUserRatingList", userRatingList); 
		 
		return "ratingsList"; 
	}
}
