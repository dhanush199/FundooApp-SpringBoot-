package com.bridgelabz.fundoonote.usercontroller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.fundoonote.userdao.UserRepository;
import com.bridgelabz.fundoonote.usermodel.User;
import com.bridgelabz.fundoonote.userservice.UserRadis;
import com.bridgelabz.fundoonote.userservice.UserRedisInf;
import com.bridgelabz.fundoonote.userservice.UserServiceInf;
import com.bridgelabz.fundoonote.userutil.TokenGeneratorInf;

@RestController
@RequestMapping("/test")
public class TestController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRedisInf userRedisInf;

	@Autowired
	private TokenGeneratorInf tokenGeneratorInf;

//	@Cacheable(value = "user", key = "#userId", unless = "#result.followers < 12000")
	@GetMapping("/get-user/{token:.+}")
	public ResponseEntity<?> getUser(@PathVariable ("token")String token, HttpServletRequest request, HttpServletResponse resp) throws Exception {
		if(token!=null) {
			userRedisInf.cacheDetails(true);
			LOG.info("Getting user with ID {}.", tokenGeneratorInf.authenticateToken(token));
			return new ResponseEntity<String>("Done",HttpStatus.OK);
		}
		else 
			return new ResponseEntity<String>("Went wrong",HttpStatus.CONFLICT);
	}
	
	@GetMapping("/retrive-user/{token:.+}")
	public ResponseEntity<?> retriveUser(@PathVariable ("token")String token, HttpServletRequest request, HttpServletResponse resp) throws Exception {
			User user=userRedisInf.singleUser(tokenGeneratorInf.authenticateToken(token));
			if(user!=null) {
			System.out.println(user);
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		else 
			return new ResponseEntity<String>("Went wrong",HttpStatus.CONFLICT);
	}

}
