package com.satish.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satish.dao.BookPriceDAO;
import com.satish.entity.BookPrice;

@Service
@Transactional
public class BookPriceServiceImpl implements BookPriceService{
	
	@Autowired
	private BookPriceDAO bookPriceDAO;
	@Override
	public BookPrice getBookPriceById(Integer bookId) {
		System.out.println("---BookPriceServiceImpl---getBookPriceById()-----"); 
		BookPrice bookPrice = null;
		Optional<BookPrice> opt = bookPriceDAO.findById(bookId);
		if(opt.isPresent()) {
			bookPrice = opt.get();
		}
		return bookPrice;
	}

	@Override
	public double getOfferedPriceById(Integer bookId) {
		System.out.println("---BookPriceServiceImpl---getOfferedPriceById()-----"); 
		
		double offerPrice = 0.0;
		Optional<BookPrice> opt = bookPriceDAO.findById(bookId);
		if(opt.isPresent()) {
			BookPrice bookPrice = opt.get();
			double price = bookPrice.getPrice();
			double offer = bookPrice.getOffer();
			
			if(offer <= 0) {
				return price;
			}
			offerPrice = price-price*offer/100;
		}
		return offerPrice;
	}

}
