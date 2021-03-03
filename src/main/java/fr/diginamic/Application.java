package fr.diginamic;

import fr.diginamic.composants.AbstractApplication;
import fr.diginamic.services.exemples.AideService;
import fr.diginamic.services.exemples.Exemple1Service;
import fr.diginamic.services.exemples.Exemple2Service;
import fr.diginamic.services.exemples.Exemple3Service;
import fr.diginamic.services.exemples.Exemple4Service;
import fr.diginamic.services.exemples.Exemple5Service;
import fr.diginamic.services.exemples.Exemple6Service;

/**
 * Fenêtre principale qui porte les principaux composants graphiques de
 * l'application:<br>
 * - les boutons du menu,<br>
 * - le panneau d'affichage des résultats<br>
 * 
 * @author RichardBONNAMY
 *
 */
public class Application extends AbstractApplication {

	/** serialVersionUID */
	private static final long serialVersionUID = 6755835482616236832L;
	
	/** Constructeur
	 * @param title titre
	 */
	public Application(String title) {
		super(title);
	}

	/**
	 * Code principal
	 * 
	 */
	public void main() {
		addMenu(1, "File");
		addMenu(2, "Gestion clients");
		addMenu(3, "Gestion vehicules");
		addMenu(4, "Gestion locations");
		addMenu(5, "Exemples");
		
		addMenuOption(1, "Aide", new AideService());
		
		addMenuOption(2, "Liste des clients", new AideService());
		
		addMenuOption(3, "Liste des vehicules", new AideService());
		
		addMenuOption(4, "Liste des locations", new AideService());
		
		addMenuOption(5, "Exemple 1 - Titres", new Exemple1Service());
		addMenuOption(5, "Exemple 2 - Textes de couleur", new Exemple2Service());
		addMenuOption(5, "Exemple 3 - Table", new Exemple3Service());
		addMenuOption(5, "Exemple 4 - Table avec liens vers méthodes", new Exemple4Service());
		addMenuOption(5, "Exemple 5 - Table dynamique", new Exemple5Service());
		addMenuOption(5, "Exemple 6 - Formulaire", new Exemple6Service());
	}
}