package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Projet;
import tn.esprit.interfaces.IProjetService;

@Stateless
@LocalBean
public class ProjetService implements IProjetService {

	@PersistenceContext(unitName="primary")
	EntityManager em;
	@Override
	public boolean add(Projet a) {
		try {
			em.persist(a);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean remove(Projet p) {
		try {
			if(p == null) return false;
			em.remove(em.find(Projet.class, p.getId()));
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public Projet get(long id) {
		Projet abs = em.find(Projet.class, id);
		return abs;
	}

	@Override
	public List<Projet> getAll() {
		return em.createQuery("from Projet",Projet.class)
				.getResultList();
	}

	@Override
	public Projet update(Projet a) {
		return em.merge(a);
	}

}
