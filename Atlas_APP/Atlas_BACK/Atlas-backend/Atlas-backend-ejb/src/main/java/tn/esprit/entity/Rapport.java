package tn.esprit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
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
	@Column(nullable = false)
	private Date dateCreation;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(nullable = false)
	private Date dateImputation;
	@Column(nullable = false)
	private Integer mois;
	@Column(nullable = false)
	private Float dureeLundi;
	@Column(nullable = false)
	private Float dureeMardi;
	@Column(nullable = false)
	private Float dureeMercredi;
	@Column(nullable = false)
	private Float dureeJeudi;
	@Column(nullable = false)
	private Float dureeVendredi;

	@Id
	@ManyToOne
	private User user;
	@Id
	@ManyToOne
	private Rubrique rubrique;
	@Column(nullable = true)
	private Integer joursSemaine;
	@Column(nullable = false)
	@Id
	private Integer annee;
	@Column(nullable = false)
	@Id
	private Integer semaine;

	public Rapport(User user, Rubrique rubrique) {
		super();
		this.user = user;
		this.rubrique = rubrique;
	}



	public Rapport(Boolean valider, Date dateCreation, Date dateImputation, Integer mois, Float dureeLundi,
			Float dureeMardi, Float dureeMercredi, Float dureeJeudi, Float dureeVendredi, Integer joursSemaine,
			Integer annee, Integer semaine) {
		super();
		this.valider = valider;
		this.dateCreation = dateCreation;
		this.dateImputation = dateImputation;
		this.mois = mois;
		this.dureeLundi = dureeLundi;
		this.dureeMardi = dureeMardi;
		this.dureeMercredi = dureeMercredi;
		this.dureeJeudi = dureeJeudi;
		this.dureeVendredi = dureeVendredi;
		this.joursSemaine = joursSemaine;
		this.annee = annee;
		this.semaine = semaine;
	}



	public Float getDureeLundi() {
		return dureeLundi;
	}



	public void setDureeLundi(Float dureeLundi) {
		this.dureeLundi = dureeLundi;
	}



	public Float getDureeMardi() {
		return dureeMardi;
	}



	public void setDureeMardi(Float dureeMardi) {
		this.dureeMardi = dureeMardi;
	}



	public Float getDureeMercredi() {
		return dureeMercredi;
	}



	public void setDureeMercredi(Float dureeMercredi) {
		this.dureeMercredi = dureeMercredi;
	}



	public Float getDureeJeudi() {
		return dureeJeudi;
	}



	public void setDureeJeudi(Float dureeJeudi) {
		this.dureeJeudi = dureeJeudi;
	}



	public Float getDureeVendredi() {
		return dureeVendredi;
	}



	public void setDureeVendredi(Float dureeVendredi) {
		this.dureeVendredi = dureeVendredi;
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

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

}
