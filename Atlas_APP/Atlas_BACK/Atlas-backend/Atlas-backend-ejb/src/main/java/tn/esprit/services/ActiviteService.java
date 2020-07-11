package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Absence;
import tn.esprit.entity.Activite;
import tn.esprit.interfaces.IActiviteService;

@Stateless
@LocalBean
public class ActiviteService implements IActiviteService {
	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Override
	public boolean add(Activite entity) {
		// TODO Auto-generated method stub
		try {
			em.persist(entity);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public Activite update(Activite entity) {
		// TODO Auto-generated method stub
		return em.merge(entity);
	}

	@Override
	public boolean remove(Activite entity) {
		// TODO Auto-generated method stub
		try {
			if(entity == null) return false;
			em.remove(em.find(Absence.class, entity.getId()));
			return true;
		}catch(Exception e) {
			return false;	
		}
	}

	@Override
	public Activite get(long id) {
		// TODO Auto-generated method stub
		Activite abs = em.find(Activite.class, id);
		return abs;
	}

	@Override
	public List<Activite> getAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Activite",Activite.class)
				.getResultList();
	}

}
