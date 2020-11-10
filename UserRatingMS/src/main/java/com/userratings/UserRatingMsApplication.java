package com.userratings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2 
public class UserRatingMsApplication implements CommandLineRunner{
	static Logger log=LoggerFactory.getLogger(UserRatingMsApplication.class); 
	
	public static void main(String[] args) {
		log.info(" UserRatingMS  - Begin "); 
		SpringApplication.run(UserRatingMsApplication.class, args);
		log.info(" UserRatingMS  - End "); 
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(" UserRatingMS  - Launched.... "); 
		
	}

}
