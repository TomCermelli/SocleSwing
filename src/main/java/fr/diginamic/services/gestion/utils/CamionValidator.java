package fr.diginamic.services.gestion.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.validator.FormValidator;
import fr.diginamic.services.gestion.entite.Voiture;

public class CamionValidator extends FormValidator {
	
	private EntityManager em;

	@Override
	public boolean validate(Form form) {
		em = FormValidator.emf.createEntityManager();
		String pattern = "[A-Z]{2}[-][0-9A-Z]{3}[-][A-Z]{2}";
		
		String nvMarque = form.getValue("marque");
		String nvModele = form.getValue("modele");
		String nvImmatriculation = form.getValue("immatriculation");
		Double nvKilometrage = Double.parseDouble(form.getValue("kilometrage"));
		Double nvVolume = Double.parseDouble(form.getValue("volume"));

		TypedQuery<Voiture> query = em.createQuery("SELECT v FROM Voiture v WHERE v.marque = ?1 AND v.modele = ?2 AND  v.kilometrage = ?3 AND v.nombrePlace = ?4",
				Voiture.class);
		query.setParameter(1, nvMarque);
		query.setParameter(2, nvModele);
		query.setParameter(3, nvKilometrage);
		query.setParameter(4, nvVolume);
		List<Voiture> voitureList = query.getResultList();

		if (!voitureList.isEmpty()) {
			console.alert("Ce camion existe déja");
			return false;
		}
		
		else if (nvMarque.trim().isEmpty()) {
			console.alert("Le nom de la marque est obligatoire !");
			return false;

		} else if (nvModele.trim().isEmpty()) {
			console.alert("Le modèle du vehicule est obligatoire !");
			return false;
		}
		else if (!nvImmatriculation.matches(pattern)) {
			console.alert("le numéro d'imatriculation n'est pas dans le bon format , veuillez entrer sous forme XX-X00-XX");
			return false;
		}
		else if (nvKilometrage<0) {
			console.alert("le kilométrage ne peut pas être négatif");
			return false;
		}
		else if (nvVolume<0) {
			console.alert("Le volume du camion ne peut pas être négatif");
			return false;
		}
		return true;
	}

}
