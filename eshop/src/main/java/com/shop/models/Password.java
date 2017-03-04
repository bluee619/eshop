package com.shop.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Password {
	@NotNull
	@Size(min=7)
	private String password;
	@NotNull
	@Size(min=7)
	private String passwordRepeat;
	@NotNull
	@Size(min=7)
	private String oldPassword;
	
	private String login;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getPasswordRepeat() {
		return passwordRepeat;
	}
	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public boolean isOk(){
		return this.password.equals(this.passwordRepeat);
	}
}
