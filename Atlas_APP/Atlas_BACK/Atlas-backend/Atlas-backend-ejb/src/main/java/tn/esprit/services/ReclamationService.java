package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Reclamation;
import tn.esprit.interfaces.IReclamationService;

@Stateless
@LocalBean
public class ReclamationService implements IReclamationService {

	@PersistenceContext(unitName="primary")
	EntityManager em;
	@Override
	public boolean add(Reclamation a) {
		try {
			em.persist(a);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean remove(Reclamation r) {
		try {
			if(r == null) return false;
			em.remove(em.find(Reclamation.class, r.getId()));
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public Reclamation get(long id) {
		Reclamation abs = em.find(Reclamation.class, id);
		return abs;
	}

	@Override
	public List<Reclamation> getAll() {
		return em.createQuery("from Reclamation",Reclamation.class)
				.getResultList();
	}

	@Override
	public Reclamation update(Reclamation a) {
		return em.merge(a);
	}

}
