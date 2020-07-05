package tn.esprit.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.User;
import tn.esprit.interfaces.IUser;

public class UserService implements IUser {
	
	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Override
	public int AddUser(User a) {
		// TODO Auto-generated method stub
		try {
			em.persist(a);
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int RemoveUser(int idUser) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.find(User.class, idUser));
			return 1;
		}catch(Exception e) {
			return 0;	
		}
	}

	@Override
	public User getUser(int i) {
		// TODO Auto-generated method stub
		User abs = em.find(User.class, i);
		return abs;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from User",User.class)
				.getResultList();
	}

	@Override
	public User updateUser(User a) {
		// TODO Auto-generated method stub
		return em.merge(a);
	}

}
