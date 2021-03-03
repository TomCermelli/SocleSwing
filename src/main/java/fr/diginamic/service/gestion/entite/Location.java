package fr.diginamic.service.gestion.entite;

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
	
	@ManyToOne
	@JoinColumn(name="id_client")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name="id_vehicule")
	private Vehicule vehicule;
	
	@OneToOne
	@JoinColumn(name="id_facture")
	private Facture facture;

}
