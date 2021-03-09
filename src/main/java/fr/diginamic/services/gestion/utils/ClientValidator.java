package fr.diginamic.services.gestion.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.validator.FormValidator;
import fr.diginamic.services.gestion.entite.Client;

public class ClientValidator extends FormValidator {
	private EntityManager em;

	@Override
	public boolean validate(Form form) {

		// REGEX
		String patternRue = "[0-9]";
		String patternTel = "[0][1][0-9]{8}";
		String patternEmail = "[a-zA-Z0-9._%+-][@][a-zA-Z0-9.-][.][a-zA-Z]";

		// Valeurs du formulaires
		String nom = form.getValue("nom");
		String prenom = form.getValue("prenom");

		String numeroRue = form.getValue("numero rue");
		String libelleRue = form.getValue("libelle");
		String codePostal = form.getValue("code postal");
		String ville = form.getValue("ville");
		String numeroTel = form.getValue("numero tel");
		String email = form.getValue("email");

		// vérifié si le client existe déja ou non
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.nom = ?1 AND c.prenom = ?2",
				Client.class);
		query.setParameter(1, nom);
		query.setParameter(2, prenom);

		List<Client> camionList = query.getResultList();

		if (!camionList.isEmpty()) {
			console.alert("Ce camion existe déja");
			return false;
		}

		else if (nom.trim().isEmpty()) {
			console.alert("Le nom est obligatoire !");
			return false;
		} else if (prenom.trim().isEmpty()) {
			console.alert("Le prénom est obligatoire !");
			return false;
		}

		else if (numeroRue.isEmpty()) {
			console.alert("Vous devez renseignez un numéro de rue");
			return false;
		} else if (libelleRue.isEmpty()) {
			console.alert("Veuillez entrer le nom de votre rue");
			return false;
		} else if (codePostal.isEmpty()) {
			console.alert("Vous devez renseignez un code postal");
			return false;
		} else if (ville.isEmpty()) {
			console.alert("Veuillez entrer le nom d'une ville");
			return false;
		} else if (!numeroTel.matches(patternTel)) {
			console.alert("Votre numéro de téléphone doit commencé par 01 et doit contenir 10 chiffres");
			return false;
		}
//		else if(!email.matches(patternEmail)) {
//			console.alert("l'email n'est pas dans le bon format");
//			return false;
//		}

		return true;
	}

}
