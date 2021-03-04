package fr.diginamic.services.gestion.entite;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="location")
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name="debut_reservation")
	private Date dateReservation;
	
	@Column(name="fin_prevu_reservation")
	private Date dateFinPrevu;
	
	@Column(name="fin_reel_reservation")
	private Date dateFinReel;

	@Column(name="kilometrage_debut")
	private double kilometrageDebut;
	
	@Column(name="kilometrage_fin")
	private double kilometrageFin;
	
	@Column(name="commentaire")
	private String commentaire;
	
	@ManyToOne
	@JoinColumn(name="id_client")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name="id_vehicule")
	private Vehicule vehicule;
	
	@OneToOne
	@JoinColumn(name="id_facture")
	private Facture facture;
	
	public Location() {
		super();
	}
	
	public Location(Date dateReservation, Date dateFinPrevu, double kilometrageDebut, Client client,
			Vehicule vehicule) {
		super();
		this.dateReservation = dateReservation;
		this.dateFinPrevu = dateFinPrevu;
		this.kilometrageDebut = kilometrageDebut;
		this.client = client;
		this.vehicule = vehicule;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	public Date getDateFinPrevu() {
		return dateFinPrevu;
	}

	public void setDateFinPrevu(Date dateFinPrevu) {
		this.dateFinPrevu = dateFinPrevu;
	}

	public Date getDateFinReel() {
		return dateFinReel;
	}

	public void setDateFinReel(Date dateFinReel) {
		this.dateFinReel = dateFinReel;
	}

	public double getKilometrageDebut() {
		return kilometrageDebut;
	}

	public void setKilometrageDebut(double kilometrageDebut) {
		this.kilometrageDebut = kilometrageDebut;
	}

	public double getKilometrageFin() {
		return kilometrageFin;
	}

	public void setKilometrageFin(double kilometrageFin) {
		this.kilometrageFin = kilometrageFin;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}
	
	

}
