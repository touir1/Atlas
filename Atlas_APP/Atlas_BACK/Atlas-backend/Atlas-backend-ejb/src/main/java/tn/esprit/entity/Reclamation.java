package tn.esprit.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Reclamation implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String libelle;
	private String type;
	private String niveau;
	private String status;

	public Reclamation(Long id, String libelle, String type, String niveau, String status) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.type = type;
		this.niveau = niveau;
		this.status = status;
	}

	public Reclamation(String libelle, String type, String niveau, String status) {
		super();
		this.libelle = libelle;
		this.type = type;
		this.niveau = niveau;
		this.status = status;
	}

	public Reclamation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
