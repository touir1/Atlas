package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Rubrique;
import tn.esprit.entity.User;
import tn.esprit.interfaces.IRubriqueService;

@Stateless
@LocalBean
public class RubriqueService implements IRubriqueService {
	
	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Override
	public Rubrique add(Rubrique a) {
		try {
			em.persist(a);
			return a;
		} catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public boolean remove(Rubrique r) {
		try {
			if(r == null) return false;
			em.remove(em.find(Rubrique.class, r.getId()));
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public Rubrique get(long id) {
		Rubrique abs = em.find(Rubrique.class, id);
		return abs;
	}

	@Override
	public List<Rubrique> getAll() {
		return em.createQuery("from Rubrique",Rubrique.class)
				.getResultList();
	}

	@Override
	public Rubrique update(Rubrique a) {
		return em.merge(a);
	}

	@Override
	public List<Rubrique> getRubriqueByProjet(long idProjet) {
		// TODO Auto-generated method stub
		return em.createQuery("select r from Rubrique r where r.projet.id = :projet",Rubrique.class)
				.setParameter("projet", idProjet)
				.getResultList();
	}

}
