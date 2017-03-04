package com.shop.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.context.WebApplicationContext;

import com.shop.models.Client;
import com.shop.service.ClientService;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginValidator implements Validator {

	@Autowired
	ClientService clientService;
	
	private static final String ERROR_LOGIN_NOTUNIQUE = "login.error.notUnique";
	private static final String ERROR_LOGIN_STHWRONG = "login.error.sthwrong";
	
	@Override
	public boolean supports(Class<?> arg0) {
		return Client.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Client client = (Client) target;
		try{
			if(!clientService.isLoginUnique(client.getLogin())){
				errors.rejectValue("login", ERROR_LOGIN_NOTUNIQUE);
			}
		}catch (Exception e){
			errors.rejectValue("login", ERROR_LOGIN_STHWRONG);
		}
	}

}
