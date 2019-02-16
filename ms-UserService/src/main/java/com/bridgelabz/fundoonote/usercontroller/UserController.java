package com.bridgelabz.fundoonote.usercontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonote.usermodel.User;
import com.bridgelabz.fundoonote.userservice.UserServiceInf;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceInf userService;

	@PostMapping("/registeruser")
	public ResponseEntity<?> registerUser( @RequestBody User user, HttpServletRequest request,HttpServletResponse resp) {
		if (userService.register(user)!=null)
			return new ResponseEntity<String>("Successfully Updated",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Please enter the valid details",HttpStatus.CONFLICT);
	}
}