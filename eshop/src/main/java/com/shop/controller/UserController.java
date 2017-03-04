package com.shop.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shop.models.Address;
import com.shop.models.Client;
import com.shop.models.Password;
import com.shop.service.ClientService;
import com.shop.validation.LoginValidator;
import com.shop.validation.PasswordValidator;
import com.shop.validation.RecaptchaFormValidator;

@Controller
public class UserController {

	@Autowired
	ClientService clientService;
	
	private final RecaptchaFormValidator recaptchaFormValidator;
	private final PasswordValidator passwordValidator;
	private final LoginValidator loginValidator;
	
	@ModelAttribute("recaptchaSiteKey")
	public String getRecaptchaSiteKey(@Value("${recaptcha.site-key}") String recaptchaSiteKey) {
		return recaptchaSiteKey;
	}

	@Autowired
	public UserController(RecaptchaFormValidator recaptchaFormValidator, PasswordValidator passwordValidator, LoginValidator loginValidator) {
		this.recaptchaFormValidator = recaptchaFormValidator;
		this.passwordValidator = passwordValidator;
		this.loginValidator = loginValidator;
	}

	@InitBinder("client")
	public void initBinderClient(WebDataBinder binder) {
		binder.addValidators(recaptchaFormValidator);
		binder.addValidators(loginValidator);
	}
	@InitBinder("password")
	public void initBinderPassword(WebDataBinder binder) {
		binder.addValidators(passwordValidator);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView model = new ModelAndView("reg");
		model.addObject("client", new Client());
		return model;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postRegistration(@Valid @ModelAttribute("client") Client client, BindingResult result) throws Exception{
		if (result.hasErrors()) {
			return "reg";
		}
		clientService.createAccount(client);
		return "redirect:/thanks";
	}
	
	@RequestMapping(value = "/user/{login}", method = RequestMethod.GET)
	public ModelAndView user(@PathVariable("login") String login){
		ModelAndView model = new ModelAndView("userpage");
		Client client = clientService.getUserByLogin(login);
		model.addObject("client", client);
		return model;
	}
	
	@RequestMapping(value = "/user/edit/password/{login}", method = RequestMethod.GET)
	public ModelAndView changePassword(@PathVariable("login") String login, Principal principal){
		ModelAndView model;
		if(principal.getName().equals(login))
			model = new ModelAndView("changepassword");
		else
			model = new ModelAndView("noaccess");
		
		return model;	
	}
	
	@RequestMapping(value = "/user/edit/password/{login}", method = RequestMethod.POST)
	public String changePasswordPost(@Valid @ModelAttribute("password") Password password, BindingResult result) throws Exception{
		if (result.hasErrors()) {
			return "changepassword";
		}
		clientService.changePassword(password.getLogin(), password.getPassword());
		return "redirect:/thanks";
	}
	
	@RequestMapping(value = "/user/edit/personal/address/{login}", method = RequestMethod.GET)
	public ModelAndView editPersonalAddress(@PathVariable("login") String login, Principal principal){
		ModelAndView model;
		if(principal.getName().equals(login))
			model = new ModelAndView("editpersonaladdress");
		else
			model = new ModelAndView("noaccess");
		
		return model;	
	}
	
	//syntax error in clientDAOImpl - changeAddress is commented
	//validation works
	@RequestMapping(value = "/user/edit/personal/address/{login}", method = RequestMethod.POST)
	public String changeFirstNamePost(@Valid @ModelAttribute("address") Address address, BindingResult result, Principal principal) throws Exception{
		if(result.hasErrors()) {
			return "editpersonaladdress";
		}
		clientService.changeAddress(principal.getName(), address);
		return "redirect:/thanks";
	}
	
}
