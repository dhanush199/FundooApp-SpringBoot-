package com.bridgelabz.fundoonote.userutil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridgelabz.fundoonote.usermodel.User;

public interface TokenGeneratorInf {
	
	String generateToken(String id);
	
	int authenticateToken(String token);
	
	public String generateUrl(String joinUrl,User user,HttpServletRequest req,HttpServletResponse resp);

}
