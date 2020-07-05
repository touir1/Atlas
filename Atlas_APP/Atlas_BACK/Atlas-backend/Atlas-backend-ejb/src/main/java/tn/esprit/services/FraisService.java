package tn.esprit.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Frais;
import tn.esprit.interfaces.IFrais;

@Stateless
public class FraisService implements IFrais {

	@PersistenceContext(unitName="primary")
	EntityManager em;
	
	@Override
	public int AddFrais(Frais a) {
		// TODO Auto-generated method stub
		try {
			em.persist(a);
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int RemoveFrais(int idFrais) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.find(Frais.class, idFrais));
			return 1;
		}catch(Exception e) {
			return 0;	
		}
	}

	@Override
	public Frais getFrais(int i) {
		// TODO Auto-generated method stub
		Frais abs = em.find(Frais.class, i);
		return abs;
	}

	@Override
	public List<Frais> getAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Frais",Frais.class)
				.getResultList();
	}

	@Override
	public Frais updateFrais(Frais a) {
		// TODO Auto-generated method stub
		return em.merge(a);
	}

}
