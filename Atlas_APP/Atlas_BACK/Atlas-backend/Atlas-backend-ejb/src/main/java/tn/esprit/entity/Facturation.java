package tn.esprit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Facturation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dateFacturation;
	private String libelle;
	private Float montantTotale;
	private String document;

	public Facturation(Long id, Date dateFacturation, String libelle, Float montantTotale, String document) {
		super();
		this.id = id;
		this.dateFacturation = dateFacturation;
		this.libelle = libelle;
		this.montantTotale = montantTotale;
		this.document = document;
	}

	public Facturation(Date dateFacturation, String libelle, Float montantTotale, String document) {
		super();
		this.dateFacturation = dateFacturation;
		this.libelle = libelle;
		this.montantTotale = montantTotale;
		this.document = document;
	}

	public Facturation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateFacturation() {
		return dateFacturation;
	}

	public void setDateFacturation(Date dateFacturation) {
		this.dateFacturation = dateFacturation;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Float getMontantTotale() {
		return montantTotale;
	}

	public void setMontantTotale(Float montantTotale) {
		this.montantTotale = montantTotale;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

}
