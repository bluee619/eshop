package com.shop.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.shop.dao.RecaptchaForm;

@Entity
public class Client extends RecaptchaForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 279968576617422627L;
	public enum Role{
		USER,
		ADMIN
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer clientId;
	
	@NotNull
	@Size(min=4, max=20)
	private String login;
	@NotNull
	@Size(min=7)
	private String password;
	@NotEmpty
	@Email
	private String email;
	
	@ManyToMany
	@JoinTable(
			name="items_in_cart",
			joinColumns={@JoinColumn(name="client_id_cart")},
			inverseJoinColumns={@JoinColumn(name="item_id_cart")}
			)
	private List<Item> cart;
	@OneToMany(mappedBy="boughtBy", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Item> boughtItems;
	@OneToMany(mappedBy="seller")
	private List<Item> itemsForSale;
	@ManyToMany
	@JoinTable(
			name="observed_items",
			joinColumns={@JoinColumn(name="client_id_observedItems")},
			inverseJoinColumns={@JoinColumn(name="item_id_observedItems")}
			)
	private List<Item> observedItems;
	//comments given are probably unnecessary
	@OneToMany(mappedBy="author")
	private List<Comment> commentsGiven;
	@OneToMany(mappedBy="receivedBy")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Comment> commentsReceived;
	@OneToMany(mappedBy="orderedBy")
	private List<Item> orders;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="personal_data_id")
	@Valid
	private PersonalData personalData;
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public Integer getclientId() {
		return clientId;
	}
	public void setclientId(Integer id) {
		this.clientId = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Item> getCart() {
		return cart;
	}
	public void setCart(List<Item> cart) {
		this.cart = cart;
	}
	public List<Item> getBoughtItems() {
		return boughtItems;
	}
	public void setBoughtItems(List<Item> boughtItems) {
		this.boughtItems = boughtItems;
	}
	public List<Item> getItemsForSale() {
		return itemsForSale;
	}
	public void setItemsForSale(List<Item> itemsForSale) {
		this.itemsForSale = itemsForSale;
	}
	public List<Item> getObservedItems() {
		return observedItems;
	}
	public void setObservedItems(List<Item> observedItems) {
		this.observedItems = observedItems;
	}
	public List<Comment> getCommentsGiven() {
		return commentsGiven;
	}
	public void setCommentsGiven(List<Comment> commentsGiven) {
		this.commentsGiven = commentsGiven;
	}
	public List<Comment> getCommentsReceived() {
		return commentsReceived;
	}
	public void setCommentsReceived(List<Comment> commentsReceived) {
		this.commentsReceived = commentsReceived;
	}
	public List<Item> getOrders() {
		return orders;
	}
	public void setOrders(List<Item> orders) {
		this.orders = orders;
	}
	public PersonalData getPersonalData() {
		return personalData;
	}
	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
