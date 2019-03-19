package com.bridgelabz.fundoonote.userservice;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoonote.usermodel.User;

public interface UserServiceInf {
	
	User register(User user,HttpServletRequest request,HttpServletResponse resp);
	User activateUser(String token, HttpServletRequest request);
	String loginUser(User user,HttpServletRequest req,HttpServletResponse resp);
	User updateUser(User user,HttpServletRequest req,HttpServletResponse resp);
	User getUserByEmail(HttpServletRequest request,User user,HttpServletResponse resp);
	User resetPassword(String emailID, HttpServletRequest request,User newPassword);
	void deleteUser(String token);
	String getTokenByUserId(HttpServletRequest request, User user, HttpServletResponse resp);
	User saveImageFile(String token,MultipartFile image);
	User getUser(String token);
	User addCollaborator(User user,int NoteId,String token);
	List<String> getAllUser(String token);
}
