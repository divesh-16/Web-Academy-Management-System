package com.divesh.entities;

public class Coach 
{
	private int cno;
	private String name;
	private String phoneNo;
	private String address;
	private double salary;
	private String password;
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
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
		return "Coach [cno=" + cno + ", name=" + name + ", phoneNo=" + phoneNo + ", address=" + address + ", salary="
				+ salary + ", password=" + password + "]";
	}
	
	
}
