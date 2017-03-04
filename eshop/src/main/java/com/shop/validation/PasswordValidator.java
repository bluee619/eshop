package com.shop.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.context.WebApplicationContext;

import com.shop.models.Password;
import com.shop.service.ClientService;


@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PasswordValidator implements Validator {

	@Autowired
	ClientService clientService;
	
	@Autowired
	PasswordEncoder encoder; 
	
	private static final String ERROR_PASSWORD_REPEAT = "password.error.repeat";
	private static final String ERROR_PASSWORD_WRONGOLD = "password.error.wrongold";
	private static final String ERROR_PASSWORD_STHWRONG = "password.error.sthwrong";

	
	@Override
	public boolean supports(Class<?> arg0) {
		return Password.class.isAssignableFrom(arg0);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Password pw = (Password) target;
		try{
			if(!pw.getPassword().equals(pw.getPasswordRepeat())){
				errors.rejectValue("password", ERROR_PASSWORD_REPEAT);
			}
			else if(!encoder.matches(pw.getOldPassword(), clientService.getUserByLogin(pw.getLogin()).getPassword())){
				errors.rejectValue("oldPassword", ERROR_PASSWORD_WRONGOLD);
			}
		}catch (Exception e){
			errors.rejectValue("password", ERROR_PASSWORD_STHWRONG);
		}
	}



}
