package com.e_commerce.product_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("\n----------------------------------------------------------");
		System.out.println("  Product Service is running!");
		System.out.println("  Swagger UI: http://localhost:8082/swagger-ui.html");
		System.out.println("  API Docs:   http://localhost:8082/v3/api-docs");
		System.out.println("----------------------------------------------------------\n");
	}
}
