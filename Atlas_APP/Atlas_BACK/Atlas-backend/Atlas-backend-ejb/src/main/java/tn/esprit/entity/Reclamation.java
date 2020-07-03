package tn.esprit.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Reclamation implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	private String Libelle;
	private String Type;
	private String Niveau;
	private String Statut;
	public Reclamation(String libelle, String type, String niveau, String statut) {
		super();
		Libelle = libelle;
		Type = type;
		Niveau = niveau;
		Statut = statut;
	}
	public Reclamation() {
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
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getNiveau() {
		return Niveau;
	}
	public void setNiveau(String niveau) {
		Niveau = niveau;
	}
	public String getStatut() {
		return Statut;
	}
	public void setStatut(String statut) {
		Statut = statut;
	}
	
	
	
	
}
