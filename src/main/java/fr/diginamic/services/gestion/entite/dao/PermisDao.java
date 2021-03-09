package fr.diginamic.services.gestion.entite.dao;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.services.gestion.entite.Permis;

public class PermisDao extends AbstractDao {
	private EntityManager em = emf.createEntityManager();

	/*
	 * Cette méthode sélectionne tout les éléments en base de Permis, la
	 * méthode renvoie ensuite le résultat de cette query sous forme de liste
	 * 
	 * 
	 */
	public List<Permis> selectAll() {

		TypedQuery<Permis> query = em.createQuery("SELECT p FROM Permis p", Permis.class);
		List<Permis> permisList = query.getResultList();

		return permisList;
	}
	
	public Permis findById(Integer id) {
		
		return this.em.find(Permis.class, id);	
	}

	/*
	 * On donne en paramètre un objet courant de type Permis pour l'inséré en
	 * base l'élément donnée est vérifié dans une class validator dans le service
	 * correspondant
	 * 
	 * @param un objet de type Permis
	 */
	public void insert(Permis permis) {
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		em.persist(permis);
		transaction.commit();
		System.out.println("Votre insertion s'est bien réalisé");
	}

	/*
	 * L'objet Permis est l'objet courant, il permettra de savoir quelle instance de Permis on manipule
	 * 
	 * 
	 * @param objet Permis et un formulaire
	 * */
	public void update(Permis permis, Form form) {
		EntityTransaction transaction = em.getTransaction();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.FRANCE);

		transaction.begin();
		String nvType = form.getValue("permis type");
		int nvNumero = Integer.parseInt(form.getValue("numero"));
		//Date nvDate = form.getValue("numero");
		

		Permis permisBase = em.find(Permis.class, permis.getId());

		permisBase.setType(nvType);
		permisBase.setNumero(nvNumero);
		permisBase.setDateObtention(null);;
		
		em.persist(permisBase);
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

		Permis permis = em.find(Permis.class, id);
		em.remove(permis);

		transaction.commit();
		System.out.println("La supression s'est bien déroulé");
	}

}
