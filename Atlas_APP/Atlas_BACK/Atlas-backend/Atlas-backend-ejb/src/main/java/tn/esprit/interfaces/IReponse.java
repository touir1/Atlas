package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Reponse;


@Remote
public interface IReponse {
	
	public int AddReponse  (Reponse  a);
	public int RemoveReponse  (int idReponse);
	public Reponse  getReponse (int i);
	public List<Reponse> getAll();

}
