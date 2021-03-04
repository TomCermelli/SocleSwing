package fr.diginamic.services.gestion.entite.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.services.gestion.entite.VoitureType;

public class VoitureTypeDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	private EntityTransaction transaction = em.getTransaction();

	public void insertVoitureType(Form form) throws IOException {
		String type_voiture = form.getValue("type de voiture");
		Double tarif = Double.parseDouble(form.getValue("tarif"));

		transaction.begin();

		VoitureType voitureTypeInsert = new VoitureType(type_voiture, tarif);
		em.persist(voitureTypeInsert);

		transaction.commit();
	}
}
