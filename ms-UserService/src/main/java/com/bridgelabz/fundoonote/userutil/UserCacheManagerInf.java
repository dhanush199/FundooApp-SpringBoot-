package com.bridgelabz.fundoonote.userutil;

import com.bridgelabz.fundoonote.usermodel.User;

public interface UserCacheManagerInf {

     void cacheUserDetails(User user);
     
     User getUserFormCache(int userId);
     
    User getUserByEmailId(String emailId);

}