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
import fr.diginamic.services.gestion.utils.TypeValidatorCamion;

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

		String html ="<section class='d-flex'>"
					+	 "<section>"
					+ 		"<section class='d-flex fd-vertical'>"
					+			"<p>Création d'un type de Voiture</p>"
					+ 			"<button><a class='btn-blue' href='creationVoiture()'><img width=25 src='images/plus-blue.png'></a></button>"
					+ 		"</section>"
					+ 	 "</section>"
					+	 "<section class='d-flex fd-vertical'>"
					+ 		"<section>"
					+			"<p>Création d'un type de Camion</p>"
					+ 			"<button><a class='btn-blue' href='creationCamion()'><img width=25 src='images/plus-blue.png'></a></button>"
					+ 		"</section>"
					+ 	 "</section>"
					+"</section>"
					+"<table class='table' cellspacing=0> "
					+	"<tr class='bg-green'><td>&nbsp;</td><td>&nbsp;</td><td>Type</td><td>Tarif</td></tr>";
		
		for (VoitureType voiture : voitureTypeList) {
					html += "<tr>"
						  + "  <td><a class='btn-blue' href='modificationVoiture(" + voiture.getId() + ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
						  + "  <td><a class='btn-red' href='suppressionVoiture(" + voiture.getId() + ")'><img width=25 src='images/trash-red-xs.png'></a></td>"
						  + "  <td width='150px'>" + voiture.getType() + "</td>"
						  + "  <td width='150px'>" + voiture.getTarif() + "</td>"
						  +"</tr>";
		}
		
			html += "</table>"
				 + "<section>"
				 + "<table>"
				 + "<tr class='bg-green'><td>&nbsp;</td><td>&nbsp;</td><td>Type</td><td>Tarif</td></tr>";
			
		for(CamionType camion : camionTypeList) {
			html += "<tr>"
					  + "  <td><a class='btn-blue' href='modificationCamion(" + camion.getId() + ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
					  + "  <td><a class='btn-red' href='suppressionCamion(" + camion.getId() + ")'><img width=25 src='images/trash-red-xs.png'></a></td>"
					  + "  <td width='150px'>" + camion.getType() + "</td>"
					  + "  <td width='150px'>" + camion.getMontant() + "</td>"
					  +"</tr>";
		}
		
		html += "</table>";
		
		console.print(html);
	}
	
	//----------------------------  CRUD des type de voiture ---------------------------- 
	
	// Insertion en base de donnée
	public void creationVoiture() {
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
	public void modificationVoiture(Integer id) {
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
	public void suppressionVoiture(Integer id) {
		VoitureTypeDao voitureTypeDao = new VoitureTypeDao();
		boolean result = console.confirm("Suppression de l'item " + id, "Confirmez-vous la suppression de l'item n°"+id);
		if(result) {
			voitureTypeDao.delete(id);
		}
		traitement();
	}
	
	//----------------------------  CRUD des type de camion ---------------------------- 
	
		// Insertion en base de donnée
		public void creationCamion() {
			TypeValidatorCamion validator = new TypeValidatorCamion();
			CamionTypeDao camionTyepDao = new CamionTypeDao();
			
			Form form = new Form();
			// On ajoute au formulaire 2 champs de type texte pour permettre de modifier le nom et le prénom du client
					form.addInput(new TextField("Type:", "type_camion"));
					form.addInput(new TextField("Montant:", "montant"));	
			// La console permet de matérialisé notre formulaire au niveau de l'IHM ( notre vue )
			console.input("Création d'un type", form, validator);
			
			// On passe le formulaire remplit par l'utilisateur dans un validator
			// Si tout les champs correspond à ce que l'ont veut on revoit un boolean à true
			boolean valide = validator.validate(form);
			// Si le formulaire a passé la batterie de test, nous envoyé ces données en base
			if(valide) {
				String type_camion = form.getValue("type_camion");
				Double montant = Double.parseDouble(form.getValue("montant"));	
				
				CamionType camionType = new CamionType(type_camion, montant);
				camionTyepDao.insert(camionType);
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
		public void modificationCamion(Integer id) {
			TypeValidatorCamion validator = new TypeValidatorCamion();
			CamionTypeDao camionTyepDao = new CamionTypeDao();
			
			CamionType camionType = em.find(CamionType.class, id);
			
			
			// On commence par créér le formulaire vide
			Form form = new Form();
					
			// On ajoute au formulaire 2 champs de type texte pour permettre de modifier le nom et le prénom du client
			form.addInput(new TextField("Type:", "type de camion", camionType.getType()));
			form.addInput(new TextField("Montant:", "montant", Double.toString(camionType.getMontant())));
			
			// Les règles métier sont vérifiées dans le validator
			boolean valide = console.input("Modification du type : " +camionType.getType(), form, validator);
			if (valide) {
				
				camionTyepDao.update(camionType, form);
			}
			traitement();
		}
		
		// Supression de l'objet courant 
		
		/*
		 * On demande l'id de l'objet courant afin de le supprimer
		 * 
		 * @param id
		 * */
		public void suppressionCamion(Integer id) {
			CamionTypeDao camionTyepDao = new CamionTypeDao();
			boolean result = console.confirm("Suppression de l'item " + id, "Confirmez-vous la suppression de l'item n°"+id);
			if(result) {
				camionTyepDao.delete(id);
			}
			traitement();
		}

}
