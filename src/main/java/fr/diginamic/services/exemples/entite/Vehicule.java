package fr.diginamic.services.exemples.entite;

import org.apache.commons.lang3.builder.EqualsBuilder;

import fr.diginamic.composants.ui.Selectable;

public class Vehicule implements Selectable {

	private Integer id;
	private String immatriculation;
	private String marque;
	private String modele;
	
	public Vehicule(String immatriculation, String marque, String modele) {
		super();
		this.immatriculation = immatriculation;
		this.marque = marque;
		this.modele = modele;
	}

	public Vehicule(Integer id, String immatriculation, String marque, String modele) {
		super();
		this.id = id;
		this.immatriculation = immatriculation;
		this.marque = marque;
		this.modele = modele;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Vehicule)) {
			return false;
		}
		Vehicule autre = (Vehicule)obj;
		return new EqualsBuilder().append(id, autre.getId()).isEquals();
	}

	@Override
	public String toString() {
		return immatriculation;
	}
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the immatriculation
	 */
	public String getImmatriculation() {
		return immatriculation;
	}
	/**
	 * @param immatriculation the immatriculation to set
	 */
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}
	/**
	 * @return the marque
	 */
	public String getMarque() {
		return marque;
	}
	/**
	 * @param marque the marque to set
	 */
	public void setMarque(String marque) {
		this.marque = marque;
	}
	/**
	 * @return the modele
	 */
	public String getModele() {
		return modele;
	}
	/**
	 * @param modele the modele to set
	 */
	public void setModele(String modele) {
		this.modele = modele;
	}


}
