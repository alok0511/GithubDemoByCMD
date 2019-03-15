package com.capgemini.bean;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customerdetails")
public class Customer {
	
	
	@Column(name = "cName")
	private String cName;
	
	@Id
	@Column(name = "mobileNo")
	private String mobileNumber;
	
	@Embedded
	private Wallet wallet;
	
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(String cName, String mobileNumber, Wallet wallet) {
		super();
		this.cName = cName;
		this.mobileNumber = mobileNumber;
		this.wallet = wallet;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	@Override
	public String toString() {
		return "Customer [Name=" + cName + ", Mobile Number=" + mobileNumber + ", Wallet=" + wallet + "]";
	}
	
}
