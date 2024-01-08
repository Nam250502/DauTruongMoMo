package com.example.CuoikiLTM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication
@EnableScheduling
public class CuoikiLtmApplication {
	public static void main(String[] args) {
		SpringApplication.run(CuoikiLtmApplication.class, args);
	}
}
