package com.shop.models;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class PersonalData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1630919981927092845L;
	
	@Id
	@GeneratedValue
	private Integer Id;
	@OneToOne(mappedBy="personalData")
	private Client client;
	@NotNull
	@Size(min=2, max=15)
	private String firstname;
	@NotNull
	@Size(min=2, max=15)
	private String surname;
	@NotNull
	@Embedded
	@Valid
	private Address address;
	@Size(min=9, max=9)
	private String phoneNumber;
	@Size(min=26, max=26)
	private String accountNumber;
	
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
}
