package tn.esprit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Rapport implements Serializable {

	private Boolean valider;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateCreation;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateImputation;
	private Integer semaine;
	private Integer mois;
	private Integer joursSemaine;
	private Float duree;

	@Id
	@ManyToOne
	private User user;
	@Id
	@ManyToOne
	private Rubrique rubrique;

	public Rapport(User user, Rubrique rubrique) {
		super();
		this.user = user;
		this.rubrique = rubrique;
	}

	public Rapport(Boolean valider, Date dateCreation, Date dateImputation, Integer semaine, Integer mois,
			Integer joursSemaine, Float duree) {
		super();
		this.valider = valider;
		this.dateCreation = dateCreation;
		this.dateImputation = dateImputation;
		this.semaine = semaine;
		this.mois = mois;
		this.joursSemaine = joursSemaine;
		this.duree = duree;
	}

	public Rapport() {
		super();
	}

	public Boolean getValider() {
		return valider;
	}

	public void setValider(Boolean valider) {
		this.valider = valider;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateImputation() {
		return dateImputation;
	}

	public void setDateImputation(Date dateImputation) {
		this.dateImputation = dateImputation;
	}

	public Integer getSemaine() {
		return semaine;
	}

	public void setSemaine(Integer semaine) {
		this.semaine = semaine;
	}

	public Integer getMois() {
		return mois;
	}

	public void setMois(Integer mois) {
		this.mois = mois;
	}

	public Integer getJoursSemaine() {
		return joursSemaine;
	}

	public void setJoursSemaine(Integer joursSemaine) {
		this.joursSemaine = joursSemaine;
	}

	public Float getDuree() {
		return duree;
	}

	public void setDuree(Float duree) {
		this.duree = duree;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Rubrique getRubrique() {
		return rubrique;
	}

	public void setRubrique(Rubrique rubrique) {
		this.rubrique = rubrique;
	}

}
