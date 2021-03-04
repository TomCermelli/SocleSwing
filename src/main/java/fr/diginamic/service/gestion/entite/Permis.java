package fr.diginamic.service.gestion.entite;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "permis")
public class Permis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "numero_permis", nullable = false)
	private int numero;

	@Column(name = "date_obtention", nullable = false)
	private Date dateObtention;

	@OneToOne
	@JoinColumn(name = "id_permis")
	private Client client;

	public Permis() {
		super();
	}

	public Permis(String type, int numero, Date dateObtention, Client client) {
		super();
		this.type = type;
		this.numero = numero;
		this.dateObtention = dateObtention;
		this.client = client;
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

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Date getDateObtention() {
		return dateObtention;
	}

	public void setDateObtention(Date dateObtention) {
		this.dateObtention = dateObtention;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
