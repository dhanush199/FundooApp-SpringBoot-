package com.bridgelabz.fundoonote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableCaching 
@EnableAutoConfiguration(exclude = ElasticsearchAutoConfiguration.class)
@ComponentScan("com.bridgelabz.fundoonote")
@SpringBootApplication
//@EnableJpaRepositories(basePackages = {"com.bridgelabz.fundoonote.userDao"})
//@EntityScan(basePackages = "com.bridgelabz.fundoonote.usermodel")
//@EnableTransactionManagement
//@EnableScheduling
public class MsUserServiceApplication{	
	public static void main(String[] args) {
		SpringApplication.run(MsUserServiceApplication.class, args);
	}	
}

