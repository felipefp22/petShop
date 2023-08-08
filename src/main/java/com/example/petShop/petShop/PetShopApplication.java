package com.example.petShop.petShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class PetShopApplication{

	public static void main(String[] args) {
		SpringApplication.run(PetShopApplication.class, args);
	}
}
