package com.team03.challenge02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Challenge02Application {

	public static void main(String[] args) {
		SpringApplication.run(Challenge02Application.class, args);
	}

}
