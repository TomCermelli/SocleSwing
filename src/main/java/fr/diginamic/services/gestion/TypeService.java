package fr.diginamic.services.gestion;

import java.io.IOException;

import javax.persistence.EntityManager;

import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.services.exemples.entite.Personne;
import fr.diginamic.services.gestion.entite.dao.VoitureTypeDao;
import fr.diginamic.services.gestion.utils.TypeValidator;

public class TypeService extends MenuService {

	@Override
	public void traitement() {
		// TODO Auto-generated method stub

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
					+"</section>";
		
		console.print(html);
	}
	
	public void creation() throws IOException {
		TypeValidator validator = new TypeValidator();
		VoitureTypeDao voitureType = new VoitureTypeDao();
		
		Form form = new Form();
		// On ajoute au formulaire 2 champs de type texte pour permettre de modifier le nom et le prénom du client
				form.addInput(new TextField("Type:", "type de voiture"));
				form.addInput(new TextField("Tarif:", "tarif"));
				
		console.input("Création d'un type", form, validator);
		boolean valide = validator.validate(form);
		if(valide) {
			voitureType.insertVoitureType(form);
		}
		
		
		
		
	}

}
