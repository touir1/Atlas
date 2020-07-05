package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Reclamation;

@Remote
public interface IReclamationService {
	
	public int addReclamation  (Reclamation  a);
	public int removeReclamation  (long idReclamation);
	public Reclamation  getReclamation (long i);
	public List<Reclamation> getAll();
	public Reclamation  updateReclamation (Reclamation  a);

}
