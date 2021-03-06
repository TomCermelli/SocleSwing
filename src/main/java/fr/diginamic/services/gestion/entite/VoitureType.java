package fr.diginamic.services.gestion.entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.diginamic.composants.ui.Selectable;

@Entity
@Table(name = "voiture_type")
public class VoitureType implements Selectable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "type", nullable = false, unique = true)
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

	public Integer getId() {
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

	@Override
	public String toString() {
		return  type;
	}
	
	

}
