package fr.diginamic.services.gestion.entite.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.services.gestion.entite.CamionType;
import fr.diginamic.services.gestion.entite.Permis;

public class PermisTypeDao extends AbstractDao {
	private EntityManager em = emf.createEntityManager();

	private EntityTransaction transaction = em.getTransaction();
	
	public List<Permis> selectAll() {

		TypedQuery<Permis> query = em.createQuery("SELECT p FROM Permis p", Permis.class);
		List<Permis> permisTypeList = query.getResultList();

		return permisTypeList;
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
