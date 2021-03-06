package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Choix;
import tn.esprit.entity.Evaluation;
import tn.esprit.interfaces.IChoixService;

@Stateless
@LocalBean
public class ChoixService implements IChoixService {

	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Override
	public Choix add(Choix a) {
		try {
			em.persist(a);
			return a;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public boolean remove(Choix c) {
		try {
			if(c == null) return false;
			em.remove(em.find(Choix.class, c.getId()));
			return true;
		}catch(Exception e) {
			return false;	
		}
		
	}

	@Override
	public Choix get(long id) {
		Choix ch = em.find(Choix.class, id);
				return ch;
	}

	@Override
	public List<Choix> getAll() {
		return em.createQuery("from Choix",Choix.class)
				.getResultList();	}

	@Override
	public Choix update(Choix a) {
		return em.merge(a);
	}

	@Override
	public List<Choix> getChoixByQuestion(long idQuestion) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from Choix c join c.question q where q.id = :question",Choix.class)
				.setParameter("question", idQuestion)
				.getResultList();
	}
}
