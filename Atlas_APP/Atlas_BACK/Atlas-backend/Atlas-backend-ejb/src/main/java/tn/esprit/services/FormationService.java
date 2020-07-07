package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import tn.esprit.entity.Formation;
import tn.esprit.interfaces.IFormationService;

@Stateless
@LocalBean
public class FormationService implements IFormationService{

	
	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Override
	public boolean add(Formation a) {
		try {
			em.persist(a);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean remove(Formation f) {
		try {
			if(f == null) return false;
			em.remove(em.find(Formation.class, f.getId()));
			return true;
		}catch(Exception e) {
			return false;	
		}
		
	}

	@Override
	public Formation get(long id) {
		Formation abs = em.find(Formation.class, id);
		return abs;
	}

	@Override
	public List<Formation> getAll() {
		return em.createQuery("from Formation",Formation.class)
				.getResultList();
	}

	@Override
	public Formation update(Formation a) {
		return em.merge(a);
	}
}
