package tn.esprit.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

@Entity
public class Absence implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	private Date DateDConge;
	private Date DateFConge;
	private float Heurs;
	private String Statut;
	private String Type;
	public Absence(Date dateDConge, Date dateFConge, float heurs, String statut, String type) {
		super();
		DateDConge = dateDConge;
		DateFConge = dateFConge;
		Heurs = heurs;
		Statut = statut;
		Type = type;
	}
	public Absence() {
		super();
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Date getDateDConge() {
		return DateDConge;
	}
	public void setDateDConge(Date dateDConge) {
		DateDConge = dateDConge;
	}
	public Date getDateFConge() {
		return DateFConge;
	}
	public void setDateFConge(Date dateFConge) {
		DateFConge = dateFConge;
	}
	public float getHeurs() {
		return Heurs;
	}
	public void setHeurs(float heurs) {
		Heurs = heurs;
	}
	public String getStatut() {
		return Statut;
	}
	public void setStatut(String statut) {
		Statut = statut;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	
	
	
	

}
