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
	 * Cette méthode sélectionne tout les éléments en base de Voiture, la
	 * méthode renvoie ensuite le résultat de cette query sous forme de liste
	 * 
	 * 
	 */
	public List<Voiture> selectAll() {

		TypedQuery<Voiture> query = em.createQuery("SELECT v FROM Voiture v", Voiture.class);
		List<Voiture> voitureList = query.getResultList();

		return voitureList;
	}

	public Voiture findById(Integer id) {

		return this.em.find(Voiture.class, id);
	}

	/*
	 * On donne en paramètre un objet courant de type VoitureType pour l'inséré en
	 * base l'élément donnée est vérifié dans une class validator dans le service
	 * correspondant
	 * 
	 * @param un objet de type Voiture
	 */
	public void insert(Voiture voiture) {
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		em.persist(voiture);
		transaction.commit();
		System.out.println("Votre insertion s'est bien réalisé");
	}

	/*
	 * L'objet Voiture est l'objet courant, il permettra de savoir quelle
	 * instance de VoitureType on manipule
	 * 
	 * 
	 * @param objet Voiture et un formulaire
	 */
	public void update(Voiture voiture, Form form) {
		EntityTransaction transaction = em.getTransaction();
		VoitureTypeDao voitureTypeDao = new VoitureTypeDao();

		transaction.begin();
		String nvMarque = form.getValue("marque");
		String nvModele = form.getValue("modele");
		String nvImmatriculation = form.getValue("immatriculation");
		Double nvKilometrage = Double.parseDouble(form.getValue("kilometrage"));
		int nvNombrePlace = Integer.parseInt(form.getValue("nombre de place"));
		VoitureType nvVoitureType = voitureTypeDao.findById(Integer.parseInt(form.getValue("voiture type")));

		Voiture voitureBase = em.find(Voiture.class, voiture.getId());

		voitureBase.setMarque(nvMarque);
		voitureBase.setModele(nvModele);
		voitureBase.setImmatriculation(nvImmatriculation);
		voitureBase.setKilometrage(nvKilometrage);
		voitureBase.setNombrePlace(nvNombrePlace);
		voitureBase.setVoitureType(nvVoitureType);

		em.persist(voitureBase);
		transaction.commit();
		System.out.println("Votre objet a bien été modifié");
	}

	/*
	 * En utilisant l'id on retrouve l'objet voulut pour la supprimer ensuite
	 * 
	 * @param id dqui re présente l'objet courantt
	 */
	public void delete(Integer id) {
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();

		Voiture voiture = em.find(Voiture.class, id);
		em.remove(voiture);

		transaction.commit();
		System.out.println("La supression s'est bien déroulé");
	}

}
