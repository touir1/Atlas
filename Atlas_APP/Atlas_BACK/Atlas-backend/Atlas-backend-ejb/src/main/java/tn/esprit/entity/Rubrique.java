package tn.esprit.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Rubrique implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id ;
	private String Titre;
	private Float Estimation ;
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
	public Float getEstimation() {
		return Estimation;
	}
	public void setEstimation(Float estimation) {
		Estimation = estimation;
	}
	public Rubrique(String titre, Float estimation) {
		super();
		Titre = titre;
		Estimation = estimation;
	}
	public Rubrique() {
		super();
	}
	
	

}
