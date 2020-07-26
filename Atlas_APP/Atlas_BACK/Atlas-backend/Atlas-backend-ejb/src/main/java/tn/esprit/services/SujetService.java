package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Projet;
import tn.esprit.entity.Question;
import tn.esprit.entity.Sujet;
import tn.esprit.entity.User;
import tn.esprit.interfaces.ISujetService;


@Stateless
@LocalBean
public class SujetService implements ISujetService {
	
	
	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Override
	public Sujet add(Sujet a) {
		try {
			em.persist(a);
			return a;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public boolean remove(Sujet s) {
		try {
			if(s == null) return false;
			em.remove(em.find(Sujet.class, s.getId()));
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public Sujet get(long id) {
		Sujet abs = em.find(Sujet.class, id);
		return abs;
	}

	@Override
	public List<Sujet> getAll() {
		return em.createQuery("from Sujet",Sujet.class)
				.getResultList();
	}

	@Override
	public Sujet update(Sujet a) {
		return em.merge(a);
	}

		


}
