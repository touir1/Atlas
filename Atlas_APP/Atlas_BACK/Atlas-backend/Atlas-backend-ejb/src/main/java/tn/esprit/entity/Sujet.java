package tn.esprit.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Sujet implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String titre;
	private Integer coeficient;
	@Column(nullable = false)
	private Boolean noter;

	@JsonIgnore
	@OneToMany(mappedBy = "sujet")
	private List<Question> questions;

	public Sujet(Long id) {
		super();
		this.id = id;
	}

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
