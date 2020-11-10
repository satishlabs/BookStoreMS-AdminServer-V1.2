package com.satish.service;

import com.satish.entity.BookPrice;

public interface BookPriceService {
	public BookPrice getBookPriceById(Integer bookId);
	public double getOfferedPriceById(Integer bookId);
}
