package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Sujet;



@Remote
public interface ISujet {
	
	public int AddSujet  (Sujet a);
	public int RemoveSujet  (int idSujet);
	public Sujet getSujet (int i);
	public List<Sujet> getAll();
	public Sujet updateSujet(Sujet a);

}
