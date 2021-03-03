package fr.diginamic.service.gestion.entite;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "id", nullable = false)
	private String nom;

	@Column(name = "id", nullable = false)
	private String prenom;

	@Embedded
	private Adresse adresse;

	@OneToOne
	@JoinColumn(name = "id_permis")
	private Permis permis;

	@OneToMany(mappedBy = "client")
	private Location location;

	public Client() {
		super();
	}

	public Client(String nom, String prenom, Adresse adresse, Permis permis, Location location) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.permis = permis;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Permis getPermis() {
		return permis;
	}

	public void setPermis(Permis permis) {
		this.permis = permis;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
