package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Reponse;
import tn.esprit.interfaces.IReponseService;

@Stateless
@LocalBean
public class ReponseService implements IReponseService {

	
	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Override
	public int addReponse(Reponse a) {
		// TODO Auto-generated method stub
		try {
			em.persist(a);
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int removeReponse(long idReponse) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.find(Reponse.class, idReponse));
			return 1;
		}catch(Exception e) {
			return 0;	
		}
	}

	@Override
	public Reponse getReponse(long i) {
		// TODO Auto-generated method stub
		Reponse abs = em.find(Reponse.class, i);
		return abs;
	}

	@Override
	public List<Reponse> getAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Reponse",Reponse.class)
				.getResultList();
	}

	@Override
	public Reponse updateReponse(Reponse a) {
		// TODO Auto-generated method stub
		return em.merge(a);
	}

}
