//package com.bridgelabz.fundoonote.userservice;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import com.bridgelabz.fundoonote.usermodel.User;
//
//@Service
//public class SecurityServiceImpl implements SecurityServiceInf{
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserServiceInf userDetailsService;
//
//    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
//
//    
//    public String findLoggedInUsername() {
//        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
//        if (userDetails instanceof User) {
//            return ((User)userDetails).getName();
//        }
//
//        return null;
//    }
////    public void autologin(String username, String password) {
////        User userDetails = (User) userDetailsService.loadUserByUsername(username);
////        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, ((Authentication) userDetails).getAuthorities());
////        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
////        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
////            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
////            logger.debug(String.format("Auto login %s successfully!", username));
////        }
////    }
//
//
//	@Override
//	public void autologin(String username, String password) {
//		// TODO Auto-generated method stub
//		
//	}
//}
