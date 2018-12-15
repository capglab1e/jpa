package com.cg.pwa.service;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.pwa.beans.Account;
import com.cg.pwa.beans.PrintTransactions;
import com.cg.pwa.dao.AccountDao;
import com.cg.pwa.dao.AccountDaoImpl;
import com.cg.pwa.exception.MyException;
import com.cg.pwa.exception.InvalidAmount;
import com.cg.pwa.exception.InvalidPhoneNumber;
import com.cg.pwa.exception.NameException;

public class AccountServiceImpl implements AccountService
{
	AccountDao dao;

	public AccountServiceImpl() 
	{
		dao = new AccountDaoImpl();
	}

	

	@Override
	public boolean validateUserName(String name) throws NameException 
	{

		if(name==null) throw new NameException("Null value");
		Pattern p = Pattern.compile("[A-Z]{1}[a-z]{2,30}");
		Matcher mat = p.matcher(name);
		if(!mat.matches())
			System.out.println("Invalid Name");
		return mat.matches();
	}

	@Override
	public boolean validatePhoneNumber(String mobileNo) throws InvalidPhoneNumber {

		if(mobileNo==null) 
			throw new InvalidPhoneNumber("Null value");
		Pattern pat = Pattern.compile("[6-9]{1}[0-9]{9}");
		Matcher mat = pat.matcher(mobileNo);
		if(!mat.matches())
			System.out.println("Invalid Mobile Number");
		return mat.matches();
	}

	@Override
	public boolean validateAmount(double amt) throws InvalidAmount {

		Pattern pat = Pattern.compile("[1-9]{1}[0-9.]{0,9}");
		Matcher mat = pat.matcher(String.valueOf(amt));
		boolean b = mat.matches();

		if (!b)
			throw new InvalidAmount();

		return b;
	}

	@Override
	public boolean validateTargetMobNo(String targetMobNo) throws InvalidPhoneNumber {

		if(targetMobNo==null) 
			throw new InvalidPhoneNumber("Null value");
		Pattern pat = Pattern.compile("[6-9]{1}[0-9]{9}");
		Matcher mat = pat.matcher(targetMobNo);
		boolean b = mat.matches();

		if (!b)
			throw new InvalidPhoneNumber("Invalid target Mobile Number");

		return b;
	}

	

	@Override
	public double showBalance(String mobileno) throws MyException,
			MyException {
		// TODO Auto-generated method stub
		String tranType = "Check balance";
		Account bal = dao.getAccount(mobileno);
		if (bal == null)
			throw new MyException("Mobile number doesn't exist");
		dao.loadTransaction(new PrintTransactions(mobileno, tranType, bal.getAccountBalance()));
		return bal.getAccountBalance();
	}

	

	@Override
	public List<PrintTransactions> getTransactions(String mobileNo)
			throws MyException, InvalidPhoneNumber {
		// TODO Auto-generated method stub
		
		if(dao.getAccount(mobileNo) == null)
			throw new MyException("Mobile number doesn't exist");
		
		List<PrintTransactions> list = null;
		
		list = dao.getTransactions(mobileNo);
		
		return list;
	}

	@Override
	public Account createAccount(Account c) throws MyException {
		// TODO Auto-generated method stub
Account create = dao.createAccount(c);
		
		if(create == null)
			throw new MyException("Mobile number doesn't exist");
		return create;
	}

	@Override
	public Account fundTransfer(String sourceMobileNo, String targetMobileNo,
			double amount) throws MyException {
		// TODO Auto-generated method stub
		String tranType = "Transfer";
		Account sbal = dao.getAccount(sourceMobileNo);
		Account tbal = dao.getAccount(targetMobileNo);
		if (sbal == null)
			throw new MyException("Mobile number doesn't exist");
		if (tbal == null)
			throw new MyException("Mobile number doesn't exist");
		double tmp = (sbal.getAccountBalance() - amount);
		if (tmp >= 0) {
			dao.setAccount(targetMobileNo, tbal.getAccountBalance() + amount);
			dao.setAccount(sourceMobileNo, sbal.getAccountBalance() - amount);
		} else {
			throw new MyException(
					"Minimum balance of Rupees greater than zero should be available...");
		}
//		System.out.println(dao.getAccount(sourceMobileNo));
		dao.loadTransaction(new PrintTransactions(sourceMobileNo, tranType, -amount));
		dao.loadTransaction(new PrintTransactions(targetMobileNo, tranType, amount));
		return dao.getAccount(sourceMobileNo);
	}

	@Override
	public Account depositAmount(String mobileNo, double amount)
			throws InvalidPhoneNumber, InvalidAmount, MyException {
		// TODO Auto-generated method stub
		String tranType = "Deposit";
		Account cDep1 = dao.getAccount(mobileNo);
		if (cDep1 == null)
			throw new MyException("Mobile number doesn't exist");
		boolean c = dao.setAccount(mobileNo, cDep1.getAccountBalance() + amount);
//		System.out.println(c);
		Account cDep = dao.getAccount(mobileNo);
		dao.loadTransaction(new PrintTransactions(mobileNo, tranType, amount));
		if (!c)
			throw new MyException("Unable to deposit");
		else
			return cDep;
	}

	@Override
	public Account withdrawAmount(String mobileNo, double amount)
			throws MyException {
		// TODO Auto-generated method stub
		String tranType = "Withdraw";
		boolean c = false;
		Account cDep1 = dao.getAccount(mobileNo);
		if (cDep1 == null)
			throw new MyException("Mobile number doesn't exist");
		if ((cDep1.getAccountBalance() - amount) >= 0)
			c = dao.setAccount(mobileNo, cDep1.getAccountBalance() - amount);
		Account cDep = dao.getAccount(mobileNo);
		dao.loadTransaction(new PrintTransactions(mobileNo, tranType, amount));
		if (!c)
			throw new MyException("Unable to withdraw");
		else
			return cDep;
	}

	@Override
	public boolean validateAll(Account c) throws MyException, NameException,
			InvalidAmount, InvalidPhoneNumber {
		// TODO Auto-generated method stub
boolean b = false;
		
		if (validateUserName(c.getCustomerName()) == true
				&& validatePhoneNumber(c.getMobileNumber()) == true
				&& validateAmount(c.getAccountBalance()) == true)
			b = true;
		if (!b)
			throw new MyException("Invalid details");
		return b;
	}

}
