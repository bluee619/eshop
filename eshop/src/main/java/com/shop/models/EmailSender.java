package com.shop.models;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class EmailSender {
	
	public EmailSender(VelocityEngine velocity, MailSender mailSender){
		this.velocity = velocity;
		this.mailSender = mailSender;
	}
	
	private VelocityEngine velocity;
	private MailSender mailSender;
	
	public void sendBuyer(String emailTo, String nameTo, String templ, Item item){
		SimpleMailMessage message = new SimpleMailMessage();
		
		Map<String, String> model_velocity = new HashMap<>();
		model_velocity.put("nameTo", nameTo);
		model_velocity.put("itemName", item.getName());
		//result = price + shipment cost
		BigDecimal result = item.getPrice().add(item.getShipment().getShippingCost());
		model_velocity.put("itemCost", result.toString());
		model_velocity.put("sellerName", item.getSeller().getPersonalData().getFirstname());
		model_velocity.put("sellerAccNr", item.getSeller().getPersonalData().getAccountNumber());
		VelocityContext velocityContext = new VelocityContext(model_velocity);
		
		StringWriter writer = new StringWriter();
	    Template template = new Template();
	    template = velocity.getTemplate(templ);
		
	    template.merge(velocityContext, writer);
	    
	    message.setFrom("onlineshoptestmailNOREPLY@gmail.com");
	    message.setTo(emailTo);
	    message.setSubject("Tranasction summary");
	    message.setText(writer.toString());
	    
		mailSender.send(message);
	}
	
	public void sendSeller(String emailTo, String buyerName, String templ, Item item){
		SimpleMailMessage message = new SimpleMailMessage();
		
		Map<String, String> model_velocity = new HashMap<>();
		model_velocity.put("nameTo", item.getSeller().getPersonalData().getFirstname());
		model_velocity.put("itemName", item.getName());
		model_velocity.put("buyerName", buyerName);
		VelocityContext velocityContext = new VelocityContext(model_velocity);
		
		StringWriter writer = new StringWriter();
	    Template template = new Template();
	    template = velocity.getTemplate(templ);
		
	    template.merge(velocityContext, writer);
	    
	    message.setFrom("onlineshoptestmailNOREPLY@gmail.com");
	    message.setTo(emailTo);
	    message.setSubject("Tranasction summary");
	    message.setText(writer.toString());
	    
		mailSender.send(message);
	}
}