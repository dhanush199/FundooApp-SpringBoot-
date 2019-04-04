package com.bridgelabz.fundoonote.userservice;

import com.bridgelabz.fundoonote.usermodel.User;

public interface UserRedisInf {

	 void cacheDetails(boolean checkFlag);
	 
	 User singleUser(int id);
	 
	 User userByEmailId(String emailId);
}
