package tn.esprit.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Choix;
import tn.esprit.interfaces.IChoixService;

@Stateless
public class ChoixService implements IChoixService {

	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Override
	public int addChoix(Choix a) {
		// TODO Auto-generated method stub
		try {
			em.persist(a);
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int removeChoix(long idChoix) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.find(Choix.class, idChoix));
			return 1;
		}catch(Exception e) {
			return 0;	
		}
	}

	@Override
	public Choix getChoix(long id) {
		// TODO Auto-generated method stub
		Choix ch = em.find(Choix.class, id);
				return ch;
	}

	@Override
	public List<Choix> getAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Choix",Choix.class)
				.getResultList();	}

	@Override
	public Choix updateChoix(Choix a) {
		// TODO Auto-generated method stub
		return em.merge(a);
	}
}
