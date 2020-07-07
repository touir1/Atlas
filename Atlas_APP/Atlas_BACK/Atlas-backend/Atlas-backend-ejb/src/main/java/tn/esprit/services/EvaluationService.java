package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Evaluation;
import tn.esprit.interfaces.IEvaluationService;

@Stateless
@LocalBean
public class EvaluationService implements IEvaluationService{

	@PersistenceContext(unitName="primary")
	EntityManager em;
	
	@Override
	public boolean add(Evaluation a) {
		try {
			em.persist(a);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean remove(Evaluation ev) {
		try {
			if(ev == null) return false;
			em.remove(em.find(Evaluation.class, ev.getId()));
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public Evaluation get(long id) {
		Evaluation abs = em.find(Evaluation.class, id);
		return abs;
	}

	@Override
	public List<Evaluation> getAll() {
		return em.createQuery("from Evaluation",Evaluation.class)
				.getResultList();
	}

	@Override
	public Evaluation update(Evaluation a) {
		return em.merge(a);
	}

}
