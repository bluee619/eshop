package com.shop.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.ClientDao;
import com.shop.models.Address;
import com.shop.models.Client;
import com.shop.models.Client.Role;

@Service
@Primary
public class ShopUserService implements UserDetailsService, ClientService {
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		
		Client client = clientDao.findByLogin(login);
		if(client != null){
			List<GrantedAuthority> authorities = buildUserAuthority(client.getRole());
			return buildUserForAuthentication(client, authorities);
		}
		throw new UsernameNotFoundException("User" + login + " hasn't been found.");
	}

	private UserDetails buildUserForAuthentication(Client client, List<GrantedAuthority> authorities) {
		return new User(client.getLogin(), client.getPassword(), authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Role role) {
		Set<GrantedAuthority> setAuths = new HashSet<>();
		setAuths.add(new SimpleGrantedAuthority(role.toString()));
		List<GrantedAuthority> Result = new ArrayList<>(setAuths);
		return Result;
		
	}

	@Override
	@Transactional
	public void createAccount(Client client) {
		String encodedPassword = encoder.encode(client.getPassword());
		client.setPassword(encodedPassword);
		Role role = Client.Role.USER;
		client.setRole(role);
		clientDao.createAccount(client);
	}
	
	@Override
	@Transactional
	public void changePassword(String login, String newPassword){
		String encodedPassword = encoder.encode(newPassword);
		clientDao.changePassword(login, encodedPassword);
	}
	
	@Override
	@Transactional
	public void changeAddress(String login, Address address){
		clientDao.changeAddress(login, address);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Client getUserByLogin(String login) {
		Client client = clientDao.findByLogin(login);
		return client;
	}

	@Override
	@Transactional(readOnly=true)
	public boolean isLoginUnique(String login) {
		return clientDao.isLoginUnique(login);
	}

	
}
