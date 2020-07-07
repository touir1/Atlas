package tn.esprit.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reponse implements Serializable {

	private Float note;
	private String reponseText;
	private String teponseNumeric;
	private Float noteSuperior;
	private String commentaire;

	@Id
	@ManyToOne
	private Evaluation evaluation;
	@Id
	@ManyToOne
	private Question question;

	public Reponse(Evaluation evaluation, Question question) {
		super();
		this.evaluation = evaluation;
		this.question = question;
	}

	public Reponse(Float note, String reponseText, String teponseNumeric, Float noteSuperior, String commentaire) {
		super();
		this.note = note;
		this.reponseText = reponseText;
		this.teponseNumeric = teponseNumeric;
		this.noteSuperior = noteSuperior;
		this.commentaire = commentaire;
	}

	public Reponse() {
		super();
	}

	public Float getNote() {
		return note;
	}

	public void setNote(Float note) {
		this.note = note;
	}

	public String getReponseText() {
		return reponseText;
	}

	public void setReponseText(String reponseText) {
		this.reponseText = reponseText;
	}

	public String getTeponseNumeric() {
		return teponseNumeric;
	}

	public void setTeponseNumeric(String teponseNumeric) {
		this.teponseNumeric = teponseNumeric;
	}

	public Float getNoteSuperior() {
		return noteSuperior;
	}

	public void setNoteSuperior(Float noteSuperior) {
		this.noteSuperior = noteSuperior;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
