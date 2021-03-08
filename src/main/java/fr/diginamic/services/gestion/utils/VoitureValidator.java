package fr.diginamic.services.gestion.utils;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.validator.FormValidator;
import fr.diginamic.services.gestion.entite.Voiture;
import fr.diginamic.services.gestion.entite.VoitureType;

public class VoitureValidator extends FormValidator {

	private EntityManager em;

	@Override
	public boolean validate(Form form) {
		em = FormValidator.emf.createEntityManager();
		String pattern = "[A-Z]{2}[-][0-9A-Z]{3}[-][A-Z]{2}";
		
		String nvMarque = form.getValue("marque");
		String nvModele = form.getValue("modele");
		String nvImmatriculation = form.getValue("immatriculation");
		Double nvKilometrage = Double.parseDouble(form.getValue("kilometrage"));
		int nvNombrePlace = Integer.parseInt(form.getValue("nombre de place"));

		TypedQuery<Voiture> query = em.createQuery("SELECT v FROM Voiture v WHERE v.marque = ?1 AND v.modele = ?2 AND  v.kilometrage = ?3 AND v.nombrePlace = ?4",
				Voiture.class);
		query.setParameter(1, nvMarque);
		query.setParameter(2, nvModele);
		query.setParameter(3, nvKilometrage);
		query.setParameter(4, nvNombrePlace);
		List<Voiture> voitureList = query.getResultList();

		if (!voitureList.isEmpty()) {
			console.alert("Cette voiture existe déja");
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
		else if (nvNombrePlace<0 || nvNombrePlace>9) {
			console.alert("Le nombre de place d'une voiture est obligatoire et doit être entre 1 et 8");
			return false;
		}
		return true;
	}

}
