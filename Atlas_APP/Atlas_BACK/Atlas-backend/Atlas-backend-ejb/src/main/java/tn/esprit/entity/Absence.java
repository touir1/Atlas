package tn.esprit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Absence implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dateDebutConge;
	private Date dateFinConge;
	private Float heures;
	private String status;
	private String type;
	
	@ManyToOne
	private User user;

	public Absence(Long id, Date dateDebutConge, Date dateFinConge, Float heures, String status, String type) {
		super();
		this.id = id;
		this.dateDebutConge = dateDebutConge;
		this.dateFinConge = dateFinConge;
		this.heures = heures;
		this.status = status;
		this.type = type;
	}

	public Absence(Date dateDebutConge, Date dateFinConge, Float heures, String status, String type) {
		super();
		this.dateDebutConge = dateDebutConge;
		this.dateFinConge = dateFinConge;
		this.heures = heures;
		this.status = status;
		this.type = type;
	}

	public Absence() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateDebutConge() {
		return dateDebutConge;
	}

	public void setDateDebutConge(Date dateDebutConge) {
		this.dateDebutConge = dateDebutConge;
	}

	public Date getDateFinConge() {
		return dateFinConge;
	}

	public void setDateFinConge(Date dateFinConge) {
		this.dateFinConge = dateFinConge;
	}

	public Float getHeures() {
		return heures;
	}

	public void setHeures(Float heures) {
		this.heures = heures;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
