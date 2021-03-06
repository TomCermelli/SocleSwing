package fr.diginamic.services.gestion.entite;

import java.time.LocalDate;
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
	private String numero;

	@Column(name = "date_obtention", nullable = false)
	private LocalDate dateObtention;

	@OneToOne
	@JoinColumn(name = "id_client")
	private Client client;

	public Permis() {
		super();
	}

	public Permis(String type, String numero, LocalDate dateObtention, Client client) {
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public LocalDate getDateObtention() {
		return dateObtention;
	}

	public void setDateObtention(LocalDate dateObtention) {
		this.dateObtention = dateObtention;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
