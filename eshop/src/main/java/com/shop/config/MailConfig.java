package com.shop.config;

import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.shop.models.EmailSender;

@Configuration
public class MailConfig {

	@Bean
	public MailSender mailSender(){
		
		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", "true");
		mailProperties.put("mail.smtp.starttls.enable", "true");
		mailProperties.put("mail.smtp.debug", "false");
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		
		mailSender.setJavaMailProperties(mailProperties);
		mailSender.setHost("smtp.gmail.com");	
		mailSender.setPort(587);
		mailSender.setUsername("test123noreplytothismail@gmail.com");
		mailSender.setPassword("123test123");
		return mailSender;
	}
	
	@Bean
	public VelocityEngine velocityEngine(){		
		Properties props = new Properties();
		props.setProperty("resource.loader", "class");
		props.setProperty("class.resource.loader.class",  ClasspathResourceLoader.class.getName());
		VelocityEngine velocityEngine = new VelocityEngine(props);
		return velocityEngine;
	}
	
	@Bean
	@Autowired
	public EmailSender email(VelocityEngine velocity, MailSender mailSender){
		EmailSender email = new EmailSender(velocity, mailSender);
		return email;
	}
}
