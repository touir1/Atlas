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
	public int addPermission(Permission a) {
		// TODO Auto-generated method stub
		try {
			em.persist(a);
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int removePermission(long idPermission) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.find(Permission.class, idPermission));
			return 1;
		}catch(Exception e) {
			return 0;	
		}
	}

	@Override
	public Permission getPermission(long i) {
		// TODO Auto-generated method stub
		Permission abs = em.find(Permission.class, i);
		return abs;
	}

	@Override
	public List<Permission> getAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Permission",Permission.class)
				.getResultList();
	}

	@Override
	public Permission updatePermission(Permission a) {
		// TODO Auto-generated method stub
		return em.merge(a);
	}

}
