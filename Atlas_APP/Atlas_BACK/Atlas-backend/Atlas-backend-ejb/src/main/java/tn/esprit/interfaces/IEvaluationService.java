package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Evaluation;
import tn.esprit.entity.Sujet;
import tn.esprit.entity.User;

@Remote
public interface IEvaluationService extends IBaseService<Evaluation> {
	public Boolean affecterUserToEvalution(long idEvaluation, long idUser);
	public List<User> getMembreByEval(long idEvaluation);
	public List<Sujet> getSujetsByEval(long idEvaluation);
	public List<Evaluation> getEvalByUser(long idUser);
		
	

}
