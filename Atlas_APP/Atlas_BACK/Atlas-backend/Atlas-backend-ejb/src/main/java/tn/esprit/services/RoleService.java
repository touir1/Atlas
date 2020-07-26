package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Role;
import tn.esprit.interfaces.IRoleService;

@Stateless
@LocalBean
public class RoleService implements IRoleService{
	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Override
	public Role add(Role a) {
		try {
			em.persist(a);
			return a;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public boolean remove(Role r) {
		try {
			if(r == null) return false;
			em.remove(em.find(Role.class, r.getId()));
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public Role get(long id) {
		Role abs = em.find(Role.class, id);
		return abs;
	}

	@Override
	public List<Role> getAll() {
		return em.createQuery("from Role",Role.class)
				.getResultList();
	}

	@Override
	public Role update(Role a) {
		return em.merge(a);
	}

	@Override
	public boolean existsRole(String libelle) {
		try {
			Role role = em
					.createQuery("select r from Role r where lower(r.libelle) = lower(:libelle)", Role.class)
					.setParameter("libelle", libelle)
					.getSingleResult();
			if(role == null) return false;
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

}
