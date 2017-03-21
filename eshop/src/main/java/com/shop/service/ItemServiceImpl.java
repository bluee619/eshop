package com.shop.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shop.dao.ItemDao;
import com.shop.models.Client;
import com.shop.models.EmailSender;
import com.shop.models.Item;

@Service
@Primary
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private EmailSender emailSender;
	
	@Override
	@Transactional(readOnly=true)
	public Optional<List<Item>> getAllByCategory(String category) {
		Optional<List<Item>> items = Optional.ofNullable(itemDao.itemsByCategory(category));
		items.ifPresent(o -> o.forEach(item -> item.setEncoded(new String(Base64.encode(item.getImage())))));
		return items;
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<List<Item>> getAll() {
		Optional<List<Item>> items = Optional.ofNullable(itemDao.allItemsDateAsc());
		items.ifPresent(o -> o.forEach(item -> item.setEncoded(new String(Base64.encode(item.getImage())))));
		return items;
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<List<Item>> getAllBySeller(int id) {
		Optional<List<Item>> items = Optional.ofNullable(itemDao.itemsBySeller(id));
		items.ifPresent(o -> o.forEach(item -> item.setEncoded(new String(Base64.encode(item.getImage())))));
		return items;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<List<Item>> getRandom(int size) {
		Optional<List<Item>> items = Optional.ofNullable(itemDao.itemsRandomOrder(size));
		items.ifPresent(o -> o.forEach(item -> item.setEncoded(new String(Base64.encode(item.getImage())))));
		return items;
	}

	@Override
	@Transactional
	public void addItem(Item item, Date date, Client seller) throws IOException {
		long time = date.getTime();
		item.setMyTimestamp(new Timestamp(time));
		item.setImage(item.getFile().getBytes());
		item.setSeller(seller);
		
		itemDao.addItem(item);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Item> getbyId(int id) {
		 Optional<Item> item = Optional.ofNullable(itemDao.getById(id));
		 item.ifPresent(i -> i.setEncoded(new String(Base64.encode(i.getImage()))));
		 return item;
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<List<Item>> getItemsByNameAndCategory(String name, String category) {
		Optional<List<Item>> items = Optional.ofNullable(itemDao.itemsByNameAndCategory(name, category));
		items.ifPresent(o -> o.forEach(item -> item.setEncoded(new String(Base64.encode(item.getImage())))));
		return items;
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<List<Item>> getItemsByName(String name) {
		Optional<List<Item>> items = Optional.ofNullable(itemDao.itemsByName(name));
		return items;
	}

	@Override
	@Transactional(readOnly=true)
	public boolean isNameUnique(String name) {
		return itemDao.isNameUnique(name);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<List<Item>> getItemsBoughtBy(int id) {
		Optional<List<Item>> items = Optional.ofNullable(itemDao.itemsBoughtBy(id));
		return items;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<List<Item>> getItemsOnSale(String name) {
		Optional<List<Item>> items = Optional.ofNullable(itemDao.itemsOnSale(name));
		return items;
	}
	
	@Override
	@Transactional
	public void buyItem(Item item, Client client) {
		try{
			if(item.getBoughtBy() == null){
				itemDao.buyItem(item, client);
				emailSender.sendBuyer(client.getEmail(), client.getPersonalData().getFirstname(), "templateBuyer.vm", item);
				emailSender.sendSeller(item.getSeller().getEmail(), client.getPersonalData().getFirstname(), "templateSeller.vm", item);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
	}

	@Override
	@Transactional
	public void buyCart(List<Item> items, Client client) {
		//filter in case already sold item was added somehow using url
		Item[] results = items.stream()
							  .filter(item -> item.getSeller() == null)
			                  .toArray(Item[]::new);
		
		try{
			for(Item item : results){
				itemDao.buyItem(item, client);
				emailSender.sendBuyer(client.getEmail(), client.getPersonalData().getFirstname(), "templateBuyer.vm", item);
				emailSender.sendSeller(item.getSeller().getEmail(), client.getPersonalData().getFirstname(), "templateSeller.vm", item);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
	}


	@Override
	@Transactional
	public void deleteItem(int id) {
		Item item = itemDao.getById(id);
		itemDao.deleteItem(item);
	}

}
