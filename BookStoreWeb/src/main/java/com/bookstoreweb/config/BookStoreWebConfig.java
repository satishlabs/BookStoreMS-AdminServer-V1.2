package com.bookstoreweb.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class BookStoreWebConfig implements WebMvcConfigurer {
	public static final String ORDER_QUEUE = "MyOrder-Queue";
	public static final String ORDER_EXCHANGE = "MyOrder-Exchange";

	public static final String USER_RATING_QUEUE = "MyUserRating-Queue";
	public static final String USER_RATING_EXCHANGE = "MyUserRating-Exchange";

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/myjsps/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		// registry.addResourceHandler("/mycss/**")
		// .addResourceLocations("classpath:/META-INF/resources/mycss/");
	}

	// Swagger
	private ApiInfo getMyApiInfo() {
		return new ApiInfo("PlaceOrderMS", "Place Order Microserices", "1.9", "Free to use for 10 times",
				new springfox.documentation.service.Contact("Satish Prasad", "https://www.coursecube.com",
						"sd@coursecube.com"),
				"API Under Free Licence", "https://www.coursecube.com");
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.any())
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot"))).build()
				.apiInfo(getMyApiInfo());
	}

	// RabbitMQ for Order
	@Bean(name = "myOrderQueue")
	Queue createOrderQueue() {
		return QueueBuilder.durable(ORDER_QUEUE).build();
	}

	@Bean(name = "myOrderExchange")
	Exchange createOrderExchange() {
		return ExchangeBuilder.topicExchange(ORDER_EXCHANGE).build();
	}

	@Bean
	Binding orderBinding(Queue myOrderQueue, TopicExchange myOrderExchange) {
		return BindingBuilder.bind(myOrderQueue).to(myOrderExchange).with(ORDER_QUEUE);
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
