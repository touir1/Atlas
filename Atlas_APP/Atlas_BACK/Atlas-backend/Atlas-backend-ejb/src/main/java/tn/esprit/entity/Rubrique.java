package tn.esprit.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Rubrique implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String titre;
	private Float estimation;

	@ManyToOne
	private Projet projet;

	@JsonIgnore
	@OneToMany(mappedBy = "rubrique")
	private List<Rapport> rapports;

	public Rubrique(Long id) {
		super();
		this.id = id;
	}

	public Rubrique(Long id, String titre, Float estimation) {
		super();
		this.id = id;
		this.titre = titre;
		this.estimation = estimation;
	}

	public Rubrique(String titre, Float estimation) {
		super();
		this.titre = titre;
		this.estimation = estimation;
	}

	public Rubrique() {
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

	public Float getEstimation() {
		return estimation;
	}

	public void setEstimation(Float estimation) {
		this.estimation = estimation;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public List<Rapport> getRapports() {
		return rapports;
	}

	public void setRapports(List<Rapport> rapports) {
		this.rapports = rapports;
	}

}
