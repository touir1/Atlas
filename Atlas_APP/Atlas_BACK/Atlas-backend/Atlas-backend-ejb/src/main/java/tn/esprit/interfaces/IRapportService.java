package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Rapport;



@Remote
public interface IRapportService {
	
	public int addRapport  (Rapport   a);
	public int removeRapport  (long idRapport);
	public Rapport  getRapport (long i);
	public List<Rapport> getAll();
	public Rapport  updateRapport (Rapport   a);
	

}
