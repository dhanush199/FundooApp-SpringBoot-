package com.bridgelabz.fundoonote.userservice;

public interface SecurityServiceInf {
	
	String findLoggedInUsername();

	void autologin(String username, String password) ;
}
