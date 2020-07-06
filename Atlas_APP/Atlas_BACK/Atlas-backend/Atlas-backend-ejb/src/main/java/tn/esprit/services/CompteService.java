package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import tn.esprit.entity.Compte;
import tn.esprit.interfaces.ICompteService;

@Stateless
@LocalBean
public class CompteService implements ICompteService{

	@PersistenceContext(unitName="primary")
	EntityManager em;
	@Override
	public int addCompte(Compte a) {
		// TODO Auto-generated method stub
		try {
			em.persist(a);
			return 1;
		} catch(Exception e) {
					return 0;
		}
	}

	@Override
	public int removeCompte(long idCompte) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.find(Compte.class, idCompte));
			return 1;
		}catch(Exception e) {
			return 0;	
		}
	}

	@Override
	public Compte getCompte(long i) {
		// TODO Auto-generated method stub
		Compte cp = em.find(Compte.class, i);
		return cp;
	}

	@Override
	public List<Compte> getAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Compte",Compte.class)
				.getResultList();
	}

	@Override
	public Compte updateCompte(Compte a) {
		// TODO Auto-generated method stub
		return em.merge(a);
	}

}
