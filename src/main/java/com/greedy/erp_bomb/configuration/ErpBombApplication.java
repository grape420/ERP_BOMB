package com.greedy.erp_bomb.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.greedy.erp_bomb")
@EntityScan("com.greedy.erp_bomb")
public class ErpBombApplication {
	public static void main(String[] args) {
		SpringApplication.run(ErpBombApplication.class, args);
	}
}
