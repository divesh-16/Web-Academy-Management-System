package com.divesh.entities;

public class Supplier 
{
	private int spid;
	private int aid;
	private String name;
	private String address;
	public Supplier(int spid, int aid, String name, String address) {
		super();
		this.spid = spid;
		this.aid = aid;
		this.name = name;
		this.address = address;
	}
	public int getSpid() {
		return spid;
	}
	public void setSpid(int spid) {
		this.spid = spid;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
