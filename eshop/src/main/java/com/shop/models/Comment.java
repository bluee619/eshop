package com.shop.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Comment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5792798421710955534L;

	@Id
	@GeneratedValue
	private Integer id;
	@NotNull
	@Lob
	@Size(min=1, max=500)
	private String description;
	@NotNull
	@Min(1)
	@Max(10)
	private Integer mark;
	@NotNull
	@OneToOne(mappedBy="comment")
	private Item item;
	@NotNull
	@ManyToOne
	@JoinColumn(name="receiver_id")
	private Client receivedBy;
	@NotNull
	@ManyToOne
	@JoinColumn(name="author_id")
	private Client author;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Client getAuthor() {
		return author;
	}
	public void setAuthor(Client author) {
		this.author = author;
	}
	public Client getReceivedBy() {
		return receivedBy;
	}
	public void setReceivedBy(Client receivedBy) {
		this.receivedBy = receivedBy;
	}
}
