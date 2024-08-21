package com.dev.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApiApplication {

	public static void main(String[] args) {

		// Set up the Database in Postgres
		try{
			DatabaseInitializer.initializeDatabase();
			System.out.println("Successfully initialised database.");
		}
		catch (Exception e) {
			System.out.println("Failed to initialise database, throwing exception...");
            throw new RuntimeException(e.getMessage());
        }

		// Start Spring Application
		SpringApplication.run(RestApiApplication.class, args);
	}
}