package tn.esprit.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Mission implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String type;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(nullable = false)
	private Date date;
	@Column(nullable = false)
	private Float duree;
	@Column(nullable = false)
	private String lieu;
	@Column(nullable=false)
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "mission")
	private List<Frais> frais;

	@JsonIgnore
	@OneToMany(mappedBy = "mission")
	private List<Facturation> facturations;

	@ManyToOne
	private Projet projet;

	public Mission(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Float getDuree() {
		return duree;
	}

	public void setDuree(Float duree) {
		this.duree = duree;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public Mission(Long id, String type, Date date, Float duree, String lieu,String description) {
		super();
		this.id = id;
		this.type = type;
		this.date = date;
		this.duree = duree;
		this.lieu = lieu;
		this.description=description;
	}

	public Mission(String type, Date date, Float duree, String lieu,String description) {
		super();
		this.type = type;
		this.date = date;
		this.duree = duree;
		this.lieu = lieu;
		this.description=description;
	}

	public Mission() {
		super();
	}

	public List<Frais> getFrais() {
		return frais;
	}

	public void setFrais(List<Frais> frais) {
		this.frais = frais;
	}

	public List<Facturation> getFacturations() {
		return facturations;
	}

	public void setFacturations(List<Facturation> facturations) {
		this.facturations = facturations;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

}
