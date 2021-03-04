package fr.diginamic.service.gestion.entite;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Embeddable
public class Adresse {
	

	@Column(name="numero_rue", nullable = false)
	private int numeroRue;
	
	@Column(name="nom_rue", nullable = false)
	private String libelleRue;
	
	@Column(name="code_postal", nullable = false)
	private int codePostal;
	
	@Column(name="ville" , nullable = false)
	private String ville;
	
	@Column(name="numéro_téléphone", nullable = false)
	private int numeroTel;
	
	@Column(name="email", nullable = false)
	private String email;
	
	

	public Adresse() {
		super();
	}

	public Adresse(int numeroRue, String libelleRue, int codePostal, String ville, int numeroTel, String email) {
		super();
		this.numeroRue = numeroRue;
		this.libelleRue = libelleRue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.numeroTel = numeroTel;
		this.email = email;
	}

	public int getNumeroRue() {
		return numeroRue;
	}

	public void setNumeroRue(int numeroRue) {
		this.numeroRue = numeroRue;
	}

	public String getLibelleRue() {
		return libelleRue;
	}

	public void setLibelleRue(String libelleRue) {
		this.libelleRue = libelleRue;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getNumeroTel() {
		return numeroTel;
	}

	public void setNumeroTel(int numeroTel) {
		this.numeroTel = numeroTel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
