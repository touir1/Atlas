package tn.esprit.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Projet;
import tn.esprit.interfaces.IProjet;

@Stateless
public class ProjetService implements IProjet {

	@PersistenceContext(unitName="primary")
	EntityManager em;
	@Override
	public int AddProjet(Projet a) {
		// TODO Auto-generated method stub
		try {
			em.persist(a);
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int RemoveProjet(int idProjet) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.find(Projet.class, idProjet));
			return 1;
		}catch(Exception e) {
			return 0;	
		}
	}

	@Override
	public Projet getProjet(int i) {
		// TODO Auto-generated method stub
		Projet abs = em.find(Projet.class, i);
		return abs;
	}

	@Override
	public List<Projet> getAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Projet",Projet.class)
				.getResultList();
	}

	@Override
	public Projet updateProjet(Projet a) {
		// TODO Auto-generated method stub
		return em.merge(a);
	}

}
