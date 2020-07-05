package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Rapport;



@Remote
public interface IRapport {
	
	public int AddRapport  (Rapport   a);
	public int RemoveRapport  (int idRapport);
	public Rapport  getRapport (int i);
	public List<Rapport> getAll();
	public Rapport  updateRapport (Rapport   a);
	

}
