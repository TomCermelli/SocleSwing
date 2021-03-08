package fr.diginamic.services.gestion.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.validator.FormValidator;
import fr.diginamic.services.gestion.entite.CamionType;
import fr.diginamic.services.gestion.entite.Voiture;

public class VoitureValidator extends FormValidator {

	private EntityManager em;

	@Override
	public boolean validate(Form form) {
		em = FormValidator.emf.createEntityManager();

		String type_camion = form.getValue("type_camion");
		String montant = form.getValue("montant");

		TypedQuery<Voiture> query = em.createQuery("SELECT v FROM Voiture v WHERE v.marque = ?1 AND v.modele",
				Voiture.class);
		query.setParameter(1, type_camion);
		query.setParameter(1, type_camion);
		List<Voiture> voitureList = query.getResultList();

		if (!voitureList.isEmpty()) {
			console.alert("Ce type existe déja");
			return false;

		}
		
		else if (type_camion.trim().isEmpty()) {
			console.alert("Le nom du type est obligatoire !");
			return false;

		} else if (montant.trim().isEmpty()) {
			console.alert("Le tarif associé au type est obligatoire !");
			return false;
		}
		return true;
	}

}
