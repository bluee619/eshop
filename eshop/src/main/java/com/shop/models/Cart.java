package com.shop.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cart {
	private List<Item> itemsInCart = new ArrayList<>();

	public List<Item> getItemsInCart() {
		return itemsInCart;
	}

	public void setItemsInCart(List<Item> itemsInCart) {
		this.itemsInCart = itemsInCart;
	}
	
	public void addItem(Item item){
		itemsInCart.add(item);
	}
	
	public void deleteItem(Item item){
		if(!itemsInCart.isEmpty()){
			this.setItemsInCart(itemsInCart.stream()
											.filter(i -> i.getId() != item.getId())
											.collect(Collectors.toList()));
		}
	}
	
	public boolean isIn(int number){
		for(Item i : itemsInCart){
			if(number==i.getId())
				return false;
		}
		return true;
	}
}
