package com.example.Anywr;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Key;

@SpringBootApplication
public class AnywrApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnywrApplication.class, args);
	}

}
