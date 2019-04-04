package com.bridgelabz.fundoonote.userservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonote.userdao.UserRepository;
import com.bridgelabz.fundoonote.usermodel.User;
import com.bridgelabz.fundoonote.userutil.UserCacheManagerInf;

@Service
public class UserRadis implements UserRedisInf{
	@Autowired
    private UserCacheManagerInf userCacheManager;

//	@Autowired
//    private UserRepository userRepository;
//	
    public void cacheDetails(boolean checkFlag)  {

//        if(userCacheManager.checkEmpty()) {// If cache is empty the put the data

//          List<User> users= userRepository.findAll();
//          System.out.println(users);

//          users.forEach(user->userCacheManager.cacheUserDetails(user));

      //  }

    }
    public User singleUser(int id) {
    	return userCacheManager.getUserFormCache(id);
    }
    
    public User userByEmailId(String emailId) {
		return userCacheManager.getUserByEmailId(emailId);
    	
    }
}
