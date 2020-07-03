package tn.esprit.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Frais  implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	private String Type;
	private String Statut;
	private String Description;
	private String Justificatif;
	private Boolean Remboursable;
	public Frais(String type, String statut, String description, String justificatif, Boolean remboursable) {
		super();
		Type = type;
		Statut = statut;
		Description = description;
		Justificatif = justificatif;
		Remboursable = remboursable;
	}
	public Frais() {
		super();
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getStatut() {
		return Statut;
	}
	public void setStatut(String statut) {
		Statut = statut;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getJustificatif() {
		return Justificatif;
	}
	public void setJustificatif(String justificatif) {
		Justificatif = justificatif;
	}
	public Boolean getRemboursable() {
		return Remboursable;
	}
	public void setRemboursable(Boolean remboursable) {
		Remboursable = remboursable;
	}
	
	
	
	
}
