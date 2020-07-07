package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Frais;
import tn.esprit.interfaces.IFraisService;

@Stateless
@LocalBean
public class FraisService implements IFraisService {

	@PersistenceContext(unitName="primary")
	EntityManager em;
	
	@Override
	public boolean add(Frais a) {
		try {
			em.persist(a);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean remove(Frais f) {
		try {
			if(f == null) return false;
			em.remove(em.find(Frais.class, f.getId()));
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public Frais get(long id) {
		Frais abs = em.find(Frais.class, id);
		return abs;
	}

	@Override
	public List<Frais> getAll() {
		return em.createQuery("from Frais",Frais.class)
				.getResultList();
	}

	@Override
	public Frais update(Frais a) {
		return em.merge(a);
	}

}
