package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Sujet;



@Remote
public interface ISujetService {
	
	public int addSujet  (Sujet a);
	public int removeSujet  (long idSujet);
	public Sujet getSujet (long i);
	public List<Sujet> getAll();
	public Sujet updateSujet(Sujet a);

}
