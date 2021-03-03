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
@Table(name = "camion_type")
public class CamionType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "type", nullable = false)
	private String type;

	@OneToMany(mappedBy = "camionType")
	private List<Camion> camion;

	public CamionType() {
		super();
	}

	public CamionType(String type, List<Camion> camion) {
		super();
		this.type = type;
		this.camion = camion;
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

	public List<Camion> getCamion() {
		return camion;
	}

	public void setCamion(List<Camion> camion) {
		this.camion = camion;
	}

}
