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

	@OneToMany(mappedBy = "voitureType")
	private List<Voiture> voiture;

	public VoitureType() {
		super();
	}

	public VoitureType(String type, List<Voiture> voiture) {
		super();
		this.type = type;
		this.voiture = voiture;
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

	public List<Voiture> getVoiture() {
		return voiture;
	}

	public void setVoiture(List<Voiture> voiture) {
		this.voiture = voiture;
	}

}
