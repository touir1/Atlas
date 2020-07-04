package tn.esprit.entity;

import java.sql.Date;

public class UserFormation {

	private String status;
	private String description;
	private Date dateDemande;
	
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
	
	
}
