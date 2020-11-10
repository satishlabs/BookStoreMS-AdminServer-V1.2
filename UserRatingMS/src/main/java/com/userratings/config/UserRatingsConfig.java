package com.userratings.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
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
public class UserRatingsConfig implements WebMvcConfigurer{
	//BookRatings
	public static final String RATINGS_QUEUE= "MyRatings-Queue";
	public static final String RATINGS_EXCHANGE= "MyRatings-Exchange"; 
	
	//UserRatings
	public static final String USER_RATING_QUEUE = "MyUserRating-Queue";
	public static final String USER_RATING_EXCHANGE = "MyUserRating-Exchange";
	
		private ApiInfo getMyApiInfo() {
			return new ApiInfo( "UserRatingsMS" , "User Ratings Microservices",
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
		
		// RabbitMQ for UserRating
		@Bean(name = "myUserRatingQueue")
		Queue createUserRatingQueue() {
			return QueueBuilder.durable(USER_RATING_QUEUE).build();
		}

		@Bean(name = "myUserRatingExchange")
		Exchange createUserRatingExchange() {
			return ExchangeBuilder.topicExchange(USER_RATING_EXCHANGE).build();
		}

		@Bean
		Binding userRatingBinding(Queue myUserRatingQueue, TopicExchange myUserRatingExchange) {
			return BindingBuilder.bind(myUserRatingQueue).to(myUserRatingExchange).with(USER_RATING_QUEUE);
		}
}
