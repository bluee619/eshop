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
	private ClientService clientService;
	
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
		return new ModelAndView("reg")
				.addObject("client", new Client());
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView postRegistration(@Valid @ModelAttribute("client") Client client, BindingResult result) throws Exception{
		if (result.hasErrors()) {
			return new ModelAndView("reg");
		}
		clientService.createAccount(client);
		return new ModelAndView("redirect:/thanks");
	}
	
	@RequestMapping(value = "/user/{login}", method = RequestMethod.GET)
	public ModelAndView user(@PathVariable("login") String login){
		return new ModelAndView("userpage")
				.addObject("client", clientService.getUserByLogin(login));
	}
	
	@RequestMapping(value = "/user/edit/password/{login}", method = RequestMethod.GET)
	public ModelAndView changePassword(@PathVariable("login") String login, Principal principal){
		return principal.getName().equals(login) ? new ModelAndView("changepassword") : new ModelAndView("noaccess");
	}
	
	@RequestMapping(value = "/user/edit/password/{login}", method = RequestMethod.POST)
	public ModelAndView changePasswordPost(@Valid @ModelAttribute("password") Password password, BindingResult result) throws Exception{
		if (result.hasErrors()) {
			return new ModelAndView("changepassword");
		}
		clientService.changePassword(password.getLogin(), password.getPassword());
		return new ModelAndView("redirect:/thanks");
	}
	
	@RequestMapping(value = "/user/edit/personal/address/{login}", method = RequestMethod.GET)
	public ModelAndView editPersonalAddress(@PathVariable("login") String login, Principal principal){
		return principal.getName().equals(login) ? new ModelAndView("editpersonaladdress") : new ModelAndView("noaccess");
	}
	
	//syntax error in clientDAOImpl - changeAddress is commented
	//validation works
	@RequestMapping(value = "/user/edit/personal/address/{login}", method = RequestMethod.POST)
	public ModelAndView changeFirstNamePost(@Valid @ModelAttribute("address") Address address, BindingResult result, Principal principal) throws Exception{
		if(result.hasErrors()) {
			return new ModelAndView("editpersonaladdress");
		}
		clientService.changeAddress(principal.getName(), address);
		return new ModelAndView("redirect:/thanks");
	}
	
}
