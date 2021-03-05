package fr.diginamic.services.exemples;

import java.awt.Color;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.db.SqlUtils;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.services.exemples.entite.Personne;
import fr.diginamic.services.gestion.entite.VoitureType;

public class Exemple5Service extends MenuService {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2-mem");

	private void initDatabase() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Personne> query = em.createQuery("SELECT p FROM Personne p", Personne.class);
		List<Personne> clients = query.getResultList();
		if (clients.size()==0) {
			SqlUtils.executeFile("exemple.sql", em);
		}
	}

	@Override
	public void traitement() {

		// La méthode initDatabase permet ici d'initialiser la base avec des données de
		// tests
		initDatabase();

		// Exemple
		EntityManager em = emf.createEntityManager();
		TypedQuery<Personne> query = em.createQuery("SELECT p FROM Personne p", Personne.class);
		List<Personne> clients = query.getResultList();

		console.clear();
		console.print("<h1 class='bg-green'><center>Liste des clients</center></h1>");

		String html = "<table cellspacing=0>"
				+ "<tr class='bg-green'><td>&nbsp;</td><td>&nbsp;</td><td>Nom</td><td>Prénom</td></tr>";
		for (Personne c : clients) {
			html += "<tr>"
				  + "  <td><a class='btn-blue' href='modifier(" + c.getId() + ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
				  + "  <td><a class='btn-red' href='supprimer(" + c.getId() + ")'><img width=25 src='images/trash-red-xs.png'></a></td>"
				  + "  <td width='150px'>" + c.getNom() + "</td>"
				  + "  <td width='150px'>" + c.getPrenom() + "</td>"
				  +"</tr>";
		}
		html += "</table>";

		console.print(html);
	}

	/** Méthode appelée lorsque l'utilisateur clique sur une icone de modification
	 * dans la table des clients.
	 * @param id identifiant du client à modifier.
	 */
	public void modifier(Long id) {
		
		EntityManager em = emf.createEntityManager();
		Personne c = em.find(Personne.class, id);
		
		// On commence par créér le formulaire vide
		Form form = new Form();
				
		// On ajoute au formulaire 2 champs de type texte pour permettre de modifier le nom et le prénom du client
		form.addInput(new TextField("Nom:", "champ1", c.getNom()));
		form.addInput(new TextField("Prénom:", "champ2", c.getPrenom()));
		
		// Les règles métier sont vérifiées dans le validator
		Exemple5FormValidator validator = new Exemple5FormValidator();
		
		boolean valide = console.input("Modification du client "+c.getPrenom()+" "+c.getNom(), form, validator);
		if (valide) {
			
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			String nvNom = form.getValue("champ1");
			String nvPrenom = form.getValue("champ2");
			c.setNom(nvNom);
			c.setPrenom(nvPrenom);
			transaction.commit();

			traitement();
		}
	}

	/** Méthode appelée lorsque l'utilisateur clique sur une icone de suppression
	 * dans la table des clients
	 * @param id identifiant du client à supprimer.
	 */
	public void supprimer(Long id) {
		boolean result = console.confirm("Suppression de l'item " + id, "Confirmez-vous la suppression de l'item n°"+id);
		console.println(""+result);
	}
	


}
