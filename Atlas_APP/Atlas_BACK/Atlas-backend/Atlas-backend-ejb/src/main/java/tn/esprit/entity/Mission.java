package tn.esprit.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mission implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id ;
	private String Type;
	private Date Date;
	private Float Duree;
	private String Lieu;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	public Float getDuree() {
		return Duree;
	}
	public void setDuree(Float duree) {
		Duree = duree;
	}
	public String getLieu() {
		return Lieu;
	}
	public void setLieu(String lieu) {
		Lieu = lieu;
	}
	public Mission(String type, java.sql.Date date, Float duree, String lieu) {
		super();
		Type = type;
		Date = date;
		Duree = duree;
		Lieu = lieu;
	}
	public Mission() {
		super();
	}
	
	

}
