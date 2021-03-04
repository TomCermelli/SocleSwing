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

	@Column(name = "modèle", nullable = false)
	private String modèle;

	@Column(name = "immatriculation", nullable = false)
	private String immatriculation;

	@Column(name = "statut", nullable = false)
	private String statut = "disponible";

	@Column(name = "kilométrage", nullable = false)
	private double kilométrage;

	@OneToMany(mappedBy = "vehicule")
	private List<Maintenance> maintenance;

	@OneToMany(mappedBy = "vehicule")
	private List<Location> location;

	public Vehicule() {
		super();
	}

	public Vehicule(String marque, String modèle, String immatriculation, String statut, double kilométrage) {
		super();
		this.marque = marque;
		this.modèle = modèle;
		this.immatriculation = immatriculation;
		this.statut = statut;
		this.kilométrage = kilométrage;
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

	public String getModèle() {
		return modèle;
	}

	public void setModèle(String modèle) {
		this.modèle = modèle;
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

	public double getKilométrage() {
		return kilométrage;
	}

	public void setKilométrage(double kilométrage) {
		this.kilométrage = kilométrage;
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
