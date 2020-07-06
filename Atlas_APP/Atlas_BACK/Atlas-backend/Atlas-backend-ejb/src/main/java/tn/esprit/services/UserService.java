package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import tn.esprit.entity.User;
import tn.esprit.interfaces.IUserService;

@Stateless
@LocalBean
public class UserService implements IUserService {
	
	private final static Logger logger = Logger.getLogger(UserService.class);
	
	@PersistenceContext(unitName="primary")
	private EntityManager em;

	@Override
	public boolean addUser(User a) {
		// TODO Auto-generated method stub
		try {
			em.persist(a);
			return true;
		} catch(Exception e) {
			logger.error("error while trying to add a user",e);
			return false;
		}
	}

	@Override
	public boolean removeUser(long idUser) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.find(User.class, idUser));
			return true;
		}catch(Exception e) {
			logger.error("error while trying to remove a user",e);
			return false;	
		}
	}

	@Override
	public User getUser(long i) {
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
