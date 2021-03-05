package fr.diginamic.services.gestion;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.services.exemples.Exemple5FormValidator;
import fr.diginamic.services.exemples.entite.Personne;
import fr.diginamic.services.gestion.entite.CamionType;
import fr.diginamic.services.gestion.entite.Permis;
import fr.diginamic.services.gestion.entite.VoitureType;
import fr.diginamic.services.gestion.entite.dao.CamionTypeDao;
import fr.diginamic.services.gestion.entite.dao.PermisTypeDao;
import fr.diginamic.services.gestion.entite.dao.VoitureTypeDao;
import fr.diginamic.services.gestion.utils.TypeValidator;

public class TypeService extends MenuService {
	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("location-agency");
	
	
	private EntityManager em = emf.createEntityManager();

	private EntityTransaction transaction = em.getTransaction();


	@Override
	public void traitement() {

		VoitureTypeDao voitureTypeDao = new VoitureTypeDao();
		CamionTypeDao camionTypeDao = new CamionTypeDao();
		PermisTypeDao permisTypeDao = new PermisTypeDao();
		
		List<VoitureType> voitureTypeList = voitureTypeDao.selectAll();
		List<CamionType> camionTypeList = camionTypeDao.selectAll();
		List<Permis> permisTypeList = permisTypeDao.selectAll();
		

		console.clear();
		console.print("<h1 class='bg-dark'><center>Création de type <br> Voiture / Camion / Permis</center></h1>");

		String html ="<section>"
					+	 "<section>"
					+ 		"<section>"
					+			"<p>Création d'un type de Voiture</p>"
					+ 			"<button><a class='btn-blue' href='creation()'><img width=25 src='images/plus-blue.png'></a></button>"
					+ 		"</section>"
					+ 	 "</section>"
					+	 "<section>"
					+ 		"<section>"
					+			"<p>Création d'un type de Camion</p>"
					+ 			"<button></button>"
					+ 		"</section>"
					+ 	 "</section>"
					+ 	"<section>"
					+ 		"<section>"
					+			"<p>Création d'un type de Permis</p>"
					+ 			"<button></button>"
					+ 		"</section>"
					+ 	 "</section>"
					+"</section>"
					+"<section>"
					+"<table class='table' cellspacing=0> "
					+	"<tr class='bg-green'><td>&nbsp;</td><td>&nbsp;</td><td>Type</td><td>Tarif</td></tr>";
		
		for (int i=0; i<voitureTypeList.size(); i++) {
					html += "<tr>"
						  + "  <td><a class='btn-blue' href='modification(" + voitureTypeList.get(i).getId() + ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
						  + "  <td><a class='btn-red' href='suppression(" + voitureTypeList.get(i).getId() + ")'><img width=25 src='images/trash-red-xs.png'></a></td>"
						  + "  <td width='150px'>" + voitureTypeList.get(i).getType() + "</td>"
						  + "  <td width='150px'>" + voitureTypeList.get(i).getTarif() + "</td>"
						  +"</tr>";
		}
		
			html += "</table>"
				 + "<table>"
				 + "<tr class='bg-green'><td>&nbsp;</td><td>&nbsp;</td><td>Type</td><td>Tarif</td></tr>";
			
		for(CamionType camion : camionTypeList) {
			html += "<tr>"
					  + "  <td><a class='btn-blue' href='modification(" + camion.getId() + ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
					  + "  <td><a class='btn-red' href='suppression(" + camion.getId() + ")'><img width=25 src='images/trash-red-xs.png'></a></td>"
					  + "  <td width='150px'>" + camion.getType() + "</td>"
					  + "  <td width='150px'>" + camion.getMontant() + "</td>"
					  +"</tr>";
		}
		
		console.print(html);
		
	}
	
	//----------------------------  CRUD des type de voiture ---------------------------- 
	
	// Insertion en base de donnée
	public void creation() {
		TypeValidator validator = new TypeValidator();
		VoitureTypeDao voitureTypeDao = new VoitureTypeDao();
		
		Form form = new Form();
		// On ajoute au formulaire 2 champs de type texte pour permettre de modifier le nom et le prénom du client
				form.addInput(new TextField("Type:", "type de voiture"));
				form.addInput(new TextField("Tarif:", "tarif"));	
		// La console permet de matérialisé notre formulaire au niveau de l'IHM ( notre vue )
		console.input("Création d'un type", form, validator);
		
		// On passe le formulaire remplit par l'utilisateur dans un validator
		// Si tout les champs correspond à ce que l'ont veut on revoit un boolean à true
		boolean valide = validator.validate(form);
		// Si le formulaire a passé la batterie de test, nous envoyé ces données en base
		if(valide) {
			String type_voiture = form.getValue("type de voiture");
			Double tarif = Double.parseDouble(form.getValue("tarif"));	
			
			VoitureType voitureType = new VoitureType(type_voiture, tarif);
			voitureTypeDao.insert(voitureType);
		}
		
		// Une fois la création terminé il faut rappeller la fonction traitement pour avoir un "rafraichissement" des données
		traitement();
		
	}
	
	// Modificiation en base de donnée

	/*
	 * On demande l'id de l'objet courant pour le transmettre dans le dao pour le modifié
	 * 
	 * @param id
	 * */
	public void modification(Integer id) {
		TypeValidator validator = new TypeValidator();
		VoitureTypeDao voitureTypeDao = new VoitureTypeDao();
		
		VoitureType voitureType = em.find(VoitureType.class, id);
		
		
		// On commence par créér le formulaire vide
		Form form = new Form();
				
		// On ajoute au formulaire 2 champs de type texte pour permettre de modifier le nom et le prénom du client
		form.addInput(new TextField("Type:", "type de voiture", voitureType.getType()));
		form.addInput(new TextField("Tarif:", "tarif", Double.toString(voitureType.getTarif())));
		
		// Les règles métier sont vérifiées dans le validator
		boolean valide = console.input("Modification du type : " +voitureType.getType(), form, validator);
		if (valide) {
			
			voitureTypeDao.update(voitureType, form);
		}
		traitement();
	}
	
	// Supression de l'objet courant 
	
	/*
	 * On demande l'id de l'objet courant afin de le supprimer
	 * 
	 * @param id
	 * */
	public void suppression(Integer id) {
		boolean result = console.confirm("Suppression de l'item " + id, "Confirmez-vous la suppression de l'item n°"+id);
		traitement();
	}

}
