package fr.diginamic.services.gestion.entite.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.services.gestion.entite.Camion;
import fr.diginamic.services.gestion.entite.Voiture;

public class CamionDao extends AbstractDao {
	
	private EntityManager em = emf.createEntityManager();

	/*
	 * Cette méthode sélectionne tout les éléments en base de VoitureType, la
	 * méthode renvoie ensuite le résultat de cette query sous forme de liste
	 * 
	 * 
	 */
	public List<Camion> selectAll() {

		TypedQuery<Camion> query = em.createQuery("SELECT c FROM Camion c", Camion.class);
		List<Camion> camionList = query.getResultList();

		return camionList;
	}
	
	public Camion findById(Integer id) {

		return this.em.find(Camion.class, id);
	}
	
	

	/*
	 * On donne en paramètre un objet courant de type VoitureType pour l'inséré en
	 * base l'élément donnée est vérifié dans une class validator dans le service
	 * correspondant
	 * 
	 * @param un objet de type VoitureType
	 */
	public void insert(Camion camion) {
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		em.persist(camion);
		transaction.commit();
		System.out.println("Votre insertion s'est bien réalisé");
	}

	/*
	 * L'objet VoitureType est l'objet courant, il permettra de savoir quelle instance de VoitureType on manipule
	 * 
	 * 
	 * @param objet VoitureType et un formulaire
	 * */
	public void update(Camion camion, Form form) {
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

		Camion camion = em.find(Camion.class, id);
		em.remove(camion);

		transaction.commit();
		System.out.println("La supression s'est bien déroulé");
	}

}
