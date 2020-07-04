package tn.esprit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mission implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	private Date date;
	private Float duree;
	private String lieu;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Float getDuree() {
		return duree;
	}

	public void setDuree(Float duree) {
		this.duree = duree;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public Mission(Long id, String type, Date date, Float duree, String lieu) {
		super();
		this.id = id;
		this.type = type;
		this.date = date;
		this.duree = duree;
		this.lieu = lieu;
	}

	public Mission(String type, Date date, Float duree, String lieu) {
		super();
		this.type = type;
		this.date = date;
		this.duree = duree;
		this.lieu = lieu;
	}

	public Mission() {
		super();
	}

}
