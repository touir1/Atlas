package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;


import tn.esprit.entity.Reponse;


@Remote
public interface IReponseService {
	
	public int addReponse  (Reponse  a);
	public int removeReponse  (long idReponse);
	public Reponse  getReponse (long i);
	public List<Reponse> getAll();
	public Reponse updateReponse(Reponse a);

}
