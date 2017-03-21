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
	private ItemService itemService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		return new ModelAndView("redirect:/main");
	}

	@RequestMapping(value = "/thanks", method = RequestMethod.GET)
	public ModelAndView thanks() {
		return new ModelAndView("thanks");
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView mainsite() {		
		Optional<List<Item>> fourRandomItems = itemService.getRandom(4);
		List<Item> items = fourRandomItems.isPresent() ? fourRandomItems.get() : Collections.emptyList();
		return new ModelAndView("mainsite")
				.addObject("itemList", items);
	}
	
	@RequestMapping(value = "/loginerror", method = RequestMethod.GET)
	public ModelAndView loginerror(){
		return new ModelAndView("loginerrorpage");
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView noaccess(){
		return new ModelAndView("noaccess");
	}

}
