package tn.esprit.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import tn.esprit.entity.Mission;
import tn.esprit.interfaces.IMission;

@Stateless
public class MissionService implements IMission {

	@PersistenceContext(unitName="primary")
	EntityManager em;
	@Override
	public int AddMission(Mission a) {
		// TODO Auto-generated method stub
		try {
			em.persist(a);
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int RemoveMission(int idMission) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.find(Mission.class, idMission));
			return 1;
		}catch(Exception e) {
			return 0;	
		}
	}

	@Override
	public Mission getMission(int i) {
		// TODO Auto-generated method stub
		Mission abs = em.find(Mission.class, i);
		return abs;
	}

	@Override
	public List<Mission> getAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Mission",Mission.class)
				.getResultList();
	}

	@Override
	public Mission updateMission(Mission a) {
		// TODO Auto-generated method stub
		return em.merge(a);
	}

}
