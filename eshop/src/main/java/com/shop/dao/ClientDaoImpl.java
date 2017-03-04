package com.shop.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.shop.models.Address;
import com.shop.models.Client;


@Repository
@Primary
public class ClientDaoImpl extends AbstractDao implements ClientDao {
	
	@Override
	public Client findByLogin(String login){
		String hql = "FROM Client C WHERE C.login = :login";
		Query query = getSession().createQuery(hql);
		query.setParameter("login", login);
		
		@SuppressWarnings("unchecked")
		List<Client> clients = (List<Client>) query.list();
		if(clients != null && !clients.isEmpty()){
			return clients.get(0);
		}
		
		return null;
	}

	@Override
	public void createAccount(Client client) {
		persist(client);
	}

	@Override
	public void changePassword(String login, String newPassword) {
		String hql = "UPDATE Client C SET C.password = :newPassword WHERE C.login = :login";
		Query query = getSession().createQuery(hql)
								  .setParameter("newPassword", newPassword)
								  .setParameter("login", login);
		
		query.executeUpdate();
	}

	@Override
	public void changeFirstname(String login, String newFirstname) {
		String hql = "UPDATE Client C SET C.personalData.firstname = :newFirstname WHERE C.login = :login";
		
		Query query = getSession().createQuery(hql)
								  .setParameter("newFirstname", newFirstname)
								  .setParameter("login", login);
		
		query.executeUpdate();
	}

	@Override
	public void changeSurname(String login, String newSurname) {
		String hql = "UPDATE Client C SET C.personalData.surname = :newSurname WHERE C.login = :login";
		
		Query query = getSession().createQuery(hql)
								  .setParameter("newSurname", newSurname)
								  .setParameter("login", login);
		
		query.executeUpdate();
	}

	@Override
	public void changePhoneNumber(String login, String newPhoneNumber) {
		String hql = "UPDATE Client C SET C.personalData.phoneNumber = :newPhoneNumber WHERE C.login = :login";
		
		Query query = getSession().createQuery(hql)
								  .setParameter("newPhoneNumber", newPhoneNumber)
								  .setParameter("login", login);
		
		query.executeUpdate();
	}

	@Override
	public void changeAccountNumber(String login, String newAccountNumber) {
		String hql = "UPDATE Client C SET C.personalData.accountNumber = :newAccountNumber WHERE C.login = :login";
		
		Query query = getSession().createQuery(hql)
								  .setParameter("newAccountNumber", newAccountNumber)
								  .setParameter("login", login);
		
		query.executeUpdate();
	}
	
	//syntax error connected with cross join
	@Override
	public void changeAddress(String login, Address address) {
	/*	String hql = "UPDATE PersonalData PD SET " + 
					 "PD.address = :address " +
					 "WHERE PD.client.login = : login";
		
		Query query = getSession().createQuery(hql)
								  .setParameter("address", address)
								  .setParameter("login", login);
		
		query.executeUpdate();*/
	}

	@Override
	public boolean isLoginUnique(String login) {
		String hql = "FROM Client C WHERE C.login = :login";
		Query query = getSession().createQuery(hql)
								  .setParameter("login", login);
		
		@SuppressWarnings("unchecked")
		List<Client> clients = (List<Client>) query.list();
		
		if(clients.isEmpty()){
			return true;
		}
		
		return false;
	}
}
