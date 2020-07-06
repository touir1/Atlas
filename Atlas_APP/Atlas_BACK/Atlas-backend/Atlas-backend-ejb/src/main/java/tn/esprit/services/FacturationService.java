package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import tn.esprit.entity.Facturation;
import tn.esprit.interfaces.IFacturationService;

@Stateless
@LocalBean
public class FacturationService implements IFacturationService{
	
	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Override
	public int addFacturation(Facturation a) {
		// TODO Auto-generated method stub
		try {
			em.persist(a);
			return 1;
		} catch(Exception e) {
					return 0;
		}
	}

	@Override
	public int removeFacturation(long idFacturation) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.find(Facturation.class, idFacturation));
			return 1;
		}catch(Exception e) {
			return 0;	
		}
	}

	@Override
	public Facturation getFacturation(long i) {
		// TODO Auto-generated method stub
		Facturation cp = em.find(Facturation.class, i);
		return cp;
	}

	@Override
	public List<Facturation> getAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Facturation",Facturation.class)
				.getResultList();
	}

	@Override
	public Facturation updateFacturation(Facturation a) {
		// TODO Auto-generated method stub
		return em.merge(a);
	}

}
