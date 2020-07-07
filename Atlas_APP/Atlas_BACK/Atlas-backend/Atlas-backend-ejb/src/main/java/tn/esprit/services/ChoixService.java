package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Choix;
import tn.esprit.interfaces.IChoixService;

@Stateless
@LocalBean
public class ChoixService implements IChoixService {

	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Override
	public boolean add(Choix a) {
		try {
			em.persist(a);
			return true;
		} catch(Exception e) {
			return false;
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
}
