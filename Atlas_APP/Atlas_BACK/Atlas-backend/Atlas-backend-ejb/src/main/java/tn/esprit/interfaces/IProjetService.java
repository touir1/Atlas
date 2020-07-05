package tn.esprit.interfaces;


import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Projet;

@Remote
public interface IProjetService {
	
	public int addProjet(Projet a);
	public int removeProjet(long idProjet);
	public Projet getProjet(long i);
	public List<Projet> getAll();
	public Projet updateProjet(Projet a);
	
	

}
