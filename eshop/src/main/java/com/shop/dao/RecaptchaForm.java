package com.shop.dao;

import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class RecaptchaForm {
	@Transient
	@NotEmpty
	@JsonProperty("g-recaptcha-response")
	private String recaptchaResponse;

	public String getRecaptchaResponse() {
		return recaptchaResponse;
	}

	public void setRecaptchaResponse(String recaptchaResponse) {
		this.recaptchaResponse = recaptchaResponse;
	}
}
