package fr.diginamic.service.gestion.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="voiture")
public class Voiture extends Vehicule{
	
	@Column(name="nombre_place")
	private int nombrePlace;
	
	@ManyToOne
	@JoinColumn(name="id_voiture_type")
	private VoitureType voitureType;
	
	

}
