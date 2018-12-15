package com.cg.pwa.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.cg.pwa.beans.Account;
import com.cg.pwa.beans.PrintTransactions;
import com.cg.pwa.exception.MyException;
import com.cg.pwa.exception.InvalidPhoneNumber;



public class AccountDaoImpl implements AccountDao 
{

	EntityManager em;

	public AccountDaoImpl() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU-Oracle");
		em = emf.createEntityManager();
	}



	@Override
	public boolean setAccount(String mobileNo, double amount) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		Account cShow = em.find(Account.class, mobileNo);
		cShow.setAccountBalance(amount);
		em.merge(cShow);
		em.getTransaction().commit();
		return true;
	}

	
	
	@Override
	public List<PrintTransactions> getTransactions(String mobileNo)
			throws MyException, InvalidPhoneNumber {
		// TODO Auto-generated method stub
		List<PrintTransactions> tranList = null;
		em.getTransaction().begin();
		Query query = em.createQuery("from PrintTransactions where mobileNumber = :mobNo");
		query.setParameter("mobNo", mobileNo);
		tranList = query.getResultList();
		if(tranList == null)
			throw new MyException("No transactions are made yet");
		em.getTransaction().commit();
		return tranList;
	}

	@Override
	public void loadTransaction(PrintTransactions pt) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(pt);
		em.getTransaction().commit();
	}

	@Override
	public Account createAccount(Account c) throws MyException {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		return c;
	}

	@Override
	public Account getAccount(String mobileno) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		Account cShow = em.find(Account.class, mobileno);
		em.getTransaction().commit();
		return cShow;
	}

}