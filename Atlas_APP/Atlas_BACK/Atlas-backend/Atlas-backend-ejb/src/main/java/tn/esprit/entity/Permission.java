package tn.esprit.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Permission implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	private String Ecran;
	private String Action;
	private String Application;
	public Permission(String ecran, String action, String application) {
		super();
		Ecran = ecran;
		Action = action;
		Application = application;
	}
	public Permission() {
		super();
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getEcran() {
		return Ecran;
	}
	public void setEcran(String ecran) {
		Ecran = ecran;
	}
	public String getAction() {
		return Action;
	}
	public void setAction(String action) {
		Action = action;
	}
	public String getApplication() {
		return Application;
	}
	public void setApplication(String application) {
		Application = application;
	}
	
	
	
	
}
