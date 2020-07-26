package tn.esprit.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Configuration implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="configuration_key", nullable = false, unique = true)
	private String key;
	@Column(name="configuration_value", nullable = false)
	private String value;

	public Configuration() {
		super();
	}

	public Configuration(Long id) {
		super();
		this.id = id;
	}

	public Configuration(Long id, String key, String value) {
		super();
		this.id = id;
		this.key = key;
		this.value = value;
	}

	public Configuration(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
