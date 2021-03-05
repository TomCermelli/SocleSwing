package fr.diginamic.composants.validator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.diginamic.composants.Console;
import fr.diginamic.composants.ui.Form;

/**
 * validateur de formulaire
 * 
 * @author rbonn
 *
 */
public abstract class FormValidator {
	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("location-agency");
	/** Console pour générer l'alerte */
	protected Console console = new Console();

	/**
	 * Méthode qui valide le formulaire et retourne true ou false
	 * 
	 * @param form formulaire
	 * @return boolean
	 */
	public abstract boolean validate(Form form);
}
