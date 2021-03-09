package fr.diginamic.services.gestion.entite.dao;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.services.gestion.entite.Adresse;
import fr.diginamic.services.gestion.entite.Client;

public class ClientDao extends AbstractDao {
	private EntityManager em = emf.createEntityManager();

	/*
	 * Cette méthode sélectionne tout les éléments en base de Client, la
	 * méthode renvoie ensuite le résultat de cette query sous forme de liste
	 * 
	 * 
	 */
	public List<Client> selectAll() {

		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c", Client.class);
		List<Client> clientList = query.getResultList();

		return clientList;
	}
	
	public Client findById(Integer id) {
		
		return this.em.find(Client.class, id);	
	}

	/*
	 * On donne en paramètre un objet courant de type client pour l'inséré en
	 * base l'élément donnée est vérifié dans une class validator dans le service
	 * correspondant
	 * 
	 * @param un objet de type client
	 */
	public void insert(Client client) {
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		em.persist(client);
		transaction.commit();
		System.out.println("Votre insertion s'est bien réalisé");
	}

	/*
	 * L'objet client est l'objet courant, il permettra de savoir quelle instance de client on manipule
	 * 
	 * 
	 * @param objet client et un formulaire
	 * */
	public void update(Client client, Form form) {
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		String nvNom = form.getValue("nom");
		String nvPrenom = form.getValue("prenom");

		int nvNumeroRue = Integer.parseInt(form.getValue("numero rue"));
		String nvLibelleRue = form.getValue("libelle");
		int nvCodePostal = Integer.parseInt(form.getValue("code postal"));
		String nvVille = form.getValue("ville");
		int nvNumeroTel = Integer.parseInt(form.getValue("numero tel"));
		String nvEmail = form.getValue("email");
		
		
		Adresse adresse = new Adresse(nvNumeroRue, nvLibelleRue, nvCodePostal, nvVille, nvNumeroTel, nvEmail);
		

		Client clientBase = em.find(Client.class, client.getId());

		clientBase.setNom(nvNom);;
		clientBase.setPrenom(nvPrenom);
		clientBase.setAdresse(adresse);;
		
		em.persist(clientBase);
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

		Client client = em.find(Client.class, id);
		em.remove(client);

		transaction.commit();
		System.out.println("La supression s'est bien déroulé");
	}
}
