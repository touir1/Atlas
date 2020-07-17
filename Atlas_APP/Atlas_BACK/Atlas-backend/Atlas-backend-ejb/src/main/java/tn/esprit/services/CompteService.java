package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Compte;
import tn.esprit.entity.Rapport;
import tn.esprit.interfaces.ICompteService;

@Stateless
@LocalBean
public class CompteService implements ICompteService {

	@PersistenceContext(unitName = "primary")
	EntityManager em;

	@Override
	public boolean add(Compte a) {
		try {
			em.persist(a);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean remove(Compte c) {
		try {
			if (c == null)
				return false;
			em.remove(em.find(Compte.class, c.getId()));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Compte get(long id) {
		Compte cp = em.find(Compte.class, id);
		return cp;
	}

	@Override
	public List<Compte> getAll() {
		return em.createQuery("from Compte", Compte.class).getResultList();
	}

	@Override
	public Compte update(Compte a) {
		return em.merge(a);
	}

	@Override
	public Compte login(String username, String pwd) {
		// TODO Auto-generated method stub
		Compte abs = em
				.createQuery("select c from Compte c where c.username = :username" + " and c.password= :password",
						Compte.class)
				.setParameter("username", username).setParameter("password", pwd).getSingleResult();

		return abs;
	}

}
