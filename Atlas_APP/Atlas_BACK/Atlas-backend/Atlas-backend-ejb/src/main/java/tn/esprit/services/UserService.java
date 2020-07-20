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

	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	@Override
	public User add(User a) {
		try {
			em.persist(a);
			return a;
		} catch (Exception e) {
			logger.error("error while trying to add a user", e);
			return null;
		}
	}

	@Override
	public boolean remove(User u) {
		try {
			if (u == null)
				return false;
			em.remove(em.find(User.class, u.getId()));
			return true;
		} catch (Exception e) {
			logger.error("error while trying to remove a user", e);
			return false;
		}
	}

	@Override
	public User get(long id) {
		User abs = em.find(User.class, id);
		return abs;
	}

	@Override
	public List<User> getAll() {
		return em.createQuery("from User", User.class).getResultList();
	}

	@Override
	public User update(User a) {
		return em.merge(a);
	}

	@Override
	public List<User> getUsersByManager(long idManager) {
		return em.createQuery("select u from User u where u.chef.id = :idManager", User.class)
				.setParameter("idManager", idManager).getResultList();
	}

}
