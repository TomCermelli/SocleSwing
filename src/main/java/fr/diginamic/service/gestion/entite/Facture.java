package fr.diginamic.service.gestion.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="facture")
public class Facture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name="numero_facture")
	private int numFacture;
	
	@Column(name="montant")
	private double montant;
	
	@Column(name="status")
	private String status="NON PAYE";
	
	@ManyToOne
	@JoinColumn(name="id_facture_type")
	private FactureType factureType;
	
	@OneToOne
	@JoinColumn(name="id_location")
	private Location location;
	
	public Facture() {
		super();
	}

	public Facture(int numFacture, double montant, FactureType factureType) {
		super();
		this.numFacture = numFacture;
		this.montant = montant;
		this.factureType = factureType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumFacture() {
		return numFacture;
	}

	public void setNumFacture(int numFacture) {
		this.numFacture = numFacture;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public FactureType getFactureType() {
		return factureType;
	}

	public void setFactureType(FactureType factureType) {
		this.factureType = factureType;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}	
}
