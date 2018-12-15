package com.cg.pwa.dao;


import java.util.List;

import com.cg.pwa.beans.Account;
import com.cg.pwa.beans.PrintTransactions;
import com.cg.pwa.exception.MyException;
import com.cg.pwa.exception.InvalidPhoneNumber;

public interface AccountDao 
{
	public Account createAccount(Account c) throws MyException;
	public Account getAccount(String mobileno);
	public boolean setAccount(String mobileNo, double amount);
	public List<PrintTransactions> getTransactions(String mobileNo) throws MyException, InvalidPhoneNumber;
	public void loadTransaction(PrintTransactions pt);
}
