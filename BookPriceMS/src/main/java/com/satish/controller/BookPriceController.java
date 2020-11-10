package com.satish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.satish.entity.BookPrice;
import com.satish.service.BookPriceService;

import io.swagger.annotations.ApiOperation;
/*
BooKPriceMS is not calling any other Microservices so no need to provide Feign Libraries for BookPriceMS
* */
@CrossOrigin
@RestController
public class BookPriceController {
	
	@Autowired
	private BookPriceService bookPriceService;
	
	@ApiOperation(value = "Get the Book Price Information",response = BookPrice.class,notes = "Gives Book Price the Information" )
	@GetMapping("/bookPrice/{bookId}")
	public BookPrice getBookPrice(@PathVariable Integer bookId) {
		System.out.println("---BookPriceController---getBookPrice()-----"); 
		return bookPriceService.getBookPriceById(bookId);
	}
	
	@ApiOperation(value = "Get the Book Offered Price Information",response = Double.class,notes = "Gives Book Offered Price Information" )
	@GetMapping("/offeredPrice/{bookId}")
	public double getOfferedPrice(@PathVariable Integer bookId) {
		System.out.println("---BookPriceController---getOfferedPrice()-----");
		return bookPriceService.getOfferedPriceById(bookId);
	}
}
