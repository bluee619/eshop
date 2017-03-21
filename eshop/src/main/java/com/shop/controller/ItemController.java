package com.shop.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shop.models.Cart;
import com.shop.models.Item;
import com.shop.models.ItemFilter;
import com.shop.service.ClientService;
import com.shop.service.ItemService;
import com.shop.validation.ItemNameValidator;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@Autowired
	private ClientService clientService;
	
	private final ItemNameValidator itemNameValidator;
	
	@Autowired
	public ItemController(ItemNameValidator itemNameValidator){
		this.itemNameValidator = itemNameValidator;
	}
	
	@InitBinder("item")
	public void initBinderClient(WebDataBinder binder) {
		binder.addValidators(itemNameValidator);
	}
	
	@RequestMapping(value = "/additem", method = RequestMethod.GET)
	public ModelAndView additem() {
		return new ModelAndView("additem")
				.addObject("item", new Item());
	}
	
	@RequestMapping(value = "/additem", method = RequestMethod.POST)
	public ModelAndView additem_post(@Valid @ModelAttribute("item") Item item, BindingResult result, Principal principal) throws IOException {
		if (result.hasErrors()) {
			return new ModelAndView("additem");
		}
		itemService.addItem(item, new Date(), clientService.getUserByLogin(principal.getName()));
		return new ModelAndView("redirect:/thanks");
	}
	
	@RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
	public ModelAndView itempage(@PathVariable("id") int id) {
		Optional<Item> optItem = itemService.getbyId(id);
		Item item = optItem.isPresent() ? optItem.get() : new Item();
		return new ModelAndView("itempage")
				.addObject("item", item);
	}
	
	@RequestMapping(value = "/addToCart/{id}", method = RequestMethod.GET)
	public ModelAndView addItemToCart(@PathVariable("id") int id, HttpServletRequest request) {
		Cart cart;
		if (request.getSession().getAttribute("cart")==null){
			cart = new Cart();
		}	
		else{
			cart = (Cart) request.getSession().getAttribute("cart");
		}
		Optional<Item> optItem = itemService.getbyId(id);
		if(optItem.isPresent()){
			if(cart.isIn(id)){
				cart.addItem(optItem.get());
				request.getSession().setAttribute("cart", cart);
			}
		}
		return new ModelAndView("redirect:/cart");
	}
	
	@RequestMapping(value = "/deleteFromCart/{id}")
	public ModelAndView deleteItemFromCart(@PathVariable("id") int id, HttpServletRequest request){
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		Optional<Item> optItem = itemService.getbyId(id);
		if(optItem.isPresent()){
			cart.deleteItem(optItem.get());
		}
		return new ModelAndView("redirect:/cart");	
	}
	
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView cart(HttpServletRequest request){
		return new ModelAndView("cart");
	}
	
	@RequestMapping(value = "/search/{category}", method = RequestMethod.GET)
	public ModelAndView searchByCategory(@PathVariable("category") String category){
		Optional<List<Item>> itemsFound = itemService.getAllByCategory(category);
		List<Item> items = itemsFound.isPresent() ? itemsFound.get() : Collections.emptyList();
		return new ModelAndView("itemsfound")
				.addObject("itemsList", items);
	}
	
	@RequestMapping(value = "/itemsfromseller/{sellerId}", method = RequestMethod.GET)
	public ModelAndView searchBySeller(@PathVariable("sellerId") int sellerId){
		Optional<List<Item>> itemsFound = itemService.getAllBySeller(sellerId);
		List<Item> items = itemsFound.isPresent() ? itemsFound.get() : Collections.emptyList();
		return new ModelAndView("itemsfound")
				.addObject("itemsList", items);
	}
	
	@RequestMapping(value = "/searcher", method = RequestMethod.POST)
	public ModelAndView mainsiteSearcher(@ModelAttribute("itemFilter") ItemFilter itemFilter, BindingResult result) {
		Optional<List<Item>> itemsFound;
		if((itemFilter.getCategory()==null || itemFilter.getCategory().equals("ALL")) && itemFilter.getName().equals("")){
			itemsFound = itemService.getAll();
		}
		else if((itemFilter.getCategory()==null || itemFilter.getCategory().equals("ALL")) && !itemFilter.getName().equals("")){
			itemsFound = itemService.getItemsByName(itemFilter.getName());
		}
		else if(itemFilter.getCategory()!=null && !itemFilter.getCategory().equals("ALL") && itemFilter.getName().equals("")){
			itemsFound = itemService.getAllByCategory(itemFilter.getCategory());
		}
		else{
			itemsFound = itemService.getItemsByNameAndCategory(itemFilter.getName(), itemFilter.getCategory());
		}
		List<Item> items = itemsFound.isPresent() ? itemsFound.get() : Collections.emptyList();
		return new ModelAndView("itemsfound")
				.addObject("itemsList", items);
	}
	
	@RequestMapping(value = "/buyitem/{id}", method = RequestMethod.GET)
	public ModelAndView buyItem(@PathVariable("id") int id, Principal principal) {
		itemService.buyItem(itemService.getbyId(id).get(), clientService.getUserByLogin(principal.getName()));
		return new ModelAndView("redirect:/thanks");
	}
	
	@RequestMapping(value = "/buycart", method = RequestMethod.GET)
	public ModelAndView buyCart(Principal principal, HttpServletRequest request) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		itemService.buyCart(cart.getItemsInCart(), clientService.getUserByLogin(principal.getName()));
		return new ModelAndView("thanks");
	}
	
	@RequestMapping(value = "/myitems", method = RequestMethod.GET)
	public ModelAndView myItems(Principal principal){
		Optional<List<Item>> itemsFound = itemService.getItemsOnSale(principal.getName());
		List<Item> items = itemsFound.isPresent() ? itemsFound.get() : Collections.emptyList();
		return new ModelAndView("myitems")
				.addObject("itemsList", items);
	}
	
	@RequestMapping(value = "/myitems/delete/{id}", method = RequestMethod.GET)
	public ModelAndView myItemsDelete(@PathVariable("id") int id, Principal principal){
		itemService.deleteItem(id);
		return new ModelAndView("redirect:/thanks");
	}
}


