package tn.esprit.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Question implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	private String libelle;

	@ManyToOne
	private Sujet sujet;

	@OneToMany(mappedBy = "question")
	private List<Choix> choix;

	public Question(Long id, String type, String libelle) {
		super();
		this.id = id;
		this.type = type;
		this.libelle = libelle;
	}

	public Question(String type, String libelle) {
		super();
		this.type = type;
		this.libelle = libelle;
	}

	public Question() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
