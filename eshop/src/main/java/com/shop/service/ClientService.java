package com.shop.service;

import com.shop.models.Address;
import com.shop.models.Client;

public interface ClientService {
	void createAccount(Client client);
	void changePassword(String login, String newPassword);
	void changeAddress(String login, Address address);
	boolean isLoginUnique(String login);
	Client getUserByLogin(String login);
}
