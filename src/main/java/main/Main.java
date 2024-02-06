package main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import hibernateConfiguration.HibernateUtil;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.close();

		entityManagerFactory.close();		
	}
}