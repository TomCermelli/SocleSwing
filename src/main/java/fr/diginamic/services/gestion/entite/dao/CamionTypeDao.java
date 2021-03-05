package fr.diginamic.services.gestion.entite.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.services.gestion.entite.CamionType;
import fr.diginamic.services.gestion.entite.VoitureType;

public class CamionTypeDao extends AbstractDao {
	
	private EntityManager em = emf.createEntityManager();

	private EntityTransaction transaction = em.getTransaction();

	public List<CamionType> selectAll() {

		TypedQuery<CamionType> query = em.createQuery("SELECT c FROM CamionType c", CamionType.class);
		List<CamionType> camionTypeList = query.getResultList();

		return camionTypeList;
	}

	public void insert(CamionType camionType) {

		transaction.begin();
		em.persist(camionType);
		transaction.commit();
		System.out.println("Votre insertion s'est bien réalisé");
	}

	public void update(CamionType camionType, Form form) {
	
		transaction.begin();

		String nvType = form.getValue("type de camion");
		Double nvTarif = Double.parseDouble(form.getValue("tarif"));

		camionType.setType(nvType);
		camionType.setMontant(nvTarif);

		transaction.commit();

		System.out.println("Votre objet a bien été modifié");
	}

	public void delete(int id) {

		CamionType camionType = em.find(CamionType.class, id);

		transaction.begin();
		em.remove(camionType);
		transaction.commit();

		System.out.println("La supression s'est bien déroulé");

	}

}
