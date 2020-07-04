package tn.esprit.entity;

public class Reponse {

	private Float note;
	private String reponseText;
	private String teponseNumeric;
	private Float noteSuperior;
	private String commentaire;

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

}
