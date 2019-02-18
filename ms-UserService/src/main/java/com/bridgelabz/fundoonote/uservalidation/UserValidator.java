package com.bridgelabz.fundoonote.uservalidation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bridgelabz.fundoonote.usermodel.User;

@Component
public class UserValidator implements Validator{

	final String REGEX_USERNAME="[a-zA-Z ]*$";
	final String REGEX_EMAILID="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
	final String REGEX_MOBILENUMBER="^[0-9]{10}$";
	final String REGEX_PASSWORD="^.*(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])[a-zA-Z0-9@#$%^&+=]*$";

	public boolean supports(Class<?> User) {
		return User.class.equals(User);
	}

	public void validate(Object target, Errors errors) {

		User user = (User) target;
		if(!(user.getName().matches(REGEX_USERNAME)))
		{
			System.out.println("Name is incorrect");
			errors.rejectValue("name", "symbolsPresent",new Object[]{"'name'"},"name can't be symbols");
		}
		if(!(user.getEmailId().matches(REGEX_EMAILID)))
		{
			System.out.println("Email is incorrect");
			errors.rejectValue("emailId", "symbolsPresent",new Object[]{"'emailId'"},"please provide proper emailID eg:abc@xyz.com");
		}
		String mobileNum=String.valueOf(user.getMobileNumber());
		if(!(mobileNum.matches(REGEX_MOBILENUMBER)))
		{
			System.out.println("CellNum is incorrect");
			errors.rejectValue("mobileNumber", "symbolsPresent",new Object[]{"'mobileNumber'"},"please provide 10 digit mobileNumber");
		}
		if(!(user.getPassword().matches(REGEX_PASSWORD)))
		{
			System.out.println("Password incorrect");
			errors.rejectValue("password", "symbolsPresent",new Object[]{"'password'"},"password should have at lease one upperCase and one Lower case letter with one special symbol in it");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
		ValidationUtils.rejectIfEmpty(errors, "emailId","emailId.required");
		ValidationUtils.rejectIfEmpty(errors, "mobileNumber","mobileNumber.required");

	}
}