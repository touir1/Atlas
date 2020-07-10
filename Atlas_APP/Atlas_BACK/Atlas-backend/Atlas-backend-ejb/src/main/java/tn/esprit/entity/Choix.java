package tn.esprit.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Choix implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ordre;
	@Column(nullable = false)
	private String libelle;

	@ManyToOne
	private Question question;

	public Choix(Long id) {
		super();
		this.id = id;
	}

	public Choix(Long id, String ordre, String libelle) {
		super();
		this.id = id;
		this.ordre = ordre;
		this.libelle = libelle;
	}

	public Choix(String ordre, String libelle) {
		super();
		this.ordre = ordre;
		this.libelle = libelle;
	}

	public Choix() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrdre() {
		return ordre;
	}

	public void setOrdre(String ordre) {
		this.ordre = ordre;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
