package tn.esprit.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Facturation implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id ;
	private Date Date_facturation;
	private String Libelle;
	private Float Montant_total;
	private String Document;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Date getDate_facturation() {
		return Date_facturation;
	}
	public void setDate_facturation(Date date_facturation) {
		Date_facturation = date_facturation;
	}
	public String getLibelle() {
		return Libelle;
	}
	public void setLibelle(String libelle) {
		Libelle = libelle;
	}
	public Float getMontant_total() {
		return Montant_total;
	}
	public void setMontant_total(Float montant_total) {
		Montant_total = montant_total;
	}
	public String getDocument() {
		return Document;
	}
	public void setDocument(String document) {
		Document = document;
	}
	public Facturation(Date date_facturation, String libelle, Float montant_total, String document) {
		super();
		Date_facturation = date_facturation;
		Libelle = libelle;
		Montant_total = montant_total;
		Document = document;
	}
	public Facturation() {
		super();
	}
	
	

}
