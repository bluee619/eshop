package com.shop.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.context.WebApplicationContext;

import com.shop.models.Item;
import com.shop.service.ItemService;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ItemNameValidator implements Validator {
	
	@Autowired
	ItemService itemService;
	
	private static final String ERROR_NAME_NOTUNIQUE = "name.error.notUnique";
	private static final String ERROR_NAME_STHWRONG = "name.error.sthwrong";
	
	@Override
	public boolean supports(Class<?> arg0) {
		return Item.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Item item = (Item) target;
		try{
			if(!itemService.isNameUnique(item.getName())){
				errors.rejectValue("name", ERROR_NAME_NOTUNIQUE);
			}
		
		}catch (Exception e){
			errors.rejectValue("name", ERROR_NAME_STHWRONG);
		}
	}
}
