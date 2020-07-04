package tn.esprit.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Formation implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String libelle;

	@OneToMany(mappedBy = "formation")
	private List<UserFormation> userFormations;

	public Formation(Long id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}

	public Formation(String libelle) {
		super();
		this.libelle = libelle;
	}

	public Formation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<UserFormation> getUserFormations() {
		return userFormations;
	}

	public void setUserFormations(List<UserFormation> userFormations) {
		this.userFormations = userFormations;
	}

}
