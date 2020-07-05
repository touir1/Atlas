package tn.esprit.interfaces;


import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Projet;

@Remote
public interface IProjet {
	
	public int AddProjet(Projet a);
	public int RemoveProjet(int idProjet);
	public Projet getProjet(int i);
	public List<Projet> getAll();
	
	

}
