package tn.esprit.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class test implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID ;
	private String Prenom;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getPrenom() {
		return Prenom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	public test(int iD, String prenom) {
		super();
		ID = iD;
		Prenom = prenom;
	}
	public test() {
		super();
	
	}
	
	
	
}
