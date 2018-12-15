package com.cg.pwa.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.cg.pwa.exception.InvalidAmount;
import com.cg.pwa.exception.InvalidPhoneNumber;
import com.cg.pwa.exception.NameException;
import com.cg.pwa.service.AccountServiceImpl;

public class TestClass 
{
	@Test
	public void ValidateNameTrue() throws NameException
	{
		AccountServiceImpl bs = new AccountServiceImpl();
		assertEquals(true, bs.validateUserName("Bhuvana"));
	}

	@Test 
	public void ValidateNameV2() throws NameException
	{
		AccountServiceImpl bs = new AccountServiceImpl();
		String name="Sanjay6767";
		boolean result= bs.validateUserName(name);
		Assert.assertEquals(false,result);
	}

	@Test 
	public void ValidateNameV3() throws NameException
	{
		AccountServiceImpl bs = new AccountServiceImpl();
		String name="6767";
		boolean result= bs.validateUserName(name);
		Assert.assertEquals(false,result);
	}
	
	@Test 
	public void ValidateNameV4() throws NameException
	{
		AccountServiceImpl bs = new AccountServiceImpl();
		String name="bhuvana";
		boolean result= bs.validateUserName(name);
		Assert.assertEquals(false,result);
	}
	
	@Test 
	public void ValidateNameV5() throws NameException
	{
		AccountServiceImpl bs = new AccountServiceImpl();
		String name="*/-+";
		boolean result= bs.validateUserName(name);
		Assert.assertEquals(false,result);
	}
	
	@Test
	public void ValidatePhonNumberTrue() throws InvalidPhoneNumber
	{
		AccountServiceImpl bs = new AccountServiceImpl();
		assertEquals(true, bs.validatePhoneNumber("7386759303"));
	}

	@Test
	public void ValidatePhoneNumber() throws InvalidPhoneNumber
	{
		AccountServiceImpl bs = new AccountServiceImpl();
		String mobNo="ABCD91828288";
		boolean result= bs.validatePhoneNumber(mobNo);
		Assert.assertEquals(false,result);
	}

	@Test
	public void ValidatePhoneNumberP1() throws InvalidPhoneNumber
	{
		AccountServiceImpl bs = new AccountServiceImpl();
		String mobNo="5236";
		boolean result= bs.validatePhoneNumber(mobNo);
		Assert.assertEquals(false,result);
	}


	@Test
	public void ValidateAmountTrue() throws InvalidAmount
	{
		AccountServiceImpl bs = new AccountServiceImpl();
		assertEquals(true, bs.validateAmount(100));
	}

	@Test (expected = InvalidAmount.class)
	public void ValidateAmount() throws InvalidAmount
	{
		AccountServiceImpl bs = new AccountServiceImpl();
		double amt= -400;
		boolean result= bs.validateAmount(amt);
	}

	@Test (expected = InvalidAmount.class)
	public void ValidateAmount1() throws InvalidAmount
	{
		AccountServiceImpl bs = new AccountServiceImpl();
		double amt= 0;
		boolean result= bs.validateAmount(amt);
	}

	
}
