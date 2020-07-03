package tn.esprit.entity;



public class Reponse {
	
	private Float Note ;
	private String Rep_txt;
	private String Rep_num;
	private Float Note_sup;
	private String Commentaire ;
	public Float getNote() {
		return Note;
	}
	public void setNote(Float note) {
		Note = note;
	}
	public String getRep_txt() {
		return Rep_txt;
	}
	public void setRep_txt(String rep_txt) {
		Rep_txt = rep_txt;
	}
	public String getRep_num() {
		return Rep_num;
	}
	public void setRep_num(String rep_num) {
		Rep_num = rep_num;
	}
	public Float getNote_sup() {
		return Note_sup;
	}
	public void setNote_sup(Float note_sup) {
		Note_sup = note_sup;
	}
	public String getCommentaire() {
		return Commentaire;
	}
	public void setCommentaire(String commentaire) {
		Commentaire = commentaire;
	}
	public Reponse(Float note, String rep_txt, String rep_num, Float note_sup, String commentaire) {
		super();
		Note = note;
		Rep_txt = rep_txt;
		Rep_num = rep_num;
		Note_sup = note_sup;
		Commentaire = commentaire;
	}
	public Reponse() {
		super();
	}
	
	

}
