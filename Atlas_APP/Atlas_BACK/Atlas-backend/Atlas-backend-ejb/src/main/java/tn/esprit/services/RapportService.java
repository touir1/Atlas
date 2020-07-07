package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Rapport;
import tn.esprit.interfaces.IRapportService;

@Stateless
@LocalBean
public class RapportService implements IRapportService {

	@PersistenceContext(unitName="primary")
	EntityManager em;
	@Override
	public boolean add(Rapport a) {
		try {
			em.persist(a);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean remove(Rapport r) {
		try {
			if(r == null || r.getUser() == null || r.getRubrique() == null) return false;
			Rapport toRemove = em.createQuery("select r from Rapport r where r.user.id = :user"
					+ " and r.rubrique.id = :rubrique",Rapport.class)
					.setParameter("user", r.getUser().getId())
					.setParameter("rubrique", r.getRubrique().getId())
					.getSingleResult();
			em.remove(toRemove);
			return true;
		}catch(Exception e) {
			return false;	
		}
	}

	@Override
	public Rapport get(long idUser, long idRubrique) {
		Rapport abs = em.createQuery("select r from Rapport r where r.user.id = :user"
				+ " and r.rubrique.id = :rubrique",Rapport.class)
				.setParameter("user", idUser)
				.setParameter("rubrique", idRubrique)
				.getSingleResult();
		return abs;
	}

	@Override
	public List<Rapport> getAll() {
		return em.createQuery("from Rapport",Rapport.class)
				.getResultList();
	}

	@Override
	public Rapport update(Rapport a) {
		return em.merge(a);
	}

}
