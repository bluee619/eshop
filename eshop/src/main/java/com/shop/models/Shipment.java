package com.shop.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Embeddable
public class Shipment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2830207664007624855L;
	
	@NotNull
	@DecimalMin("0.00")
	private BigDecimal shippingCost;
	@NotNull
	@DecimalMin("0.00")
	private BigDecimal approxTime;
	
	public BigDecimal getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(BigDecimal shippingCost) {
		this.shippingCost = shippingCost;
	}
	public BigDecimal getApproxTime() {
		return approxTime;
	}
	public void setApproxTime(BigDecimal approxTime) {
		this.approxTime = approxTime;
	}
}
