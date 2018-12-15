package com.cg.pwa.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "bank")
public class Account 
{

	@Id
	private String mobileNumber;
	private String customerName;
	private double accountBalance;

	public Account(String name, String mobNo, double amt) 
	{
		// TODO Auto-generated constructor stub
		this.customerName = name;
		this.mobileNumber = mobNo;
		this.accountBalance = amt;
	}
	public Account() 
	{
		// TODO Auto-generated constructor stub
	}
	public String getCustomerName() 
	{
		return customerName;
	}
	public void setCustomerName(String customerName) 
	{
		this.customerName = customerName;
	}
	public String getMobileNumber() 
	{
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) 
	{
		this.mobileNumber = mobileNumber;
	}
	public double getAccountBalance() 
	{
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) 
	{
		this.accountBalance = accountBalance;
	}

	public void fundTransfer(double amount) 
	{
		this.accountBalance = this.accountBalance - amount;
	}


	@Override
	public String toString() 
	{
		return "CustomerName: " + customerName + ", MobileNumber: "+ mobileNumber + ", Amount: " + accountBalance;
	}



}

