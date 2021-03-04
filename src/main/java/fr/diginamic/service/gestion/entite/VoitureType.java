package fr.diginamic.service.gestion.entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "voiture_type")
public class VoitureType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "type", nullable = false)
	private String type;
	
	@Column(name="tarif", nullable = false)
	private double tarif;

	@OneToMany(mappedBy = "voitureType")
	private List<Voiture> voiture;

	public VoitureType() {
		super();
	}

	public VoitureType(String type, double tarif) {
		super();
		this.type = type;
		this.tarif = tarif;
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
	
	

	public double getTarif() {
		return tarif;
	}


	public void setTarif(double tarif) {
		this.tarif = tarif;
	}


	public List<Voiture> getVoiture() {
		return voiture;
	}

	public void setVoiture(List<Voiture> voiture) {
		this.voiture = voiture;
	}

}
