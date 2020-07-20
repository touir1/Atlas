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
public class FacturationService implements IFacturationService {

	@PersistenceContext(unitName = "primary")
	EntityManager em;

	@Override
	public Facturation add(Facturation a) {
		try {
			em.persist(a);
			return a;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean remove(Facturation f) {
		try {
			if(f == null) return false;
			em.remove(em.find(Facturation.class, f.getId()));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Facturation get(long id) {
		Facturation cp = em.find(Facturation.class, id);
		return cp;
	}

	@Override
	public List<Facturation> getAll() {
		return em.createQuery("from Facturation", Facturation.class).getResultList();
	}

	@Override
	public Facturation update(Facturation a) {
		return em.merge(a);
	}

}
