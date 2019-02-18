package com.bridgelabz.fundoonote.usercontroller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonote.usermodel.User;
import com.bridgelabz.fundoonote.userservice.UserServiceInf;

@RestController
@RequestMapping("/user")
public class UserController {

	static Logger logger = Logger.getLogger(UserController.class.getName());

	@Autowired
	private UserServiceInf userService;

	@PostMapping("/registeruser")
	public ResponseEntity<?> registerUser( @RequestBody User user, HttpServletRequest request,HttpServletResponse resp) {
		try {
		if (userService.register(user,request,resp)!=null)
			return new ResponseEntity<String>("Successfully Updated",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Please enter the valid details",HttpStatus.CONFLICT);
		}catch( Exception ex ) {
		    logger.log( Level.SEVERE, ex.toString(), ex );
			return new ResponseEntity<String>("Please enter the valid details",HttpStatus.CONFLICT);

		}
	}

	@GetMapping(value="/userverification/{token:.+}")
	public ResponseEntity<?> activateUser(@PathVariable("token") String token, HttpServletRequest request) {
		User registeredUser = userService.activateUser(token, request);
		if (registeredUser != null) {
			return new ResponseEntity<User>(registeredUser, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Email incorrect. Please enter valid email address present in database",
					HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/loginuser")
	public ResponseEntity<?> loginUser( @RequestBody User user, HttpServletRequest request,HttpServletResponse resp) {
		if (userService.loginUser(user, request, resp)!=null)
			return new ResponseEntity<String>("Successfully logged in",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Please enter the valid details or please activate your account from your emailId",HttpStatus.CONFLICT);
	}

	@PostMapping("/updateuser")
	public ResponseEntity<?> updateUser( @RequestBody User user, HttpServletRequest request,HttpServletResponse resp) {
		if (userService.updateUser(user, request, resp)!=null)
			return new ResponseEntity<String>("Successfully updated",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Please enter the valid details or please activate your account from your emailId",HttpStatus.CONFLICT);
	}
	@PostMapping("/forgotpassword")
	public ResponseEntity<?> forgotPassword(@RequestHeader("token") String token,@RequestBody User user, HttpServletRequest request,HttpServletResponse resp) {
		System.out.println("we are herwe");
		User existingUser = userService.getUserByEmail(token, request, user, resp);
		if (existingUser != null) {
			return new ResponseEntity<User>(user, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Email incorrect. Please enter valid email address present in database",
					HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/resetpassword/{token:.+}")
	public ResponseEntity<?> resetPassword(@PathVariable("token") String token,@RequestBody User user, HttpServletRequest request,HttpServletResponse resp) {
		System.out.println("we are hereeee");
		User existingUser = userService.resetPassword(user.getEmailId(),request,user);
		if (existingUser != null) {
			return new ResponseEntity<User>(user, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Email incorrect. Please enter valid email address present in database",
					HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/deleteuser")
	public ResponseEntity<?> deleteUser(@RequestHeader("token") String token, HttpServletRequest request,HttpServletResponse resp) {
		userService.deleteUser(token);

		return new ResponseEntity<String>("Successfully deleted", HttpStatus.FOUND);

	}

//	try {
//	} catch( Exception ex ) {
//	    logger.log( Level.SEVERE, ex.toString(), ex );
//	}
}
