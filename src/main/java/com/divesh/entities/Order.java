package com.divesh.entities;

public class Order 
{
	private int oid;
	private int sid;
	private String productName;
	private int quantity;
	private double amount;
	private String payOption;
	private String payStatus;
	private String deliveryStatus;
	public Order(int oid, int sid, String productName, int quantity, double amount, String payOption, String payStatus,
			String deliveryStatus) {
		super();
		this.oid = oid;
		this.sid = sid;
		this.productName = productName;
		this.quantity = quantity;
		this.amount = amount;
		this.payOption = payOption;
		this.payStatus = payStatus;
		this.deliveryStatus = deliveryStatus;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPayOption() {
		return payOption;
	}
	public void setPayOption(String payOption) {
		this.payOption = payOption;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	
	
}
