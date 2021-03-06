package tn.esprit.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Evaluation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	
	@Column(nullable = false)
	private String status;
  
	@Column(nullable = false)
	private String titre;
	
	public String getTitre() {
		return titre;
	}



	public void setTitre(String titre) {
		this.titre = titre;
	}

	@ManyToOne
	private User user;
	
	
	
	@JsonIgnore
	@ManyToMany
	public List<User> getMembres() {
		return membres;
	}



	public void setMembres(List<User> membres) {
		this.membres = membres;
	}

	@JsonIgnore
	@ManyToMany
	private List<User> membres;
	
	@ManyToOne
	private User createdBy;

	@JsonIgnore
	@OneToMany(mappedBy = "evaluation")
	private List<Reponse> reponses;

	public Evaluation(Long id) {
		super();
		this.id = id;
	}

	

	public Evaluation(Long id, String status, User user) {
		super();
		this.id = id;
		this.status = status;
		this.user = user;
	}



	public Evaluation(User createdBy, String status) {
		super();
		this.createdBy = createdBy;
		this.status = status;
	}

	public Evaluation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Reponse> getReponses() {
		return reponses;
	}

	public void setReponses(List<Reponse> reponses) {
		this.reponses = reponses;
	}

}
