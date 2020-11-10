package com.satish.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.satish.dto.BookPriceInfo;


@FeignClient(value="MyBookPriceMS",url="http://localhost:9000") 
public interface BookPriceProxy {
	
	@GetMapping("/bookPrice/{bookId}")
	public BookPriceInfo getBookPrice(@PathVariable Integer bookId) ;
	
	
	@GetMapping("/offeredPrice/{bookId}")
	public double getOfferedPrice(@PathVariable Integer bookId) ;
}
