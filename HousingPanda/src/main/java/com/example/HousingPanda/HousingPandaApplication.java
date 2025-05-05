package com.example.HousingPanda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HousingPandaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HousingPandaApplication.class, args);
		/*
		Approach:
		Will need 2 tables in the database
		 1. Table 1 stores user info after they have registered
		 2. Table 2 store listings info and uses user_id from table 1 as a foreign key to establish a link
		- One user can have many Listings - One-Many relation
		Post Request to create a User and a new listing for a user
		Get Request to get all entries in user and listing database
		 */
	}

}
