package com.divesh.entities;

public class Accessories 
{
	private int aid;
	private String name;
	private int stock;
	private double price;
	
	public Accessories(int aid, String name, int stock, double price) {
		super();
		this.aid = aid;
		this.name = name;
		this.stock = stock;
		this.price = price;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
