package fr.diginamic.services.gestion.utils;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.validator.FormValidator;

public class PermisValidator extends FormValidator {
	
	
	@Override
	public boolean validate(Form form) {
		String type = form.getValue("type");
		String  numero = form.getValue("numero");
		String dateObtention = form.getValue("dateObtention");
		
		if(type.trim().isEmpty()) {
			console.alert("Le type de permis est obligatoire !");
			return false;
		}
		else if(numero.length()<15) {
			console.alert("Le numéro de permis doit contenir 15 chiffres");
			return false;
		}
		
		return true;
	}

}
