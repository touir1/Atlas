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
	public int addAbsence(Absence a) {
		// TODO Auto-generated method stub
		try {
			em.persist(a);
			return 1;
		} catch(Exception e) {
			return 0;
		}
		
	}

	@Override
	public int removeAbsence(long idAbsence) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.find(Absence.class, idAbsence));
			return 1;
		}catch(Exception e) {
			return 0;	
		}
		
	}

	@Override
	public Absence getAbsence(long i) {
		// TODO Auto-generated method stub
		Absence abs = em.find(Absence.class, i);
		return abs;
	}

	@Override
	public List<Absence> getAll() {
		return em.createQuery("from Absence",Absence.class)
				.getResultList();
	}

	@Override
	public Absence updateAbsence(Absence a) {
		// TODO Auto-generated method stub
		return em.merge(a);
	}

}
