package com.shop.dao;

import com.shop.models.Address;
import com.shop.models.Client;

public interface ClientDao {
	Client findByLogin(String login);
	void createAccount(Client client);
	void changePassword(String login, String newPassword);
	void changePhoneNumber(String login, String newPhoneNumber);
	void changeAccountNumber(String login, String newAccountNumber);
	void changeAddress(String login, Address address);
	void changeFirstname(String login, String newFirstname);
	void changeSurname(String login, String newSurname);
	boolean isLoginUnique(String login);
}