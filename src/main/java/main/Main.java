package main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.PartidaDAOImpl;
import hibernateConfiguration.HibernateUtil;

public class Main {

	public static void main(String[] args) {
		boolean finalPartida = false, turno = true;
		PartidaDAOImpl partidaDAOImpl = new PartidaDAOImpl();
		
		partidaDAOImpl.iniciarPartida();
		
	}
}