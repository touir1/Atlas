package tn.esprit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Facturation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateFacturation;
	private String libelle;
	private Float montantTotale;
	private String document;

	@ManyToOne
	private Mission mission;

	public Facturation(Long id) {
		super();
		this.id = id;
	}

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

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

}
