package tn.esprit.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Formation implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	private String Libelle;
	
	public Formation(String libelle) {
		super();
		Libelle = libelle;
	} 
	public Formation() {
		super();
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getLibelle() {
		return Libelle;
	}
	public void setLibelle(String libelle) {
		Libelle = libelle;
	}
	
	
	
	
}
