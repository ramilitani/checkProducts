package com.exercise.dott.checkproducts;

import com.exercise.dott.checkproducts.services.CheckProductsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CheckProductsApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(CheckProductsApplication.class, args);
		CheckProductsService service = applicationContext.getBean(CheckProductsService.class);

		System.out.println("***********************CHECKING ORDERS**************************");
		service.checkOrders(args);
	}
}
