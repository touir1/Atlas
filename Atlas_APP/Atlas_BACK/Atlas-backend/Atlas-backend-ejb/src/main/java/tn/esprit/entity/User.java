package tn.esprit.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

@Entity
public class User implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	private String Nom;
	private String Prenom;
	private String Email;
	private String Poste;
	private String Image;
	private Date Date_Naiss;
	private Date Date_Contrat;
	public User(String nom, String prenom, String email, String poste, String image, Date date_Naiss,
			Date date_Contrat) {
		super();
		Nom = nom;
		Prenom = prenom;
		Email = email;
		Poste = poste;
		Image = image;
		Date_Naiss = date_Naiss;
		Date_Contrat = date_Contrat;
	}
	public User() {
		super();
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getPrenom() {
		return Prenom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPoste() {
		return Poste;
	}
	public void setPoste(String poste) {
		Poste = poste;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	public Date getDate_Naiss() {
		return Date_Naiss;
	}
	public void setDate_Naiss(Date date_Naiss) {
		Date_Naiss = date_Naiss;
	}
	public Date getDate_Contrat() {
		return Date_Contrat;
	}
	public void setDate_Contrat(Date date_Contrat) {
		Date_Contrat = date_Contrat;
	}
	
	
	
}
