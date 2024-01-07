package com.example.CuoikiLTM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CuoikiLtmApplication {
	public static void main(String[] args) {
		SpringApplication.run(CuoikiLtmApplication.class, args);
	}
}
