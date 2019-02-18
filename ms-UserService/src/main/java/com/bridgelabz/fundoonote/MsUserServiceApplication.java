package com.bridgelabz.fundoonote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.bridgelabz.fundoonote")
@SpringBootApplication
public class MsUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsUserServiceApplication.class, args);
	}

}

