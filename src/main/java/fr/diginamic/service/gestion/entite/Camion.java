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

	
	
}
