package com.mouad.Ressources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RessourcesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RessourcesApplication.class, args);
	}

}
