package com.bridgelabz.fundoonote.userservice;


import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoonote.userdao.UserRepository;
import com.bridgelabz.fundoonote.usermodel.User;
import com.bridgelabz.fundoonote.userutil.EmailUtil;
import com.bridgelabz.fundoonote.userutil.TokenGeneratorInf;

@Service
public class UserServiceImpl implements UserServiceInf {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private EmailUtil emailService;

	@Autowired
	private TokenGeneratorInf tokenGenerator;

	public User register(User user, HttpServletRequest request, HttpServletResponse resp) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		if (userRepository.save(user) != null) {
			System.out.println("User id is " + user.getId());
			String id = String.valueOf(user.getId());
			String token = tokenGenerator.generateToken(id);
			resp.setHeader("token", token);
			String verificationUrl = tokenGenerator.generateUrl("/userverification/", user, request, resp);
			emailService.sendEmail("dhanushsh1995@gmail.com", "Activate User", verificationUrl);
			return user;
		} else
			return null;
	}

	public User activateUser(String token, HttpServletRequest request) {
		int id = tokenGenerator.authenticateToken(token);
		Optional<User> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			User newUser = optional.get();
			newUser.setActivationStatus(true);
			userRepository.save(newUser);
			return newUser;
		}
		return null;
	}

	public String loginUser(User user, HttpServletRequest req, HttpServletResponse resp) {
		User existingUser = userRepository.findUserByEmailId(user.getEmailId());
		if (existingUser != null && bCryptPasswordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
			if (existingUser.isActivationStatus() == true) {
				System.out.println("User detail is=" + existingUser.getId() + "," + existingUser.getName() + ","
						+ existingUser.getEmailId() + "," + existingUser.getMobileNumber());
				String token = tokenGenerator.generateToken(String.valueOf(existingUser.getId()));
				return token;
			} else {
				String verificationUrl=tokenGenerator.generateUrl("/userverification/",
						existingUser, req, resp);
				emailService.sendEmail("dhanushsh1995@gmail.com", "Verification Mail", verificationUrl);
				return null;
			}
		}
		return null;
	}

	public User updateUser(User user, HttpServletRequest req, HttpServletResponse resp) {
		User exixtingUser = userRepository.findUserByEmailId(user.getEmailId());

		if (exixtingUser != null && bCryptPasswordEncoder.matches(user.getPassword(), exixtingUser.getPassword())) {
			user.setId(exixtingUser.getId());
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setActivationStatus(exixtingUser.getActivationStatus());
			userRepository.save(user);
			return exixtingUser;
		}

		return null;
	}

	public User getUserByEmail(HttpServletRequest request, User user, HttpServletResponse resp) {
		User exsistingUser = userRepository.findUserByEmailId(user.getEmailId());
		if (exsistingUser != null) {
			String PasswordResetLink = tokenGenerator.generateUrl("/resetpassword/", exsistingUser, request, resp);
			emailService.sendEmail("dhanushsh1995@gmail.com", "Password Reset Link Mail",
					"please click on this link to reset password " + PasswordResetLink);
		}
		return exsistingUser;
	}

	public String getTokenByUserId(HttpServletRequest request, User user, HttpServletResponse resp) {
		User exsistingUser = userRepository.findUserByEmailId(user.getEmailId());
		String token=null;
		if (exsistingUser != null) {
			token=tokenGenerator.generateToken(String.valueOf(exsistingUser.getId()));
			String PasswordResetLink="http://localhost:4200/reset-password/"+token;
			emailService.sendEmail("dhanushsh1995@gmail.com", "Password Reset Link Mail",
					"please click on this link to reset password " + PasswordResetLink);
			}
			return token;
		}

		public User resetPassword(String emailID, HttpServletRequest request, User user) {
			User exsistingUser = userRepository.findUserByEmailId(emailID);
			if (exsistingUser != null) {
				exsistingUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
				userRepository.save(exsistingUser);
			}
			return exsistingUser;
		}

		public void deleteUser(String token) {
			int userId = tokenGenerator.authenticateToken(token);
			userRepository.deleteById(userId);

		}

		public User loadUserByUsername(String username) {
			return null;

		}

		@Transactional
		public User saveImageFile(String token, MultipartFile file) {
			System.out.println(file);
			int userId = tokenGenerator.authenticateToken(token);
			System.out.println(userId+" My user ID");

		    User recipe = userRepository.findUserById(userId);
		     try {
		    	 byte[] bytes = file.getBytes();
		    	 System.out.println("My bytes are "+bytes);
		    	 System.out.println("My User is "+recipe);
				    String base64 = new String(Base64.encodeBase64(bytes), "ISO-8859-2");

				recipe.setImage(bytes);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     recipe= userRepository.save(recipe);
		     return recipe;
		}
		
		public User getUser(String token){
			int userId = tokenGenerator.authenticateToken(token);
			return userRepository.findUserById(userId);
		}
	}
