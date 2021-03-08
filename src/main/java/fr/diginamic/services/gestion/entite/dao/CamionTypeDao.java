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


	
	/*
	 * Cette méthode sélectionne tout les éléments en base de CamionType, la
	 * méthode renvoie ensuite le résultat de cette query sous forme de liste
	 * 
	 * 
	 */
	public List<CamionType> selectAll() {

		TypedQuery<CamionType> query = em.createQuery("SELECT c FROM CamionType c", CamionType.class);
		List<CamionType> camionTypeList = query.getResultList();

		return camionTypeList;
	}
	
	/*
	 * On donne en paramètre un objet courant de type CamionType pour l'inséré en
	 * base l'élément donnée est vérifié dans une class validator dans le service
	 * correspondant
	 * 
	 * @param un objet de type VoitureType
	 */
	public void insert(CamionType camionType) {
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		em.persist(camionType);
		transaction.commit();
		System.out.println("Votre insertion s'est bien réalisé");
	}

	/*
	 * L'objet CamionType est l'objet courant, il permettra de savoir quelle instance de VoitureType on manipule
	 * 
	 * 
	 * @param objet VoitureType et un formulaire
	 * */
	public void update(CamionType camionType, Form form) {
	
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		String nvType = form.getValue("type_camion");
		Double nvTarif = Double.parseDouble(form.getValue("tarif"));

		CamionType camionBase = em.find(CamionType.class, camionType.getId());

		camionBase.setType(nvType);
		camionBase.setMontant(nvTarif);

		transaction.commit();
		System.out.println("Votre objet a bien été modifié");
	}

	public void delete(int id) {
		EntityTransaction transaction = em.getTransaction();

		CamionType camionType = em.find(CamionType.class, id);

		transaction.begin();
		em.remove(camionType);
		transaction.commit();

		System.out.println("La supression s'est bien déroulé");

	}

}
