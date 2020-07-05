package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Rubrique;



@Remote
public interface IRubriqueService {

	public int addRubrique  (Rubrique a);
	public int removeRubrique  (long idRubrique);
	public Rubrique getRubrique (long i);
	public List<Rubrique> getAll();
	public Rubrique updateRubrique(Rubrique a);
	
}
