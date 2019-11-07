package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Many Spring Boot developers always have their main class annotated with 
 * @Configuration, @EnableAutoConfiguration and @ComponentScan. Since these 
 * annotations are so frequently used together (especially if you follow 
 * the best practices above), Spring Boot provides a convenient 
 * @SpringBootApplication alternative.
 */
@SpringBootApplication
public class PayrollApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayrollApplication.class, args);
	}

}