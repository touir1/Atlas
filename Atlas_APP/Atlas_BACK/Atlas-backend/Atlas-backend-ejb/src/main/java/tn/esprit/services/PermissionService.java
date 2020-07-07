package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Permission;
import tn.esprit.interfaces.IPermissionService;

@Stateless
@LocalBean
public class PermissionService  implements IPermissionService{

	@PersistenceContext(unitName="primary")
	EntityManager em;
	@Override
	public boolean add(Permission a) {
		try {
			em.persist(a);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean remove(Permission p) {
		try {
			if(p == null) return false;
			em.remove(em.find(Permission.class, p.getId()));
			return true;
		}catch(Exception e) {
			return false;	
		}
	}

	@Override
	public Permission get(long id) {
		Permission abs = em.find(Permission.class, id);
		return abs;
	}

	@Override
	public List<Permission> getAll() {
		return em.createQuery("from Permission",Permission.class)
				.getResultList();
	}

	@Override
	public Permission update(Permission a) {
		return em.merge(a);
	}

}
