package com.divesh.entities;

public class Admin 
{
	private int id;
	private String name;
	private String phoneNo;
	private String address;
	private double salary;
	private String password;
	
	
	public Admin(int id, String name, String phoneNo, String address, double salary, String password) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNo = phoneNo;
		this.address = address;
		this.salary = salary;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", phoneNo=" + phoneNo + ", address=" + address + ", salary="
				+ salary + ", password=" + password + "]";
	}
	
	
}
