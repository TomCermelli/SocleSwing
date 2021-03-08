package fr.diginamic.services.gestion.entite.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.services.gestion.entite.Voiture;
import fr.diginamic.services.gestion.entite.VoitureType;

public class VoitureDao extends AbstractDao {
	
	private EntityManager em = emf.createEntityManager();

	/*
	 * Cette méthode sélectionne tout les éléments en base de VoitureType, la
	 * méthode renvoie ensuite le résultat de cette query sous forme de liste
	 * 
	 * 
	 */
	public List<Voiture> selectAll() {

		TypedQuery<Voiture> query = em.createQuery("SELECT v FROM Voiture v", Voiture.class);
		List<Voiture> voitureList = query.getResultList();

		return voitureList;
	}

	/*
	 * On donne en paramètre un objet courant de type VoitureType pour l'inséré en
	 * base l'élément donnée est vérifié dans une class validator dans le service
	 * correspondant
	 * 
	 * @param un objet de type VoitureType
	 */
	public void insert(Voiture voiture) {
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		em.persist(voiture);
		transaction.commit();
		System.out.println("Votre insertion s'est bien réalisé");
	}

	/*
	 * L'objet VoitureType est l'objet courant, il permettra de savoir quelle instance de VoitureType on manipule
	 * 
	 * 
	 * @param objet VoitureType et un formulaire
	 * */
	public void update(Voiture voiture, Form form) {
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
//		String nvType = form.getValue("type de voiture");
//		Double nvTarif = Double.parseDouble(form.getValue("tarif"));
//
//		Voiture voitureBase = em.find(Voiture.class, voiture.getId());
//
//		voitureBase.setType(nvType);
//		voitureBase.setTarif(nvTarif);

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

		Voiture voiture = em.find(Voiture.class, id);
		em.remove(voiture);

		transaction.commit();
		System.out.println("La supression s'est bien déroulé");
	}

}
