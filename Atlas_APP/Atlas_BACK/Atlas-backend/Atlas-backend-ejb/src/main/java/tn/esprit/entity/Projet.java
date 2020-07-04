package tn.esprit.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Projet implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titre;
	private Date dateCreation;
	private Date dateCloture;

	@ManyToMany
	private List<User> membres;

	@OneToMany(mappedBy = "projet")
	private List<Mission> missions;

	@OneToMany(mappedBy = "projet")
	private List<Rubrique> rubriques;

	public Projet(Long id, String titre, Date dateCreation, Date dateCloture) {
		super();
		this.id = id;
		this.titre = titre;
		this.dateCreation = dateCreation;
		this.dateCloture = dateCloture;
	}

	public Projet(String titre, Date dateCreation, Date dateCloture) {
		super();
		this.titre = titre;
		this.dateCreation = dateCreation;
		this.dateCloture = dateCloture;
	}

	public Projet() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateCloture() {
		return dateCloture;
	}

	public void setDateCloture(Date dateCloture) {
		this.dateCloture = dateCloture;
	}

	public List<User> getMembres() {
		return membres;
	}

	public void setMembres(List<User> membres) {
		this.membres = membres;
	}

	public List<Mission> getMissions() {
		return missions;
	}

	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}

	public List<Rubrique> getRubriques() {
		return rubriques;
	}

	public void setRubriques(List<Rubrique> rubriques) {
		this.rubriques = rubriques;
	}

}
