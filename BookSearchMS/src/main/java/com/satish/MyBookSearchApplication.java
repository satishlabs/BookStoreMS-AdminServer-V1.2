package com.satish;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
@EnableSwagger2 
@EnableFeignClients
public class MyBookSearchApplication implements CommandLineRunner{

	
	public static void main(String[] args) {
		System.out.println(" BookSearch - Begin ");
		SpringApplication.run(MyBookSearchApplication.class, args);
		System.out.println(" BookSearch - End "); 
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(" BookSearch - Launched.... "); 
	}

}
