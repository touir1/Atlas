package tn.esprit.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nom;
	@Column(nullable = false)
	private String prenom;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String poste;
	private String image;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateNaissance;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(nullable = false)
	private Date dateContrat;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Compte> comptes;

	@JsonIgnore
	@OneToMany(mappedBy = "createdBy")
	private List<Projet> projetsCreated;

	@JsonIgnore
	@OneToMany(mappedBy = "createdBy")
	private List<Evaluation> evaluationCreated;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Evaluation> evaluations;

	@JsonIgnore
	@ManyToMany(mappedBy = "membres")
	private List<Projet> projets;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Frais> frais;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Absence> absences;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Reclamation> reclamations;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<UserFormation> userFormations;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Rapport> rapports;

	@ManyToOne
	private User chef;

	@JsonIgnore
	@OneToMany(mappedBy = "chef")
	private List<User> membresEquipe;

	public User(Long id) {
		super();
		this.id = id;
	}

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

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	public List<Projet> getProjets() {
		return projets;
	}

	public void setProjets(List<Projet> projets) {
		this.projets = projets;
	}

	public List<Frais> getFrais() {
		return frais;
	}

	public void setFrais(List<Frais> frais) {
		this.frais = frais;
	}

	public List<Absence> getAbsences() {
		return absences;
	}

	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}

	public List<Reclamation> getReclamations() {
		return reclamations;
	}

	public void setReclamations(List<Reclamation> reclamations) {
		this.reclamations = reclamations;
	}

	public List<UserFormation> getUserFormations() {
		return userFormations;
	}

	public void setUserFormations(List<UserFormation> userFormations) {
		this.userFormations = userFormations;
	}

	public List<Rapport> getRapports() {
		return rapports;
	}

	public void setRapports(List<Rapport> rapports) {
		this.rapports = rapports;
	}

	public List<Projet> getProjetsCreated() {
		return projetsCreated;
	}

	public void setProjetsCreated(List<Projet> projetsCreated) {
		this.projetsCreated = projetsCreated;
	}

	public List<Evaluation> getEvaluationCreated() {
		return evaluationCreated;
	}

	public void setEvaluationCreated(List<Evaluation> evaluationCreated) {
		this.evaluationCreated = evaluationCreated;
	}

	public List<User> getMembresEquipe() {
		return membresEquipe;
	}

	public void setMembresEquipe(List<User> membresEquipe) {
		this.membresEquipe = membresEquipe;
	}

	@PreRemove
	public void removeRelations() {
		if (membresEquipe != null) {
			for (User user : membresEquipe) {
				user.setChef(null);
			}
		}
		if (comptes != null) {
			for (Compte compte : comptes) {
				compte.setUser(null);
			}
		}
		if (projetsCreated != null) {
			for (Projet projet : projetsCreated) {
				projet.setCreatedBy(null);
			}
		}
		if (evaluationCreated != null) {
			for (Evaluation evaluation : evaluationCreated) {
				evaluation.setCreatedBy(null);
			}
		}
		if (evaluations != null) {
			for (Evaluation evaluation : evaluations) {
				evaluation.setUser(null);
			}
		}
		if (projets != null) {
			for (Projet projet : projets) {
				projet.getMembres().remove(this);
			}
		}
		if (frais != null) {
			for (Frais frais : frais) {
				frais.setUser(null);
			}
		}
		if (absences != null) {
			for (Absence absence : absences) {
				absence.setUser(null);
			}
		}
		if (reclamations != null) {
			for (Reclamation reclamation : reclamations) {
				reclamation.setUser(null);
			}
		}
		if (userFormations != null) {
			for (UserFormation userFormation : userFormations) {
				userFormation.setUser(null);
			}
		}
		if (rapports != null) {
			for (Rapport rapport : rapports) {
				rapport.setUser(null);
			}
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
