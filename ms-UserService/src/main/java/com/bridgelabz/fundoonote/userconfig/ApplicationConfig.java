package com.bridgelabz.fundoonote.userconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
@ComponentScan("com.bridgelabz.fundoonote")
public class ApplicationConfig{
	 @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
//	 @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//	   @Override
//	   public AuthenticationManager authenticationManagerBean() throws Exception {
//	       return super.authenticationManagerBean();
//	   }
	
}
