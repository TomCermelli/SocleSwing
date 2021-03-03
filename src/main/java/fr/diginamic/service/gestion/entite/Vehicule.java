package fr.diginamic.service.gestion.entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class Vehicule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "marque", nullable = false)
	private String marque;
	
	@Column(name = "modèle", nullable = false)
	private String modèle;
	
	@Column(name = "immatriculation", nullable = false)
	private String immatriculation;
	
	@Column(name = "statut", nullable = false)
	private String statut = "disponible";
	
	@Column(name = "kilométrage", nullable = false)
	private double kilométrage;
	
	@OneToMany(mappedBy="client")
	private List<Maintenance> maintenance;
	
	@OneToMany(mappedBy="client")
	private List<Location> location;
	
}
