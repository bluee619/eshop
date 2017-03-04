package com.shop.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.shop.models.Item;
import com.shop.service.ItemService;

@Controller
@SessionAttributes("cart")
public class MainController {

	@Autowired
	ItemService itemService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView("redirect:/main");
		return model;
	}

	@RequestMapping(value = "/thanks", method = RequestMethod.GET)
	public String thanks() {
		return "thanks";
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView mainsite() {
		ModelAndView model = new ModelAndView("mainsite");
		
		Optional<List<Item>> fourRandomItems = itemService.getRandom(4);
		
		List<Item> items = fourRandomItems.isPresent() ? fourRandomItems.get() : Collections.emptyList();
		
		model.addObject("itemList", items);
		return model;
	}
	
	@RequestMapping(value = "/loginerror", method = RequestMethod.GET)
	public ModelAndView loginerror(){
		ModelAndView model = new ModelAndView("loginerrorpage");
		return model;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView noaccess(){
		ModelAndView model = new ModelAndView("noaccess");
		return model;
	}

}
