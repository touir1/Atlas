package tn.esprit.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Frais implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	private String status;
	private String description;
	private String justificatif;
	private Boolean remboursable;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Mission mission;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJustificatif() {
		return justificatif;
	}

	public void setJustificatif(String justificatif) {
		this.justificatif = justificatif;
	}

	public Boolean getRemboursable() {
		return remboursable;
	}

	public void setRemboursable(Boolean remboursable) {
		this.remboursable = remboursable;
	}

	public Frais(Long id, String type, String status, String description, String justificatif, Boolean remboursable) {
		super();
		this.id = id;
		this.type = type;
		this.status = status;
		this.description = description;
		this.justificatif = justificatif;
		this.remboursable = remboursable;
	}

	public Frais(String type, String status, String description, String justificatif, Boolean remboursable) {
		super();
		this.type = type;
		this.status = status;
		this.description = description;
		this.justificatif = justificatif;
		this.remboursable = remboursable;
	}

	public Frais() {
		super();
	}

}
