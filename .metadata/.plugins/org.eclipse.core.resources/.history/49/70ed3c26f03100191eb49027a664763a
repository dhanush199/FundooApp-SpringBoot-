package com.bridgelabz.fundoonote.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonote.userdao.UserRepository;
import com.bridgelabz.fundoonote.usermodel.User;
import com.bridgelabz.fundoonote.userutil.EmailUtil;

@Service
public class UserServiceImpl implements UserServiceInf {
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EmailUtil emailService;
	
	public User register(User user) {	
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		emailService.sendEmail("dhanushsh1995@gmail.com", "Activate User", "Click here");
		return userrepository.save(user);
		
	}

}
