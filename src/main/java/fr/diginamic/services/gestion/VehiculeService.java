package fr.diginamic.services.gestion;

import java.util.ArrayList;
import java.util.List;

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
import fr.diginamic.services.gestion.utils.VoitureValidator;

public class VehiculeService extends MenuService {

	public void traitement() {

		VoitureDao voitureDao = new VoitureDao();
		CamionDao camionDao = new CamionDao();

		List<Voiture> voitureList = voitureDao.selectAll();
		List<Camion> camionList = camionDao.selectAll();

		console.clear();
		console.print("<h1 class='bg-dark'><center>Création de Vehicule<br></center></h1>");

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
				+	"<tr class='bg-green'><td>&nbsp;</td><td>&nbsp;</td><td>Type</td><td>Tarif</td></tr>";

		for (Voiture voiture : voitureList) {
			html += "<tr>"
					  + "  <td><a class='btn-blue' href='modificationVoiture(" + voiture.getId() + ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
					  + "  <td><a class='btn-red' href='suppressionVoiture(" + voiture.getId() + ")'><img width=25 src='images/trash-red-xs.png'></a></td>"
					  + "  <td width='150px'>" + voiture.getMarque() + "</td>"
					  + "  <td width='150px'>" + voiture.getModele() + "</td>"
					  + "  <td width='150px'>" + voiture.getImmatriculation() + "</td>"
					  + "  <td width='150px'>" + voiture.getKilometrage() + "</td>"
					  + "  <td width='150px'>" + voiture.getNombrePlace() + "</td>"
					  + "  <td width='150px'>" + voiture.getVoitureType() + "</td>" 
					  +"</tr>";

		}

		html += "</table>" + "<section>" + "<table>"
				+ "<tr class='bg-green'><td>&nbsp;</td><td>&nbsp;</td><td>Type</td><td>Tarif</td></tr>";

	
		html += "</table>";

		console.print(html);
	}

	public void creationVoiture() {
		VoitureValidator validator = new VoitureValidator();
		
		VoitureTypeDao voitureTypeDao = new VoitureTypeDao();
		List<VoitureType> voitureTypeList = voitureTypeDao.selectAll();
		
		Form form = new Form();
		// On ajoute au formulaire 2 champs de type texte.
		form.addInput(new TextField("Marque:", "champ1"));
		form.addInput(new TextField("Modèle:", "champ2"));
		form.addInput(new TextField("Immatriculation:", "champ3"));
		form.addInput(new TextField("Kilométrage:", "champ4"));
		form.addInput(new TextField("Nombre de place:", "champ5"));
		
		List<Selectable> voitureTypeSelectable = new ArrayList<>();
		for(VoitureType voiture : voitureTypeList) {
			voitureTypeSelectable.add(voiture);
		}
		
		// Champ de type liste de sélection
		form.addInput(new ComboBox("Type de voiture:", "voiture", voitureTypeSelectable));
		console.input("Création d'une Voiture", form, validator);
	}

}
