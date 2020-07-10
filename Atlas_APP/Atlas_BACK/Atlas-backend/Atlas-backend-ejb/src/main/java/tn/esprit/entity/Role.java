package tn.esprit.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Role implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String libelle;

	@JsonIgnore
	@ManyToMany(mappedBy = "roles")
	private List<Permission> permissions;

	@JsonIgnore
	@ManyToMany
	private List<Compte> comptes;

	public Role(Long id) {
		super();
		this.id = id;
	}

	public Role(Long id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}

	public Role(String libelle) {
		super();
		this.libelle = libelle;
	}

	public Role() {
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

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

}
