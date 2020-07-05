package tn.esprit.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Question;
import tn.esprit.interfaces.IQuestion;

@Stateless
public class QuestionService  implements IQuestion{

	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Override
	public int AddQuestion(Question a) {
		// TODO Auto-generated method stub
		try {
			em.persist(a);
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int RemoveQuestion(int idQuestion) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.find(Question.class, idQuestion));
			return 1;
		}catch(Exception e) {
			return 0;	
		}
	}

	@Override
	public Question getQuestion(int i) {
		// TODO Auto-generated method stub
		Question abs = em.find(Question.class, i);
		return abs;
	}

	@Override
	public List<Question> getAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Question",Question.class)
				.getResultList();
	}

	@Override
	public Question updateQuestion(Question a) {
		// TODO Auto-generated method stub
		return em.merge(a);
	}
}
