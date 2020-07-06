package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Frais;
import tn.esprit.interfaces.IFraisService;

@Stateless
@LocalBean
public class FraisService implements IFraisService {

	@PersistenceContext(unitName="primary")
	EntityManager em;
	
	@Override
	public int addFrais(Frais a) {
		// TODO Auto-generated method stub
		try {
			em.persist(a);
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int removeFrais(long idFrais) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.find(Frais.class, idFrais));
			return 1;
		}catch(Exception e) {
			return 0;	
		}
	}

	@Override
	public Frais getFrais(long i) {
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
