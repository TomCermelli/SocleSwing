package fr.diginamic.services.gestion.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.validator.FormValidator;
import fr.diginamic.services.gestion.entite.CamionType;

public class TypeValidatorCamion extends FormValidator {

	private EntityManager em;

	@Override
	public boolean validate(Form form) {
		em = FormValidator.emf.createEntityManager();

		String type_camion = form.getValue("type_camion");
		String montant = form.getValue("montant");

		TypedQuery<CamionType> query2 = em.createQuery("SELECT c FROM CamionType c WHERE c.montant = ?1",
				CamionType.class);
		query2.setParameter(1, type_camion);
		List<CamionType> camionTypeList = query2.getResultList();

		if (type_camion.trim().isEmpty()) {
			console.alert("Le nom du type est obligatoire !");
			return false;
		} else if (!camionTypeList.isEmpty()) {
			console.alert("Ce type existe déja");
			return false;

		} else if (montant.trim().isEmpty()) {
			console.alert("Le tarif associé au type est obligatoire !");
			return false;
		}
		return true;
	}
}
