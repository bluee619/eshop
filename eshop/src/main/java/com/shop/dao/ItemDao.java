package com.shop.dao;

import java.util.List;

import com.shop.models.Client;
import com.shop.models.Item;

public interface ItemDao {
	List<Item> allItemsDateAsc();
	
	List<Item> itemsRandomOrder(int size);
	List<Item> itemsByCategory(String category);
	List<Item> itemsBySeller(int id);
	List<Item> itemsByName(String name);
	List<Item> itemsByNameAndCategory(String name, String category);
	List<Item> itemsBoughtBy(int id);
	List<Item> itemsOnSale(String name);
	
	void addItem(Item item);
	void deleteItem(Item item);
	void buyItem(Item item, Client client);
	Item getById(int id);
	boolean isNameUnique(String name);
	
}
