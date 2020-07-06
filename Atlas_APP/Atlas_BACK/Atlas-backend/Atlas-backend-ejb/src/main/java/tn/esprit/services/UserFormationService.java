package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
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
	public int addUserFormation(UserFormation a) {
		// TODO Auto-generated method stub
		try {
			em.persist(a);
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int removeUserFormation(long idUserFormation) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.find(UserFormation.class, idUserFormation));
			return 1;
		}catch(Exception e) {
			return 0;	
		}
	}

	@Override
	public UserFormation getUserFormation(long i) {
		// TODO Auto-generated method stub
		UserFormation abs = em.find(UserFormation.class, i);
		return abs;
	}

	@Override
	public List<UserFormation> getAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from UserFormation",UserFormation.class)
				.getResultList();
	}

	@Override
	public UserFormation updateUserFormation(UserFormation a) {
		// TODO Auto-generated method stub
		return em.merge(a);
	}

}
