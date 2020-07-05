package tn.esprit.services;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Reponse;
import tn.esprit.interfaces.IReponse;

@Stateful
public class ReponseService implements IReponse {

	
	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Override
	public int AddReponse(Reponse a) {
		// TODO Auto-generated method stub
		try {
			em.persist(a);
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int RemoveReponse(int idReponse) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.find(Reponse.class, idReponse));
			return 1;
		}catch(Exception e) {
			return 0;	
		}
	}

	@Override
	public Reponse getReponse(int i) {
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
