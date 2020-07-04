package tn.esprit.entity;

import java.sql.Date;

public class User_formation {

	private String Status;
	private String Description;
	private Date Date_demande;
	
	public User_formation(String status, String description, Date date_demande) {
		super();
		Status = status;
		Description = description;
		Date_demande = date_demande;
	}
	
	public User_formation() {
		super();
	}

	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Date getDate_demande() {
		return Date_demande;
	}
	public void setDate_demande(Date date_demande) {
		Date_demande = date_demande;
	}
	
	
}
