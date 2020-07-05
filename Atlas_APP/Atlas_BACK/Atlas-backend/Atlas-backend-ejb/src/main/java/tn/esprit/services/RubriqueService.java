package tn.esprit.services;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Rubrique;
import tn.esprit.interfaces.IRubrique;

@Stateful
public class RubriqueService implements IRubrique {
	
	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Override
	public int AddRubrique(Rubrique a) {
		// TODO Auto-generated method stub
		try {
			em.persist(a);
			return 1;
		} catch(Exception e) {
			return 0;
		}
		
	}

	@Override
	public int RemoveRubrique(int idRubrique) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.find(Rubrique.class, idRubrique));
			return 1;
		}catch(Exception e) {
			return 0;	
		}
	}

	@Override
	public Rubrique getRubrique(int i) {
		// TODO Auto-generated method stub
		Rubrique abs = em.find(Rubrique.class, i);
		return abs;
	}

	@Override
	public List<Rubrique> getAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Rubrique",Rubrique.class)
				.getResultList();
	}

	@Override
	public Rubrique updateRubrique(Rubrique a) {
		// TODO Auto-generated method stub
		return em.merge(a);
	}

}
