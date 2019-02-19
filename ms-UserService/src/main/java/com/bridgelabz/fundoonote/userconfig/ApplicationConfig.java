package com.bridgelabz.fundoonote.userconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bridgelabz.fundoonote.uservalidation.UserValidator;
@Configuration
@ComponentScan("com.bridgelabz.fundoonote")
public class ApplicationConfig{
	 @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
	 @Bean
	 public UserValidator getUserValidator() {
		 return new UserValidator();
	 }
//	    @Bean
//	    public WebMvcConfigurer corsConfigurer() {
//	        return new WebMvcConfigurerAdapter() {
//	            @Override
//	            public void addCorsMappings(CorsRegistry registry) {
//	                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
//	                        .allowedHeaders("*");
//	            }
//	        };
//	    }
}
