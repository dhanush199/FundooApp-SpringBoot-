package com.bridgelabz.fundoonote.userutil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonote.usermodel.User;

@Service
@Component
public class UserCacheManagerImpl implements UserCacheManagerInf{


	public static final String TABLE = "user";
	public static final String TABLE_USER = "Table"+""+TABLE;

	//	public static final String EMAIL_ID = "emailId";

	private RedisUtil<User> redisUtilUser;

	@Autowired
	public UserCacheManagerImpl(RedisUtil<User> redisUtilStudent) {

		this.redisUtilUser = redisUtilStudent;

	}

	public void cacheUserDetails(User user){
		redisUtilUser.putMap(TABLE_USER,TABLE+user.getId(),user);
		//redisUtilUser.putMap(TABLE_USER,user.getEmailId(),user);
		System.out.println(user);

		//        redisUtilStudent.setExpire(TABLE_STUDENT,1,TimeUnit.DAYS);

	}

	public User getUserFormCache(int userId){
		System.out.println("userId= "+userId);
		return redisUtilUser.getValue("Tableuser"+String.valueOf(141));

		//        redisUtilStudent.setExpire(TABLE_STUDENT,1,TimeUnit.DAYS);

	}
	public User getUserByEmailId(String emailId) {
		return redisUtilUser.getValue(emailId);

	}


}