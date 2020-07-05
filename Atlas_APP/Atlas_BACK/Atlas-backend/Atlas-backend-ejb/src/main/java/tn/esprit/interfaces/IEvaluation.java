package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Evaluation;


@Remote
public interface IEvaluation {
	public int AddEvaluation(Evaluation a);
	public int RemoveEvaluation(int idEvaluation);
	public Evaluation getEvaluation(int i);
	public List<Evaluation> getAll();
	public Evaluation updateEvaluation(Evaluation a);

}
