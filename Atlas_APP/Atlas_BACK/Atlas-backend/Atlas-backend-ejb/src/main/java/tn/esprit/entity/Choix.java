package tn.esprit.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Choix implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id ;
	private String Ordre ;
	private String libelle ;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getOrdre() {
		return Ordre;
	}
	public void setOrdre(String ordre) {
		Ordre = ordre;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Choix(String ordre, String libelle) {
		super();
		Ordre = ordre;
		this.libelle = libelle;
	}
	public Choix() {
		super();
	}
	
	
	

}
