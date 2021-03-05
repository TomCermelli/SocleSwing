package fr.diginamic.services.gestion.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.validator.FormValidator;
import fr.diginamic.services.gestion.entite.VoitureType;
import fr.diginamic.services.gestion.entite.dao.VoitureTypeDao;

public class TypeValidator extends FormValidator {
	
	private EntityManager em;
	
	@Override
	public boolean validate(Form form) {
		em = FormValidator.emf.createEntityManager();
		
		TypedQuery<VoitureType> query = em.createQuery("SELECT v FROM VoitureType v WHERE v.type = ?1",
				VoitureType.class);
		query.setParameter(1, "type");
		List<VoitureType> voitureTypeList = query.getResultList();
			
		String type_voiture = form.getValue("type de voiture");
		String tarif = form.getValue("tarif");

		if (type_voiture.trim().isEmpty()) {
			console.alert("Le nom du type est obligatoire !");
			return false;
		}
		else if(!voitureTypeList.isEmpty()) {
			console.alert("Ce type existe déja");
			return false;
			
		}
		else if(tarif.trim().isEmpty()) {
			console.alert("Le tarif associé au type est obligatoire !");
			return false;
		}
		return true;
	}
	
	
	public boolean validatePermis() {
		
		return true;
	}
}
