package tn.esprit.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Sujet implements Serializable {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id ;
	private String Titre;
	private int Coeficient;
	private int Noter ;
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
	public int getCoeficient() {
		return Coeficient;
	}
	public void setCoeficient(int coeficient) {
		Coeficient = coeficient;
	}
	public int getNoter() {
		return Noter;
	}
	public void setNoter(int noter) {
		Noter = noter;
	}
	public Sujet(String titre, int coeficient, int noter) {
		super();
		Titre = titre;
		Coeficient = coeficient;
		Noter = noter;
	}
	public Sujet() {
		super();
	}
	
	
}
