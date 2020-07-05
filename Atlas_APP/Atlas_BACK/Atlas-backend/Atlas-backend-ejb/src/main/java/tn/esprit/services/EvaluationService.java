package tn.esprit.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Evaluation;
import tn.esprit.interfaces.IEvaluation;

@Stateless
public class EvaluationService implements IEvaluation{

	@PersistenceContext(unitName="primary")
	EntityManager em;
	
	@Override
	public int AddEvaluation(Evaluation a) {
		// TODO Auto-generated method stub
		try {
			em.persist(a);
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int RemoveEvaluation(int idEvaluation) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.find(Evaluation.class, idEvaluation));
			return 1;
		}catch(Exception e) {
			return 0;	
		}
	}

	@Override
	public Evaluation getEvaluation(int i) {
		// TODO Auto-generated method stub
		Evaluation abs = em.find(Evaluation.class, i);
		return abs;
	}

	@Override
	public List<Evaluation> getAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Evaluation",Evaluation.class)
				.getResultList();
	}

	@Override
	public Evaluation updateEvaluation(Evaluation a) {
		// TODO Auto-generated method stub
		return em.merge(a);
	}

}
