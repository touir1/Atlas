package tn.esprit.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Projet implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id ;
	private String Titre ;
	private Date Date_creation;
	private Date Date_cloture;
	
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getTitre() {
		return Titre;
	}
	public void setTitre(String titre) {
		Titre = titre;
	}
	public Date getDate_creation() {
		return Date_creation;
	}
	public void setDate_creation(Date date_creation) {
		Date_creation = date_creation;
	}
	public Date getDate_cloture() {
		return Date_cloture;
	}
	public void setDate_cloture(Date date_cloture) {
		Date_cloture = date_cloture;
	}
	public Projet(String titre, Date date_creation, Date date_cloture) {
		super();
		Titre = titre;
		Date_creation = date_creation;
		Date_cloture = date_cloture;
	}
	public Projet() {
		super();
	}
	
	

}
