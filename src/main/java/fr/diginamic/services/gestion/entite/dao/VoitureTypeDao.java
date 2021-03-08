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

	/*
	 * Cette méthode sélectionne tout les éléments en base de VoitureType, la
	 * méthode renvoie ensuite le résultat de cette query sous forme de liste
	 * 
	 * 
	 */
	public List<VoitureType> selectAll() {

		TypedQuery<VoitureType> query = em.createQuery("SELECT v FROM VoitureType v", VoitureType.class);
		List<VoitureType> voitureTypeList = query.getResultList();

		return voitureTypeList;
	}
	
	public VoitureType findById(Integer id) {
		
		return this.em.find(VoitureType.class, id);	
	}

	/*
	 * On donne en paramètre un objet courant de type VoitureType pour l'inséré en
	 * base l'élément donnée est vérifié dans une class validator dans le service
	 * correspondant
	 * 
	 * @param un objet de type VoitureType
	 */
	public void insert(VoitureType voitureType) {
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		em.persist(voitureType);
		transaction.commit();
		System.out.println("Votre insertion s'est bien réalisé");
	}

	/*
	 * L'objet VoitureType est l'objet courant, il permettra de savoir quelle instance de VoitureType on manipule
	 * 
	 * 
	 * @param objet VoitureType et un formulaire
	 * */
	public void update(VoitureType voitureType, Form form) {
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		String nvType = form.getValue("type de voiture");
		Double nvTarif = Double.parseDouble(form.getValue("tarif"));

		VoitureType voitureBase = em.find(VoitureType.class, voitureType.getId());

		voitureBase.setType(nvType);
		voitureBase.setTarif(nvTarif);
		
		em.persist(voitureBase);
		transaction.commit();
		System.out.println("Votre objet a bien été modifié");
	}
	
	
	/*
	 * En utilisant l'id on retrouve l'objet voulut pour la supprimer ensuite
	 * 
	 * @param id  dqui re présente l'objet courantt
	 * */
	public void delete(Integer id) {
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();

		VoitureType voitureType = em.find(VoitureType.class, id);
		em.remove(voitureType);

		transaction.commit();
		System.out.println("La supression s'est bien déroulé");
	}
}
