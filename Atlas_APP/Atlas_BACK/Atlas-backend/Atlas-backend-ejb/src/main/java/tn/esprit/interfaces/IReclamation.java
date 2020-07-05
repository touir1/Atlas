package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Reclamation;

@Remote
public interface IReclamation {
	
	public int AddReclamation  (Reclamation  a);
	public int RemoveReclamation  (int idReclamation);
	public Reclamation  getReclamation (int i);
	public List<Reclamation> getAll();

}
