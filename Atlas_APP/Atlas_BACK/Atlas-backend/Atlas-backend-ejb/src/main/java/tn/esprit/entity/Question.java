package tn.esprit.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Question implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String type;
	@Column(nullable = false)
	private String libelle;

	@ManyToOne
	private Sujet sujet;

	@JsonIgnore
	@OneToMany(mappedBy = "question")
	private List<Choix> choix;

	@JsonIgnore
	@OneToMany(mappedBy = "question")
	private List<Reponse> reponses;

	public Question(Long id) {
		super();
		this.id = id;
	}

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

	public Sujet getSujet() {
		return sujet;
	}

	public void setSujet(Sujet sujet) {
		this.sujet = sujet;
	}

	public List<Choix> getChoix() {
		return choix;
	}

	public void setChoix(List<Choix> choix) {
		this.choix = choix;
	}

	public List<Reponse> getReponses() {
		return reponses;
	}

	public void setReponses(List<Reponse> reponses) {
		this.reponses = reponses;
	}

}
