package com.capgemini.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CollectionUtil {

	private static EntityManagerFactory entityManagerFactory;

	public CollectionUtil() {

		entityManagerFactory = Persistence.createEntityManagerFactory("Wallet");
	}

	public static EntityManagerFactory getFactory() {
		return entityManagerFactory;
	}
}