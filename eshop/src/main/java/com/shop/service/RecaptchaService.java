package com.shop.service;

public interface RecaptchaService {
	boolean isResponseValid(String remoteIp, String response);
}

