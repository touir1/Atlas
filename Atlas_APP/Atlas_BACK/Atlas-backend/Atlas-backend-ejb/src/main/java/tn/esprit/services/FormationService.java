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
	public int addFormation(Formation a) {
		// TODO Auto-generated method stub
		try {
			em.persist(a);
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int removeFormation(long idFormation) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.find(Formation.class, idFormation));
			return 1;
		}catch(Exception e) {
			return 0;	
		}
		
	}

	@Override
	public Formation getFormation(long i) {
		// TODO Auto-generated method stub
		Formation abs = em.find(Formation.class, i);
		return abs;
	}

	@Override
	public List<Formation> getAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Formation",Formation.class)
				.getResultList();
	}

	@Override
	public Formation updateFormation(Formation a) {
		// TODO Auto-generated method stub
		return em.merge(a);
	}
}
