package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Formation;


@Remote
public interface IFormationService {
	
	public int addFormation(Formation a);
	public int removeFormation(long idFormation);
	public Formation getFormation(long id);
	public List<Formation> getAll();
	public Formation updateFormation(Formation a);
	
	

}
