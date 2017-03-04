package com.shop.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.shop.models.Client;
import com.shop.models.Item;

public interface ItemService {
	
	Optional<List<Item>> getAll();
	Optional<List<Item>> getAllBySeller(int id);
	
	Optional<List<Item>> getRandom(int size);
	Optional<List<Item>> getAllByCategory(String category);
	Optional<List<Item>> getItemsByName(String name);
	Optional<List<Item>> getItemsByNameAndCategory(String name, String category);
	Optional<List<Item>> getItemsBoughtBy(int id);
	Optional<List<Item>> getItemsOnSale(String name);
	
	Optional<Item> getbyId(int id);
	void addItem(Item item, Date date, Client seller) throws IOException;
	void deleteItem(int id);
	void buyItem(Item item, Client client);
	void buyCart(List<Item> items, Client client);
	boolean isNameUnique(String name);
}
