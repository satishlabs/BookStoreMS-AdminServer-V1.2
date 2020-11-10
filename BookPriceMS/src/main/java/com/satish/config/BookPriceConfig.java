package com.satish.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class BookPriceConfig {
	
	private ApiInfo getMyApiInfo() {
		return new ApiInfo( "BookPriceMS" , "Book Price Microservices",
				"1.9","Free to use for 10 times",
				new springfox.documentation.service.Contact("Satish Prasad","https://www.coursecube.com","sd@coursecube.com"),
				"API Under Free Licence","https://www.coursecube.com" );
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.build( )
				.apiInfo(getMyApiInfo() );
	}
	
	
}
