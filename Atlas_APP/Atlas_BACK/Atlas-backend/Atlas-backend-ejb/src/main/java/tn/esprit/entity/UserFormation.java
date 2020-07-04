package tn.esprit.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "User_Formation")
public class UserFormation implements Serializable {

	private String status;
	private String description;
	private Date dateDemande;

	@Id
	@ManyToOne
	private Formation formation;
	@Id
	@ManyToOne
	private User user;

	public UserFormation(String status, String description, Date dateDemande) {
		super();
		this.status = status;
		this.description = description;
		this.dateDemande = dateDemande;
	}

	public UserFormation() {
		super();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate_demande() {
		return dateDemande;
	}

	public void setDate_demande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public Date getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
