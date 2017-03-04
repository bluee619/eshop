package com.shop.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;


@Entity
public class Item implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 818728117758672269L;

	@Id
	@GeneratedValue
	private Integer id;
	@NotNull
	@Size(min=2, max=20)
	private String name;
	@NotNull
	@Lob
	@Size(min=1, max=1000)
	@Type(type = "org.hibernate.type.TextType")
	private String description;
	@DecimalMin("0.00")
	@NotNull
	private BigDecimal price;
	
	public enum Categories {
		MOTO,
		IT,
		FOOD,
		SOMETHING;
	}
	
	@Temporal(TemporalType.DATE)
    private java.util.Date date;
	@NotEmpty
	private String category;
	@ManyToOne
	@JoinColumn(name="client_id_seller")
	private Client seller;
	
	@Lob
	@Type(type="org.hibernate.type.BinaryType")
	private byte[] image;
	
	@Transient
	@NotNull
    private MultipartFile file;
	
	@Transient
	private String encoded;
	
	@NotNull
	@Embedded
	@Valid
	private Shipment shipment;
	@OneToOne
	@JoinColumn(name="comment_id")
	private Comment comment;
	@ManyToMany(mappedBy="cart")
	private List<Client> inCart;
	@ManyToOne
	@JoinColumn(name="client_id_boughtBy")
	private Client boughtBy;
	@ManyToMany(mappedBy="observedItems")
	private List<Client> observedBy;
	@ManyToOne
	@JoinColumn(name="client_id_orderedBy")
	private Client orderedBy;
	
	
	public List<Client> getObservedBy() {
		return observedBy;
	}
	public void setObservedBy(List<Client> observedBy) {
		this.observedBy = observedBy;
	}
	public Client getBoughtBy() {
		return boughtBy;
	}
	public void setBoughtBy(Client boughtBy) {
		this.boughtBy = boughtBy;
	}
	public List<Client> getInCart() {
		return inCart;
	}
	public void setInCart(List<Client> inCart) {
		this.inCart = inCart;
	}
	public java.util.Date getMyTimestamp() {
		return date;
	}
	public void setMyTimestamp(java.util.Date myTimestamp) {
		date = myTimestamp;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		if(category.equals("Moto"))
			this.category = Categories.MOTO.toString();
		else if(category.equals("Technology"))
			this.category = Categories.IT.toString();
		else if(category.equals("Food"))
			this.category = Categories.FOOD.toString();
		else this.category = Categories.SOMETHING.toString();
	}
	public Client getSeller() {
		return seller;
	}
	public void setSeller(Client seller) {
		this.seller = seller;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Shipment getShipment() {
		return shipment;
	}
	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getEncoded() {
		return encoded;
	}
	public void setEncoded(String encoded) {
		this.encoded = encoded;
	}
	public java.util.Date getDate() {
		return date;
	}
	public Client getOrderedBy() {
		return orderedBy;
	}
	
	
}
