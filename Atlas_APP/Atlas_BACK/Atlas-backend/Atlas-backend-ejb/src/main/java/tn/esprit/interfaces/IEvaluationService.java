package tn.esprit.interfaces;

import javax.ejb.Remote;

import tn.esprit.entity.Evaluation;

@Remote
public interface IEvaluationService extends IBaseService<Evaluation> {

}
