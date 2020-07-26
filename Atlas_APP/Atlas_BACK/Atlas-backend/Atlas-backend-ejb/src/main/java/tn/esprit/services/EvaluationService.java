package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Evaluation;
import tn.esprit.entity.Projet;
import tn.esprit.entity.Question;
import tn.esprit.entity.Sujet;
import tn.esprit.entity.User;
import tn.esprit.interfaces.IEvaluationService;

@Stateless
@LocalBean
public class EvaluationService implements IEvaluationService{

	@PersistenceContext(unitName="primary")
	EntityManager em;
	
	@Override
	public Evaluation add(Evaluation a) {
		try {
			em.persist(a);
			return a;
		} catch(Exception e) {
			return null;
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

	@Override
	public Boolean affecterUserToEvalution(long idEvaluation, long idUser) {
		// TODO Auto-generated method stub
		Evaluation p = em.find(Evaluation.class, idEvaluation);
		User u = em.find(User.class, idUser);
		p.getMembres().add(u);
		u.getEvaluations().add(p);
		return true;
		
	}

	@Override
	public List<User> getMembreByEval(long idEvaluation) {
	
			// TODO Auto-generated method stub
			return em.createQuery("select m from Evaluation p join p.membres m where p.id = :evaluation",User.class)
					.setParameter("evaluation", idEvaluation)
					.getResultList();
		
	}

	@Override
	public List<Sujet> getSujetsByEval(long idEvaluation) {
		// TODO Auto-generated method stub
		return em.createQuery("select distinct r.question.sujet from Evaluation e join e.reponses r where e.id = :evaluation ",Sujet.class)
				.setParameter("evaluation", idEvaluation)
				.getResultList();
	}

	@Override
	public List<Evaluation> getEvalByUser(long idUser) {
		// TODO Auto-generated method stub
		return em.createQuery("select p from Evaluation p join p.membres m where m.id = :user",Evaluation.class)
				.setParameter("user", idUser)
				.getResultList();
	}

	
	

}
	
	
	

