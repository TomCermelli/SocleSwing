package fr.diginamic.services.gestion.entite.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.services.exemples.entite.Personne;
import fr.diginamic.services.gestion.entite.VoitureType;

public class VoitureTypeDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	private EntityTransaction transaction = em.getTransaction();

	public List<VoitureType> selectAll() {

		TypedQuery<VoitureType> query = em.createQuery("SELECT v FROM VoitureType v", VoitureType.class);
		List<VoitureType> voitureTypeList = query.getResultList();

		return voitureTypeList;
	}

	public void insert(VoitureType voitureType) {

		transaction.begin();
		em.persist(voitureType);
		transaction.commit();
		System.out.println("Votre insertion s'est bien réalisé");
	}

	public void update(VoitureType voitureType, Form form) {
	
		transaction.begin();

		String nvType = form.getValue("type de voiture");
		Double nvTarif = Double.parseDouble(form.getValue("tarif"));

		voitureType.setType(nvType);
		voitureType.setTarif(nvTarif);

		transaction.commit();

		System.out.println("Votre objet a bien été modifié");
	}

	public void delete(int id) {

		VoitureType voitureType = em.find(VoitureType.class, id);

		transaction.begin();
		em.remove(voitureType);
		transaction.commit();

		System.out.println("La supression s'est bien déroulé");

	}
}
