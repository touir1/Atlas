package tn.esprit.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Role implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String libelle;

	public Role(Long id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}

	public Role(String libelle) {
		super();
		this.libelle = libelle;
	}

	public Role() {
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

}
