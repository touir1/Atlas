package tn.esprit.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Projet implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String titre;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(nullable = false)
	private Date dateCreation;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateCloture;
	private boolean cloturer;
	@ManyToOne
	private User createdBy;
	
	@JsonIgnore
	@ManyToMany
	private List<User> membres;

	@JsonIgnore
	@OneToMany(mappedBy = "projet")
	private List<Mission> missions;

	@JsonIgnore
	@OneToMany(mappedBy = "projet")
	private List<Rubrique> rubriques;

	public Projet(Long id) {
		super();
		this.id = id;
	}

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

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Boolean getCloturer() {
		return cloturer;
	}

	public void setCloturer(Boolean cloturer) {
		this.cloturer = cloturer;
	}
	
	

}
