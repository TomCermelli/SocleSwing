package fr.diginamic.services.gestion.entite;

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
	

	public Voiture() {
		super();
	}
	
	public Voiture(String marque, String modele, String immatriculation, double kilometrage, int nombrePlace, VoitureType voitureType) {
		super(marque, modele, immatriculation, kilometrage);
		// TODO Auto-generated constructor stub
		this.nombrePlace = nombrePlace;
		this.voitureType = voitureType;
	}

	public int getNombrePlace() {
		return nombrePlace;
	}

	public void setNombrePlace(int nombrePlace) {
		this.nombrePlace = nombrePlace;
	}

	public VoitureType getVoitureType() {
		return voitureType;
	}

	public void setVoitureType(VoitureType voitureType) {
		this.voitureType = voitureType;
	}
	
	

}
