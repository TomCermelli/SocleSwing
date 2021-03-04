package fr.diginamic.services.gestion.entite.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AbstractDao {
	
	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Locationdb");

}
