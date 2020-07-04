package tn.esprit.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Permission implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ecran;
	private String action;
	private String application;

	@ManyToMany
	private List<Role> roles;

	public Permission(Long id, String ecran, String action, String application) {
		super();
		this.id = id;
		this.ecran = ecran;
		this.action = action;
		this.application = application;
	}

	public Permission(String ecran, String action, String application) {
		super();
		this.ecran = ecran;
		this.action = action;
		this.application = application;
	}

	public Permission() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEcran() {
		return ecran;
	}

	public void setEcran(String ecran) {
		this.ecran = ecran;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
