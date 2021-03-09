package fr.diginamic.services.gestion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.ComboBox;
import fr.diginamic.composants.ui.DateField;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.Selectable;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.services.gestion.entite.Client;
import fr.diginamic.services.gestion.entite.Permis;
import fr.diginamic.services.gestion.entite.Voiture;
import fr.diginamic.services.gestion.entite.VoitureType;
import fr.diginamic.services.gestion.entite.dao.ClientDao;
import fr.diginamic.services.gestion.entite.dao.PermisDao;
import fr.diginamic.services.gestion.entite.dao.VoitureDao;
import fr.diginamic.services.gestion.entite.dao.VoitureTypeDao;
import fr.diginamic.services.gestion.utils.PermisValidator;
import fr.diginamic.services.gestion.utils.VoitureValidator;

public class PermisService extends MenuService {

	@Override
	public void traitement() {

		PermisDao permisDao = new PermisDao();

		List<Permis> permisList = permisDao.selectAll();

		console.clear();
		console.println("<h1 class='bg-dark'><center>Création de Permis<br></center></h1>");

		String html = "<section class='d-flex fd-vertical'>" + "<p>Création d'un permis</p>"
				+ "<button><a class='btn-blue' href='creation()'><img width=25 src='images/plus-blue.png'></a></button>"
				+ "</section>" + "<h2>List de Voiture</h2>" + "<table class='table' cellspacing=0> "
				+ "<tr class='bg-green'><td>&nbsp;</td><td>&nbsp;</td><td>Type</td>" + "<td>Numero</td>"
				+ "<td>DateObtention</td>" + "</tr>";

		for (Permis permis : permisList) {
			html += "<tr>" + "  <td><a class='btn-blue' href='modification(" + permis.getId()
					+ ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
					+ "  <td><a class='btn-red' href='suppression(" + permis.getId()
					+ ")'><img width=25 src='images/trash-red-xs.png'></a></td>" + "  <td width='150px'>"
					+ permis.getType() + "</td>" + "  <td width='150px'>" + permis.getNumero() + "</td>"
					+ "  <td width='150px'>" + permis.getDateObtention() + "</td>" + "</td>"
					+ "  <td width='150px'>" + permis.getClient() + "</td>" +"</tr>";
		}
		html += "</table>";

		console.println(html);
	}
	
	public void creation() {
		PermisValidator validator = new PermisValidator();
		PermisDao permisDao = new PermisDao();
		
		ClientDao clientDao = new ClientDao();
		List<Client> clientList = clientDao.selectAll();
		
		Form form = new Form();
		// On ajoute au formulaire 2 champs de type texte.
		form.addInput(new TextField("Type:", "type"));
		form.addInput(new TextField("Numero:", "numero"));
		form.addInput(new DateField("Date d'obtention :", "dateObtention"));
		
		List<Selectable> clientSelectable = new ArrayList<>();
		for(Client client : clientList) {
			clientSelectable.add(client);
		}
		form.addInput(new ComboBox("Type de voiture:", "client", clientSelectable));
		
		boolean valide = console.input("Création d'une Voiture", form, validator);
		
		if(valide) {
			String type = form.getValue("type");
			int  numero = Integer.parseInt(form.getValue("numero"));
			String dateObtention = form.getValue("dateObtention");
			Client client = form.getValue("client");
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy", Locale.FRENCH);
			LocalDate dateTime = LocalDate.parse(dateObtention, formatter);
		
			
			Permis permis = new Permis(type, numero , dateTime, client);
			permisDao.insert(permis);
			
		}
		// Une fois la création terminé il faut rappeller la fonction traitement pour avoir un "rafraichissement" des données
		traitement();
		
		
	}

}
