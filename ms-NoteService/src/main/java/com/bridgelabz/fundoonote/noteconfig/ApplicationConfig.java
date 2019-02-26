package com.bridgelabz.fundoonote.noteconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("com.bridgelabz.fundoonote")
public class ApplicationConfig{

	 @SuppressWarnings("deprecation")
		@Bean
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurerAdapter() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE")
							.allowedHeaders("token", "Content-Type").exposedHeaders("token", "Content-Type")
							.allowCredentials(false).maxAge(4000);
				}
			};
		}
}