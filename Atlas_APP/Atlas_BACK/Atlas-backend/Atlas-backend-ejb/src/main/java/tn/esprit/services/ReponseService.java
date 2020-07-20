package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Reponse;
import tn.esprit.interfaces.IReponseService;

@Stateless
@LocalBean
public class ReponseService implements IReponseService {

	
	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Override
	public Reponse add(Reponse a) {
		try {
			em.persist(a);
			return a;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public boolean remove(Reponse r) {
		try {
			if(r == null || r.getEvaluation() == null || r.getQuestion() == null) return false;
			Reponse toRemove = em.createQuery("select r from Reponse r where r.evaluation.id = :evaluation"
					+ " and r.question.id = :question",Reponse.class)
					.setParameter("evaluation", r.getEvaluation().getId())
					.setParameter("question", r.getQuestion().getId())
					.getSingleResult();
			em.remove(toRemove);
			return true;
		}catch(Exception e) {
			return false;	
		}
	}

	@Override
	public Reponse get(long idEvaluation, long idQuestion) {
		Reponse abs = em.createQuery("select r from Reponse r where r.evaluation.id = :evaluation"
				+ " and r.question.id = :question",Reponse.class)
				.setParameter("evaluation", idEvaluation)
				.setParameter("question", idQuestion)
				.getSingleResult();
		return abs;
	}

	@Override
	public List<Reponse> getAll() {
		return em.createQuery("from Reponse",Reponse.class)
				.getResultList();
	}

	@Override
	public Reponse update(Reponse a) {
		return em.merge(a);
	}

}
