package tn.esprit.services;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Sujet;
import tn.esprit.interfaces.ISujet;


@Stateful
public class SujetService implements ISujet {
	
	
	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Override
	public int AddSujet(Sujet a) {
		// TODO Auto-generated method stub
		try {
			em.persist(a);
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int RemoveSujet(int idSujet) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.find(Sujet.class, idSujet));
			return 1;
		}catch(Exception e) {
			return 0;	
		}
	}

	@Override
	public Sujet getSujet(int i) {
		// TODO Auto-generated method stub
		Sujet abs = em.find(Sujet.class, i);
		return abs;
	}

	@Override
	public List<Sujet> getAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Sujet",Sujet.class)
				.getResultList();
	}

	@Override
	public Sujet updateSujet(Sujet a) {
		// TODO Auto-generated method stub
		return em.merge(a);
	}

}
