package fr.diginamic.services.gestion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.ComboBox;
import fr.diginamic.composants.ui.DateField;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.Selectable;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.services.gestion.entite.Adresse;
import fr.diginamic.services.gestion.entite.Client;
import fr.diginamic.services.gestion.entite.Permis;
import fr.diginamic.services.gestion.entite.dao.ClientDao;
import fr.diginamic.services.gestion.utils.ClientValidator;
import fr.diginamic.services.gestion.utils.TypeValidator;

public class ClientService extends MenuService {
public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("location-agency");
	
	
	private EntityManager em = emf.createEntityManager();

	private EntityTransaction transaction = em.getTransaction();


	@Override
	public void traitement() {
		ClientDao clientDao = new ClientDao();

		List<Client> clientList = clientDao.selectAll();

		console.clear();
		console.println("<h1 class='bg-dark'><center>Création de Client<br></center></h1>");

		String htmlHeader = "<section class='d-flex fd-vertical'>" + "<p>Création d'un client</p>"
				+ "<button><a class='btn-blue' href='creation()'><img width=25 src='images/plus-blue.png'></a></button>"
				+ "</section>";
		String htmlTitle = "<h2>List de client</h2>";
		String htmlBody= "<table class='table' cellspacing=0> "
				+ "<tr class='bg-green'><td>&nbsp;</td><td>&nbsp;</td><td>Nom</td>" + "<td>Prenom</td>"
				+ "<td width='325px'>Adresse</td>" 
				+ "<td width='325px'>Infos perso</td>"
				+"</tr>";

		for (Client client : clientList) {
			htmlBody += "<tr>" + "  <td><a class='btn-blue' href='modification(" + client.getId()
					+ ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
					+ "  <td><a class='btn-red' href='suppression(" + client.getId()
					+ ")'><img width=25 src='images/trash-red-xs.png'></a></td>" + "  <td width='150px'>"
					+ client.getNom() + "</td>" + "  <td width='150px'>" + client.getPrenom() + "</td>"
					+ "  <td width='150px'>" +client.getAdresse().getNumeroRue()+ " " +client.getAdresse().getLibelleRue()+ " </br> " + client.getAdresse().getVille() + ", " +client.getAdresse().getCodePostal()+ "</td>"
					+"  <td width='150px'> Téléphone: " +client.getAdresse().getNumeroTel()+ " </br> Email: " +client.getAdresse().getEmail()+ "</td>";
		}
		htmlBody += "</table>";

		console.println(htmlHeader);
		console.println(htmlTitle);
		console.println(htmlBody);
		
	}
	
	public void creation() {
		ClientValidator validator = new ClientValidator();
		ClientDao clientDao = new ClientDao();

		Form form = new Form();
		// On ajoute au formulaire 2 champs de type texte.
		form.addInput(new TextField("Nom:", "nom"));
		form.addInput(new TextField("Prenom:", "prenom"));
		form.addInput(new TextField("Numéro de rue:", "numero rue"));
		form.addInput(new TextField("Libellé:", "libelle"));
		form.addInput(new TextField("Code Postal:", "code postal"));
		form.addInput(new TextField("Ville:", "ville"));
		form.addInput(new TextField("Numéro de téléphone:", "numero tel"));
		form.addInput(new TextField("Email:", "email"));
		
		
		boolean valide = console.input("Création d'une Voiture", form, validator);
		
		if(valide) {
			String nom = form.getValue("nom");
			String prenom = form.getValue("prenom");

			int numeroRue = Integer.parseInt(form.getValue("numero rue"));
			String libelleRue = form.getValue("libelle");
			int codePostal = Integer.parseInt(form.getValue("code postal"));
			String ville = form.getValue("ville");
			String numeroTel = form.getValue("numero tel");
			String email = form.getValue("email");
		
			Adresse adresse = new Adresse(numeroRue, libelleRue, codePostal, ville, numeroTel, email);
			
			Client client = new Client(nom, prenom , adresse);
			clientDao.insert(client);
		}
		// Une fois la création terminé il faut rappeller la fonction traitement pour avoir un "rafraichissement" des données
		traitement();
	}
	
	public void modification(Integer id) {
		ClientValidator validator = new ClientValidator();
		ClientDao clientDao = new ClientDao();
		
		Client client = em.find(Client.class, id);
		// On commence par créér le formulaire vide
		Form form = new Form();
		// On ajoute au formulaire 7 champs de type texte.
		form.addInput(new TextField("Nom:", "nom", client.getNom()));
		form.addInput(new TextField("Prenom:", "prenom", client.getPrenom()));
		form.addInput(new TextField("Numéro de rue:", "numero rue", String.valueOf(client.getAdresse().getNumeroRue())));
		form.addInput(new TextField("Libellé:", "libelle", client.getAdresse().getLibelleRue()));
		form.addInput(new TextField("Code Postal:", "code postal", String.valueOf(client.getAdresse().getCodePostal())));
		form.addInput(new TextField("Ville:", "ville", client.getAdresse().getVille()));
		form.addInput(new TextField("Numéro de téléphone:", "numero tel", client.getAdresse().getNumeroTel()));
		form.addInput(new TextField("Email:", "email", client.getAdresse().getEmail()));
		
		
		// Les règles métier sont vérifiées dans le validator
		boolean valide = console.input("Modification du client : " +client.getNom()+ " " +client.getPrenom(), form, validator);
		if (valide) {
			
			clientDao.update(client, form);
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
		ClientDao ClientDao = new ClientDao();
		boolean result = console.confirm("Suppression de l'item " + id, "Confirmez-vous la suppression de l'item n°"+id);
		if(result) {
			ClientDao.delete(id);
		}
		traitement();
	}

}
