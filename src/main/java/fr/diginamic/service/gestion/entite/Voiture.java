package fr.diginamic.service.gestion.entite;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="voiture")
public class Voiture extends Vehicule{
	
	private int nombrePlace;
	
	

}
