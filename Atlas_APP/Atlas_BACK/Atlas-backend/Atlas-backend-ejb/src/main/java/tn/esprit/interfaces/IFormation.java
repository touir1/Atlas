package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Formation;


@Remote
public interface IFormation {
	
	public int AddFormation(Formation a);
	public int RemoveFormation(int idFormation);
	public Formation getFormation(int i);
	public List<Formation> getAll();
	public Formation updateFormation(Formation a);
	
	

}
