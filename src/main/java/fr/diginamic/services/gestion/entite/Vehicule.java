package fr.diginamic.services.gestion.entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="vehicule")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehicule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "marque", nullable = false)
	private String marque;

	@Column(name = "modele", nullable = false)
	private String modele;

	@Column(name = "immatriculation", nullable = false)
	private String immatriculation;

	@Column(name = "statut", nullable = false)
	private String statut = "disponible";

	@Column(name = "kilometrage", nullable = false)
	private double kilometrage;

	@OneToMany(mappedBy = "vehicule")
	private List<Maintenance> maintenance;

	@OneToMany(mappedBy = "vehicule")
	private List<Location> location;

	public Vehicule() {
		super();
	}

	public Vehicule(String marque, String modele, String immatriculation, double kilometrage) {
		super();
		this.marque = marque;
		this.modele = modele;
		this.immatriculation = immatriculation;
		this.kilometrage = kilometrage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public double getKilometrage() {
		return kilometrage;
	}

	public void setKilometrage(double kilométrage) {
		this.kilometrage = kilométrage;
	}

	public List<Maintenance> getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(List<Maintenance> maintenance) {
		this.maintenance = maintenance;
	}

	public List<Location> getLocation() {
		return location;
	}

	public void setLocation(List<Location> location) {
		this.location = location;
	}

}
