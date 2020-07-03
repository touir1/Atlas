package tn.esprit.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Rapport implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id ;
	private Boolean Valider;
	private Date Date_creation ;
	private Date Date_imputation ;
	private int Semaine ;
	private int Mois;
	private int Jours_semaine;
	private Float duree;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Boolean getValider() {
		return Valider;
	}
	public void setValider(Boolean valider) {
		Valider = valider;
	}
	public Date getDate_creation() {
		return Date_creation;
	}
	public void setDate_creation(Date date_creation) {
		Date_creation = date_creation;
	}
	public Date getDate_imputation() {
		return Date_imputation;
	}
	public void setDate_imputation(Date date_imputation) {
		Date_imputation = date_imputation;
	}
	public int getSemaine() {
		return Semaine;
	}
	public void setSemaine(int semaine) {
		Semaine = semaine;
	}
	public int getMois() {
		return Mois;
	}
	public void setMois(int mois) {
		Mois = mois;
	}
	public int getJours_semaine() {
		return Jours_semaine;
	}
	public void setJours_semaine(int jours_semaine) {
		Jours_semaine = jours_semaine;
	}
	public Float getDuree() {
		return duree;
	}
	public void setDuree(Float duree) {
		this.duree = duree;
	}
	public Rapport(Boolean valider, Date date_creation, Date date_imputation, int semaine, int mois, int jours_semaine,
			Float duree) {
		super();
		Valider = valider;
		Date_creation = date_creation;
		Date_imputation = date_imputation;
		Semaine = semaine;
		Mois = mois;
		Jours_semaine = jours_semaine;
		this.duree = duree;
	}
	public Rapport() {
		super();
	}
	
		
	

}
