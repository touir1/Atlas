package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Absence;
import tn.esprit.interfaces.IAbsenceService;

@Stateless
@LocalBean
public class AbsenceService  implements IAbsenceService{
	
	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Override
	public Absence add(Absence a) {
		try {
			em.persist(a);
			return a;
		} catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public boolean remove(Absence a) {
		try {
			if(a == null) return false;
			em.remove(em.find(Absence.class, a.getId()));
			return true;
		}catch(Exception e) {
			return false;	
		}
		
	}

	@Override
	public Absence get(long id) {
		Absence abs = em.find(Absence.class, id);
		return abs;
	}

	@Override
	public List<Absence> getAll() {
		return em.createQuery("from Absence",Absence.class)
				.getResultList();
	}

	@Override
	public Absence update(Absence a) {
		return em.merge(a);
	}

	@Override
	public List<Absence> getListByStatusForManager(String status, long idManager) {
		return em.createQuery("select a from Absence a where a.status = :status"
				+ " and a.user.chef.id = :manager",Absence.class)
				.setParameter("status", status)
				.setParameter("manager", idManager)
				.getResultList();
	}

	@Override
	public List<Absence> getListByStatusForUser(String status, long idUser) {
		return em.createQuery("select a from Absence a where a.status = :status"
				+ " and a.user.id = :user",Absence.class)
				.setParameter("status", status)
				.setParameter("user", idUser)
				.getResultList();
	}

}
