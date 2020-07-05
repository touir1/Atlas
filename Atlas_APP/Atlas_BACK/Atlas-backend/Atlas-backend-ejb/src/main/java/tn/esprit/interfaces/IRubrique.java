package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Rubrique;



@Remote
public interface IRubrique {

	public int AddRubrique  (Rubrique a);
	public int RemoveRubrique  (int idRubrique);
	public Rubrique getRubrique (int i);
	public List<Rubrique> getAll();
	public Rubrique updateRubrique(Rubrique a);
	
}
