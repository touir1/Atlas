package tn.esprit.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Reclamation;
import tn.esprit.interfaces.IReclamation;

@Stateless
public class ReclamationService implements IReclamation {

	@PersistenceContext(unitName="primary")
	EntityManager em;
	@Override
	public int AddReclamation(Reclamation a) {
		// TODO Auto-generated method stub
		try {
			em.persist(a);
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int RemoveReclamation(int idReclamation) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.find(Reclamation.class, idReclamation));
			return 1;
		}catch(Exception e) {
			return 0;	
		}
	}

	@Override
	public Reclamation getReclamation(int i) {
		// TODO Auto-generated method stub
		Reclamation abs = em.find(Reclamation.class, i);
		return abs;
	}

	@Override
	public List<Reclamation> getAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Reclamation",Reclamation.class)
				.getResultList();
	}

	@Override
	public Reclamation updateReclamation(Reclamation a) {
		// TODO Auto-generated method stub
		return em.merge(a);
	}

}
