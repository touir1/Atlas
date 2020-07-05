package tn.esprit.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Rapport;
import tn.esprit.interfaces.IRapportService;

@Stateless
public class RapportService implements IRapportService {

	@PersistenceContext(unitName="primary")
	EntityManager em;
	@Override
	public int addRapport(Rapport a) {
		// TODO Auto-generated method stub
		try {
			em.persist(a);
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int removeRapport(long idRapport) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.find(Rapport.class, idRapport));
			return 1;
		}catch(Exception e) {
			return 0;	
		}
	}

	@Override
	public Rapport getRapport(long i) {
		// TODO Auto-generated method stub
		Rapport abs = em.find(Rapport.class, i);
		return abs;
	}

	@Override
	public List<Rapport> getAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Rapport",Rapport.class)
				.getResultList();
	}

	@Override
	public Rapport updateRapport(Rapport a) {
		// TODO Auto-generated method stub
		return em.merge(a);
	}

}
