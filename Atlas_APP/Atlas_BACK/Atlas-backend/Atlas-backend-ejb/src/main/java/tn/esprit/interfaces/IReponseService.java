package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Reponse;

@Remote
public interface IReponseService extends IDoubleBaseService<Reponse> {
  
	
	public List<Reponse> getReponseByEval(long idEvaluation);
}
