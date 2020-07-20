package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Question;
import tn.esprit.interfaces.IQuestionService;

@Stateless
@LocalBean
public class QuestionService  implements IQuestionService{

	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Override
	public Question add(Question a) {
		try {
			em.persist(a);
			return a;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public boolean remove(Question q) {
		try {
			if(q == null) return false;
			em.remove(em.find(Question.class, q.getId()));
			return true;
		}catch(Exception e) {
			return false;	
		}
	}

	@Override
	public Question get(long id) {
		Question abs = em.find(Question.class, id);
		return abs;
	}

	@Override
	public List<Question> getAll() {
		return em.createQuery("from Question",Question.class)
				.getResultList();
	}

	@Override
	public Question update(Question a) {
		return em.merge(a);
	}
}
