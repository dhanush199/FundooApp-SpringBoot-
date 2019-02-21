package com.bridgelabz.fundoonote.userservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridgelabz.fundoonote.usermodel.User;

public interface UserServiceInf {
	
	User register(User user,HttpServletRequest request,HttpServletResponse resp);
	User activateUser(String token, HttpServletRequest request);
	String loginUser(User user,HttpServletRequest req,HttpServletResponse resp);
	User updateUser(User user,HttpServletRequest req,HttpServletResponse resp);
	User getUserByEmail( String userToken,HttpServletRequest request,User user,HttpServletResponse resp);
	User resetPassword(String emailID, HttpServletRequest request,User newPassword);
	void deleteUser(String token);
	//User loadUserByUsername(String username);
}
