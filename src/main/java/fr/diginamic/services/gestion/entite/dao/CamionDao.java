package fr.diginamic.services.gestion.entite.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.services.gestion.entite.Camion;
import fr.diginamic.services.gestion.entite.CamionType;
import fr.diginamic.services.gestion.entite.Voiture;
import fr.diginamic.services.gestion.entite.VoitureType;

public class CamionDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	/*
	 * Cette méthode sélectionne tout les éléments en base de Camion, la méthode
	 * renvoie ensuite le résultat de cette query sous forme de liste
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
	 * On donne en paramètre un objet courant de type Camion pour l'inséré en base
	 * l'élément donnée est vérifié dans une class validator dans le service
	 * correspondant
	 * 
	 * @param un objet de type Camion
	 */
	public void insert(Camion camion) {
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		em.persist(camion);
		transaction.commit();
		System.out.println("Votre insertion s'est bien réalisé");
	}

	/*
	 * L'objet VoitureType est l'objet courant, il permettra de savoir quelle
	 * instance de VoitureType on manipule
	 * 
	 * 
	 * @param objet VoitureType et un formulaire
	 */
	public void update(Camion camion, Form form) {
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		String nvMarque = form.getValue("marque");
		String nvModele = form.getValue("modele");
		String nvImmatriculation = form.getValue("immatriculation");
		Double nvKilometrage = Double.parseDouble(form.getValue("kilometrage"));
		Double nvVolume = Double.parseDouble(form.getValue("volume"));
		CamionType nvCamionType = form.getValue("camion");

		Camion camionBase = em.find(Camion.class, camion.getId());

		camionBase.setMarque(nvMarque);
		camionBase.setModele(nvModele);
		camionBase.setImmatriculation(nvImmatriculation);
		camionBase.setKilometrage(nvKilometrage);
		camionBase.setVolume(nvVolume);
		camionBase.setCamionType(nvCamionType);

		em.persist(camionBase);
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

		Camion camion = em.find(Camion.class, id);
		em.remove(camion);

		transaction.commit();
		System.out.println("La supression s'est bien déroulé");
	}

}
