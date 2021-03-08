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
import fr.diginamic.services.gestion.entite.CamionType;
import fr.diginamic.services.gestion.entite.VoitureType;
import fr.diginamic.services.gestion.entite.dao.CamionTypeDao;
import fr.diginamic.services.gestion.entite.dao.VoitureTypeDao;

public class VehiculeService extends MenuService {

	public void traitement() {

		VoitureTypeDao voitureTypeDao = new VoitureTypeDao();
		CamionTypeDao camionTypeDao = new CamionTypeDao();

		List<VoitureType> voitureTypeList = voitureTypeDao.selectAll();
		List<CamionType> camionTypeList = camionTypeDao.selectAll();

		console.clear();
		console.print("<h1 class='bg-dark'><center>Création de type <br> Voiture / Camion / Permis</center></h1>");

		String html = "<section class='d-flex'>" + "<section>" + "<section class='d-flex fd-vertical'>"
				+ "<p>Création d'un type de Voiture</p>"
				+ "<button><a class='btn-blue' href='creationVoiture()'><img width=25 src='images/plus-blue.png'></a></button>"
				+ "</section>" + "</section>" + "<section class='d-flex fd-vertical'>" + "<section>"
				+ "<p>Création d'un type de Camion</p>"
				+ "<button><a class='btn-blue' href='creationCamion()'><img width=25 src='images/plus-blue.png'></a></button>"
				+ "</section>" + "</section>" + "</section>" + "<table class='table' cellspacing=0> "
				+ "<tr class='bg-green'><td>&nbsp;</td><td>&nbsp;</td><td>Type</td><td>Tarif</td></tr>";

		for (VoitureType voiture : voitureTypeList) {
			html += "<tr>" + "  <td><a class='btn-blue' href='modificationVoiture(" + voiture.getId()
					+ ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
					+ "  <td><a class='btn-red' href='suppressionVoiture(" + voiture.getId()
					+ ")'><img width=25 src='images/trash-red-xs.png'></a></td>" + "  <td width='150px'>"
					+ voiture.getType() + "</td>" + "  <td width='150px'>" + voiture.getTarif() + "</td>" + "</tr>";
		}

		html += "</table>" + "<section>" + "<table>"
				+ "<tr class='bg-green'><td>&nbsp;</td><td>&nbsp;</td><td>Type</td><td>Tarif</td></tr>";

		for (CamionType camion : camionTypeList) {
			html += "<tr>" + "  <td><a class='btn-blue' href='modificationCamion(" + camion.getId()
					+ ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
					+ "  <td><a class='btn-red' href='suppressionCamion(" + camion.getId()
					+ ")'><img width=25 src='images/trash-red-xs.png'></a></td>" + "  <td width='150px'>"
					+ camion.getType() + "</td>" + "  <td width='150px'>" + camion.getMontant() + "</td>" + "</tr>";
		}

		html += "</table>";

		console.print(html);
	}

	public void creationVoiture() {
		
		VoitureTypeDao voitureTypeDao = new VoitureTypeDao();
		List<VoitureType> voitureTypeList = voitureTypeDao.selectAll();
		
		Form form = new Form();
		// On ajoute au formulaire 2 champs de type texte.
		form.addInput(new TextField("Marque:", "champ1"));
		form.addInput(new TextField("Modèle:", "champ2"));
		form.addInput(new TextField("Immatriculation:", "champ3"));
		form.addInput(new TextField("Kilométrage:", "champ4"));
		form.addInput(new TextField("Nombre de place:", "champ5"));
		
//		List<Selectable> voitureTypeSelectable = new ArrayList<>();
//		for(VoitureType voiture : voitureTypeList) {
//			voitureTypeSelectable.add(voiture.getId(), null);
//		}
//		
//		// Champ de type liste de sélection
//		form.addInput(new ComboBox("Type de voiture:", "voiture", voitureTypeSelectable, voitureTypeSelectable.get(0)));
		console.input("Création d'une Voiture", form, validator);
	}

}
