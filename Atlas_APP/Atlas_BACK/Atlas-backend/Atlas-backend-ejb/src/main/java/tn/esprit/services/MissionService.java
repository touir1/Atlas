package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import tn.esprit.entity.Mission;
import tn.esprit.interfaces.IMissionService;

@Stateless
@LocalBean
public class MissionService implements IMissionService {

	@PersistenceContext(unitName="primary")
	EntityManager em;
	@Override
	public boolean add(Mission a) {
		try {
			em.persist(a);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean remove(Mission m) {
		try {
			if(m == null) return false;
			em.remove(em.find(Mission.class, m.getId()));
			return true;
		}catch(Exception e) {
			return false;	
		}
	}

	@Override
	public Mission get(long id) {
		Mission abs = em.find(Mission.class, id);
		return abs;
	}

	@Override
	public List<Mission> getAll() {
		return em.createQuery("from Mission",Mission.class)
				.getResultList();
	}

	@Override
	public Mission update(Mission a) {
		return em.merge(a);
	}

}
