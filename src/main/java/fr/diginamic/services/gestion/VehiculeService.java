package fr.diginamic.services.gestion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.h2.engine.SysProperties;

import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.ComboBox;
import fr.diginamic.composants.ui.DateField;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.Selectable;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.services.exemples.entite.Vehicule;
import fr.diginamic.services.gestion.entite.Camion;
import fr.diginamic.services.gestion.entite.CamionType;
import fr.diginamic.services.gestion.entite.Voiture;
import fr.diginamic.services.gestion.entite.VoitureType;
import fr.diginamic.services.gestion.entite.dao.CamionDao;
import fr.diginamic.services.gestion.entite.dao.CamionTypeDao;
import fr.diginamic.services.gestion.entite.dao.VoitureDao;
import fr.diginamic.services.gestion.entite.dao.VoitureTypeDao;
import fr.diginamic.services.gestion.utils.TypeValidator;
import fr.diginamic.services.gestion.utils.VoitureValidator;

public class VehiculeService extends MenuService {
public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("location-agency");
	
	
	private EntityManager em = emf.createEntityManager();

	private EntityTransaction transaction = em.getTransaction();

	public void traitement() {

		VoitureDao voitureDao = new VoitureDao();
		CamionDao camionDao = new CamionDao();

		List<Voiture> voitureList = voitureDao.selectAll();
		List<Camion> camionList = camionDao.selectAll();

		console.clear();
		console.println("<h1 class='bg-dark'><center>Création de Vehicule<br></center></h1>");

		String html ="<section class='d-flex'>"
				+	 "<section>"
				+ 		"<section class='d-flex fd-vertical'>"
				+			"<p>Création d'une voiture</p>"
				+ 			"<button><a class='btn-blue' href='creationVoiture()'><img width=25 src='images/plus-blue.png'></a></button>"
				+ 		"</section>"
				+ 	 "</section>"
				+	 "<section class='d-flex fd-vertical'>"
				+ 		"<section>"
				+			"<p>Création d'un camion</p>"
				+ 			"<button><a class='btn-blue' href='creationCamion()'><img width=25 src='images/plus-blue.png'></a></button>"
				+ 		"</section>"
				+ 	 "</section>"
				+"</section>"
				+"<table class='table' cellspacing=0> "
				+ "<tr class='bg-green'><td>&nbsp;</td><td>&nbsp;</td><td>Marque</td>"
				+ "<td>Modele</td>"
				+ "<td>Statut</td>"
				+ "<td>Immatriculation</td>"
				+ "<td>Kilométrage</td>"
				+ "<td>NombrePlace</td>"
				+"<td>Type de voiture</td>"
				+"</tr>";

		for (Voiture voiture : voitureList) {
			html += "<tr>"
					  + "  <td><a class='btn-blue' href='modificationVoiture(" + voiture.getId() + ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
					  + "  <td><a class='btn-red' href='suppressionVoiture(" + voiture.getId() + ")'><img width=25 src='images/trash-red-xs.png'></a></td>"
					  + "  <td width='150px'>" + voiture.getMarque() + "</td>"
					  + "  <td width='150px'>" + voiture.getModele() + "</td>"
					  + "  <td width='150px'>" + voiture.getStatut() + "</td>"
					  + "  <td width='150px'>" + voiture.getImmatriculation() + "</td>"
					  + "  <td width='150px'>" + voiture.getKilometrage() + "km</td>"
					  + "  <td width='150px'>" + voiture.getNombrePlace() + "</td>"
					  + "  <td width='150px'>" + voiture.getVoitureType() + "</td>" 
					  +"</tr>";
		}

		html += "</table>" 
				+ "<section>" 
				+"<table class='table' cellspacing=0> "
				+	"<tr class='bg-green'><td>&nbsp;</td><td>&nbsp;</td><td>Marque</td>"
				+ "<td>Modele</td>"
				+ "<td>Statut</td>"
				+ "<td>Immatriculation</td>"
				+ "<td>Kilométrage</td>"
				+ "<td>NombrePlace</td>"
				+"<td>Type de voiture</td>"
				+"</tr>";

		for (Camion camion : camionList) {
			html += "<tr>"
					  + "  <td><a class='btn-blue' href='modificationVoiture(" + camion.getId() + ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
					  + "  <td><a class='btn-red' href='suppressionVoiture(" + camion.getId() + ")'><img width=25 src='images/trash-red-xs.png'></a></td>"
					  + "  <td width='150px'>" + camion.getMarque() + "</td>"
					  + "  <td width='150px'>" + camion.getModele() + "</td>"
					  + "  <td width='150px'>" + camion.getStatut() + "</td>"
					  + "  <td width='150px'>" + camion.getImmatriculation() + "</td>"
					  + "  <td width='150px'>" + camion.getKilometrage() + "km</td>"
					  + "  <td width='150px'>" + camion.getVolume() + "</td>"
					  + "  <td width='150px'>" + camion.getCamionType() + "</td>" 
					  +"</tr>";
		}

	   html += "</table>";

		console.println(html);
	}

	public void creationVoiture() {
		VoitureValidator validator = new VoitureValidator();
		VoitureDao voitureDao = new VoitureDao();
		
		VoitureTypeDao voitureTypeDao = new VoitureTypeDao();
		List<VoitureType> voitureTypeList = voitureTypeDao.selectAll();
		
		Form form = new Form();
		// On ajoute au formulaire 2 champs de type texte.
		form.addInput(new TextField("Marque:", "marque"));
		form.addInput(new TextField("Modèle:", "modele"));
		form.addInput(new TextField("Immatriculation:", "immatriculation"));
		form.addInput(new TextField("Kilométrage:", "kilometrage"));
		form.addInput(new TextField("Nombre de place:", "nombre de place"));
		
		List<Selectable> voitureTypeSelectable = new ArrayList<>();
		for(VoitureType voiture : voitureTypeList) {
			voitureTypeSelectable.add(voiture);
		}
		
		// Champ de type liste de sélection
		form.addInput(new ComboBox("Type de voiture:", "voiture", voitureTypeSelectable));
		boolean valide = console.input("Création d'une Voiture", form, validator);
		
		if(valide) {
			String nvMarque = form.getValue("marque");
			String nvModele = form.getValue("modele");
			String nvImmatriculation = form.getValue("immatriculation");
			Double nvKilometrage = Double.parseDouble(form.getValue("kilometrage"));
			int nvNombrePlace = Integer.parseInt(form.getValue("nombre de place"));
			VoitureType nvVoitureType = form.getValue("voiture");
			
			Voiture voiture = new Voiture(nvMarque, nvModele, nvImmatriculation, nvKilometrage, nvNombrePlace, nvVoitureType);
			voitureDao.insert(voiture);
			
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
			VoitureValidator validator = new VoitureValidator();
			VoitureDao voitureDao = new VoitureDao();
			Voiture voitureId = voitureDao.findById(id);	
			
			// On commence par créér le formulaire vide
			Form form = new Form();
					
			// On ajoute au formulaire 2 champs de type texte pour permettre de modifier le nom et le prénom du client
			form.addInput(new TextField("Marque:", "marque"));
			form.addInput(new TextField("Modèle:", "modele"));
			form.addInput(new TextField("Immatriculation:", "immatriculation"));
			form.addInput(new TextField("Kilométrage:", "kilometrage"));
			form.addInput(new TextField("Nombre de place:", "nombre de place"));
			
			// Les règles métier sont vérifiées dans le validator
			boolean valide = console.input("Modification de la voiture : "+voitureId.getModele()+ " de la marque " +voitureId.getMarque() , form, validator);
			if (valide) {
				voitureDao.update(voitureId, form);
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
			VoitureDao voitureDao = new VoitureDao();
			boolean result = console.confirm("Suppression de l'item " + id, "Confirmez-vous la suppression de l'item n°"+id);
			if(result) {
				voitureDao.delete(id);
			}
			traitement();
		}

}
