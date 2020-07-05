package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Evaluation;


@Remote
public interface IEvaluationService {
	public int qddEvaluation(Evaluation a);
	public int removeEvaluation(long idEvaluation);
	public Evaluation getEvaluation(long i);
	public List<Evaluation> getAll();
	public Evaluation updateEvaluation(Evaluation a);

}
