package tn.esprit.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Compte  implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	private String Username;
	private String Password;
	public Compte(String username, String password) {
		super();
		Username = username;
		Password = password;
	}
	
	public Compte() {
		super();
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
	
	
}
