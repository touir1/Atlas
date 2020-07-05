package tn.esprit.services;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Role;
import tn.esprit.interfaces.IRoleService;

@Stateful
public class RoleService implements IRoleService{
	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Override
	public int addRole(Role a) {
		// TODO Auto-generated method stub
		try {
			em.persist(a);
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int removeRole(long idRole) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.find(Role.class, idRole));
			return 1;
		}catch(Exception e) {
			return 0;	
		}
	}

	@Override
	public Role getRole(long i) {
		// TODO Auto-generated method stub
		Role abs = em.find(Role.class, i);
		return abs;
	}

	@Override
	public List<Role> getAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Role",Role.class)
				.getResultList();
	}

	@Override
	public Role updateRole(Role a) {
		// TODO Auto-generated method stub
		return em.merge(a);
	}

}
