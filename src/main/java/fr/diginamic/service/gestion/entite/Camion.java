package fr.diginamic.service.gestion.entite;

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

	public Camion(double volume, CamionType camionType) {
		super();
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
