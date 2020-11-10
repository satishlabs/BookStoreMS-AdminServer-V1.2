package com.satish.config;

import org.springframework.amqp.core.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class BookSearchConfig implements WebMvcConfigurer{
	public static final String RATINGS_QUEUE= "MyRatings-Queue";
	public static final String RATINGS_EXCHANGE= "MyRatings-Exchange"; 
	
	public static final String INVENTORY_QUEUE= "MyInventory-Queue";
	public static final String INVENTORY_EXCHANGE= "MyInventory-Exchange"; 

	private ApiInfo getMyApiInfo() {
		return new ApiInfo( "BookSearchMS" , "Book Search Microserices",
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
	
	//RabbitMQ for Rating
	@Bean(name = "myRatingsQueue")
	Queue  createRatingsQueue() {
		return QueueBuilder.durable(RATINGS_QUEUE).build();
	}
	
	@Bean(name = "myRatingsExchange")
	Exchange createRatingsExchange() {
		return ExchangeBuilder.topicExchange(RATINGS_EXCHANGE).build();
	}
	
	@Bean
	Binding ratingBinding(Queue myRatingsQueue, TopicExchange myRatingsExchange) {
		return BindingBuilder.bind(myRatingsQueue).to(myRatingsExchange).with(RATINGS_QUEUE);
	}
	
	//RabbitMQ for Inventory
	@Bean(name = "myInventoryQueue")
	Queue createInventoryQueue() {
		return QueueBuilder.durable(INVENTORY_QUEUE).build();
	}
	
	@Bean(name = "myInventoryExchange")
	Exchange createInventoryExchange() {
		return ExchangeBuilder.topicExchange(INVENTORY_EXCHANGE).build();
	}
	
	@Bean
	Binding inventoryBinding(Queue myInventoryQueue, TopicExchange myInventoryExchange) {
		return BindingBuilder.bind(myInventoryQueue).to(myInventoryExchange).with(INVENTORY_QUEUE);
	}
}
