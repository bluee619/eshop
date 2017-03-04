package com.shop.dao;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.shop.models.Client;
import com.shop.models.Item;

@Repository
@Primary
public class ItemDaoImpl extends AbstractDao implements ItemDao {
	
	@Override
	public List<Item> allItemsDateAsc() {
		String hql = "From Item I WHERE I.boughtBy is null order by I.date asc";
		Query query = getSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Item> items = (List<Item>) query.list();
		if(items != null && !items.isEmpty()){
			return items;
		}
		
		return null;
	}
	
	@Override
	public List<Item> itemsRandomOrder(int size) {
		String hql = "From Item I WHERE I.boughtBy is null";
		Query query = getSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Item> items = (List<Item>) query.list();
		if(items != null && !items.isEmpty()){
			Collections.shuffle(items);
			items = items.stream()
						 .limit(size)
						 .collect(Collectors.toList());
			return items;
		}
		
		return null;
	}
	

	@Override
	public List<Item> itemsByCategory(String category) {
		String hql = "From Item I where I.category = :category and I.boughtBy is null order by I.date asc";
		Query query = getSession().createQuery(hql)
								  .setParameter("category", category);
		
		@SuppressWarnings("unchecked")
		List<Item> items = (List<Item>) query.list();
		if(items != null && !items.isEmpty()){
			return items;
		}
		
		return null;
	}

	@Override
	public List<Item> itemsBySeller(int id) {
		String hql = "From Item I where I.seller.clientId = :clientId and I.boughtBy is null order by I.date asc";
		Query query = getSession().createQuery(hql)
								  .setParameter("clientId", id);
		
		@SuppressWarnings("unchecked")
		List<Item> items = (List<Item>) query.list();
		if(items != null && !items.isEmpty()){
			return items;
		}
		
		return null;
	}

	@Override
	public void addItem(Item item) {
		getSession().persist(item);
	}

	@Override
	public Item getById(int id) {
		String hql = "From Item I where I.id = :id";
		
		@SuppressWarnings("unchecked")
		List<Item> items = getSession().createQuery(hql)
									   .setParameter("id", id)
									   .list();
		
		if(items != null && !items.isEmpty()){
			return items.get(0);
		}
		
		return null;
	}

	@Override
	public List<Item> itemsByName(String name) {
		String hql = "From Item I where I.name like :name and I.boughtBy is null order by I.date asc";
		Query query = getSession().createQuery(hql)
								  .setParameter("name","%" + name + "%");
		
		@SuppressWarnings("unchecked")
		List<Item> items = (List<Item>) query.list();
		if(items != null && !items.isEmpty()){
			return items;
		}
		
		return null;
	}

	@Override
	public List<Item> itemsByNameAndCategory(String name, String category) {
		String hql = "From Item I where I.name like :name and I.category = :category and I.boughtBy is null order by I.date asc";
		Query query = getSession().createQuery(hql)
								  .setParameter("name","%" + name + "%")
								  .setParameter("category", category);
		
		@SuppressWarnings("unchecked")
		List<Item> items = (List<Item>) query.list();
		if(items != null && !items.isEmpty()){
			return items;
		}
		
		return null;
	}
	
	@Override
	public List<Item> itemsOnSale(String name) {
		String hql = "From Item I where I.seller.login = :name and I.boughtBy is null";
		Query query = getSession().createQuery(hql)
								  .setParameter("name", name);
		
		@SuppressWarnings("unchecked")
		List<Item> items = (List<Item>) query.list();
		if(items != null && !items.isEmpty()){
			return items;
		}
		return null;
	}
	
	@Override
	public List<Item> itemsBoughtBy(int id) {
		String hql = "From Client C where C.id = :id";
		Query query = getSession().createQuery(hql)
								  .setParameter("id", id);
		
		List<Item> items = ((Client) query.uniqueResult()).getBoughtItems();
		if(items != null && !items.isEmpty()){
			return items;
		}
		return null;
	}

	@Override
	public boolean isNameUnique(String name) {
		String hql = "FROM Item I WHERE I.name = :name";
		Query query = getSession().createQuery(hql)
								  .setParameter("name", name);
		
		@SuppressWarnings("unchecked")
		List<Item> items = (List<Item>) query.list();
		
		if(items.isEmpty()){
			return true;
		}
		return false;
	}

	@Override
	public void buyItem(Item item, Client client) {
		String hql = "UPDATE Item SET boughtBy = :boughtBy" + " WHERE id = :id";
		
		Query query = getSession().createQuery(hql)
								  .setParameter("boughtBy", client)
								  .setParameter("id", item.getId());
		query.executeUpdate();
	
 	}

	@Override
	public void deleteItem(Item item){	
		delete(item);
	}





}
