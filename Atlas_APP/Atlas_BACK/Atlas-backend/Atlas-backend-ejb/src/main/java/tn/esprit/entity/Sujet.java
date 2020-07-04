package tn.esprit.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sujet implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titre;
	private Integer coeficient;
	private Boolean noter;

	@OneToMany(mappedBy = "sujet")
	private List<Question> questions;

	public Sujet(Long id, String titre, Integer coeficient, Boolean noter) {
		super();
		this.id = id;
		this.titre = titre;
		this.coeficient = coeficient;
		this.noter = noter;
	}

	public Sujet(String titre, Integer coeficient, Boolean noter) {
		super();
		this.titre = titre;
		this.coeficient = coeficient;
		this.noter = noter;
	}

	public Sujet() {
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

	public Integer getCoeficient() {
		return coeficient;
	}

	public void setCoeficient(Integer coeficient) {
		this.coeficient = coeficient;
	}

	public Boolean getNoter() {
		return noter;
	}

	public void setNoter(Boolean noter) {
		this.noter = noter;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}
