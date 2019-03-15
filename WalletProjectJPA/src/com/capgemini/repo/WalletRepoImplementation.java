package com.capgemini.repo;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.capgemini.bean.Customer;
import com.capgemini.bean.Wallet;
import com.capgemini.util.CollectionUtil;


public class WalletRepoImplementation implements WalletRepo {
	EntityManagerFactory entityManagerFactory ;
	public WalletRepoImplementation() {
		super();
		new CollectionUtil();
		
	}

	@Override
	public boolean save(Customer customer) {
		
		
		entityManagerFactory = CollectionUtil.getFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(customer);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

	@Override
	public Customer findOne(String mobileNumber) {
		
		entityManagerFactory = CollectionUtil.getFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Customer customer1 =entityManager.find(Customer.class, mobileNumber);
		entityManager.close();
		return customer1;

	}

	@Override
	public void update(String mobileNumber, BigDecimal amount) {
		entityManagerFactory = CollectionUtil.getFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Customer customer1 =entityManager.find(Customer.class, mobileNumber);
		customer1.setWallet(new Wallet(amount));
		entityManager.getTransaction().commit();
		entityManager.close();

	}

}
