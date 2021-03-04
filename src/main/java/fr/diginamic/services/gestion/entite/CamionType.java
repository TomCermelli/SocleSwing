package fr.diginamic.services.gestion.entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "camion_type")
public class CamionType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "type", nullable = false)
	private String type;
	
	@Column(name="montant", nullable = false)
	private double montant;

	@OneToMany(mappedBy = "camionType")
	private List<Camion> camion;

	public CamionType() {
		super();
	}

	public CamionType(String type, Double montant) {
		super();
		this.type = type;
		this.montant = montant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public List<Camion> getCamion() {
		return camion;
	}

	public void setCamion(List<Camion> camion) {
		this.camion = camion;
	}

}
