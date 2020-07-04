package tn.esprit.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	private String email;
	private String poste;
	private String image;
	private Date dateNaissance;
	private Date dateContrat;

	@OneToMany(mappedBy = "user")
	private List<Compte> comptes;
	
	@OneToMany(mappedBy="user")
	private List<Evaluation> evaluations;
	
	@ManyToMany(mappedBy="membres")
	private List<Projet> projets;

	@OneToMany(mappedBy = "user")
	private List<Frais> frais;
	
	@OneToMany(mappedBy = "user")
	private List<Absence> absences;
	
	@OneToMany(mappedBy = "user")
	private List<Reclamation> reclamations;
	
	@ManyToOne
	private User chef;

	public User(Long id, String nom, String prenom, String email, String poste, String image, Date dateNaissance,
			Date dateContrat) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.poste = poste;
		this.image = image;
		this.dateNaissance = dateNaissance;
		this.dateContrat = dateContrat;
	}

	public User(String nom, String prenom, String email, String poste, String image, Date dateNaissance,
			Date dateContrat) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.poste = poste;
		this.image = image;
		this.dateNaissance = dateNaissance;
		this.dateContrat = dateContrat;
	}

	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Date getDateContrat() {
		return dateContrat;
	}

	public void setDateContrat(Date dateContrat) {
		this.dateContrat = dateContrat;
	}

	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	public User getChef() {
		return chef;
	}

	public void setChef(User chef) {
		this.chef = chef;
	}

}
