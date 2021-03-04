package fr.diginamic.service.gestion.entite;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="maintenance")
public class Maintenance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name="cout_reparation")
	private double coutReparation;
	
	@Column(name="debut_debut_maintenance")
	private Date debutMaintenance;
	
	@Column(name="date_fin_maintenance")
	private Date finMaintenance;
	
	@ManyToOne
	@JoinColumn(name="id_vehicule")
	private Vehicule vehicule;

	public Maintenance() {
		super();
	}
	
	public Maintenance(double coutReparation, Date debutMaintenance, Date finMaintenance, Vehicule vehicule) {
		super();
		this.coutReparation = coutReparation;
		this.debutMaintenance = debutMaintenance;
		this.finMaintenance = finMaintenance;
		this.vehicule = vehicule;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCoutReparation() {
		return coutReparation;
	}

	public void setCoutReparation(double coutReparation) {
		this.coutReparation = coutReparation;
	}

	public Date getDebutMaintenance() {
		return debutMaintenance;
	}

	public void setDebutMaintenance(Date debutMaintenance) {
		this.debutMaintenance = debutMaintenance;
	}

	public Date getFinMaintenance() {
		return finMaintenance;
	}

	public void setFinMaintenance(Date finMaintenance) {
		this.finMaintenance = finMaintenance;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}
	
	
	

	
}
