package fr.diginamic.services.gestion.entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="camion")
public class Camion extends Vehicule {
	
	@Column(name="volume")
	private double volume;
	
	@ManyToOne
	@JoinColumn(name="id_camion_type")
	private CamionType camionType;
	
	
	public Camion() {
		super();
	}
	
	public Camion(String marque, String modele, String immatriculation, double kilometrage, double volume, CamionType camionType) {
		super(marque, modele, immatriculation, kilometrage);
		// TODO Auto-generated constructor stub
		this.volume = volume;
		this.camionType = camionType;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public CamionType getCamionType() {
		return camionType;
	}

	public void setCamionType(CamionType camionType) {
		this.camionType = camionType;
	}

	
	
}
