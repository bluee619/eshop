package com.shop.models;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class Address implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3472095436763295199L;
	
	@NotNull
	@Size(min=2, max=20)
	private String city;
	@NotNull
	@Size(min=2, max=20)
	private String street;
	@NotNull
	@Size(min=6, max=6)
	private String postalCode;
	@NotEmpty
	private String houseNumber;
	@NotNull
	@Size(min=2, max=20)
	private String state;
	
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
