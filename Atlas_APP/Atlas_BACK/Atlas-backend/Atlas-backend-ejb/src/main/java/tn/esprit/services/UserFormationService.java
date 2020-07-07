package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.UserFormation;
import tn.esprit.interfaces.IUserFormation;

@Stateless
@LocalBean
public class UserFormationService implements IUserFormation{
	
	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Override
	public boolean add(UserFormation a) {
		try {
			em.persist(a);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean remove(UserFormation u) {
		try {
			if(u == null || u.getUser() == null || u.getFormation() == null) return false;
			UserFormation toRemove = em.createQuery("select r from UserFormation r where r.user.id = :user"
					+ " and r.formation.id = :formation",UserFormation.class)
					.setParameter("user", u.getUser().getId())
					.setParameter("formation", u.getFormation().getId())
					.getSingleResult();
			em.remove(toRemove);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public UserFormation get(long idUser, long idFormation) {
		UserFormation abs = em.createQuery("select r from UserFormation r where r.user.id = :user"
				+ " and r.formation.id = :formation",UserFormation.class)
				.setParameter("user", idUser)
				.setParameter("formation", idFormation)
				.getSingleResult();
		return abs;
	}

	@Override
	public List<UserFormation> getAll() {
		return em.createQuery("from UserFormation",UserFormation.class)
				.getResultList();
	}

	@Override
	public UserFormation update(UserFormation a) {
		return em.merge(a);
	}

}
