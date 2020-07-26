package tn.esprit.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Compte;
import tn.esprit.entity.Rapport;
import tn.esprit.entity.Role;
import tn.esprit.interfaces.ICompteService;

@Stateless
@LocalBean
public class CompteService implements ICompteService {

	@PersistenceContext(unitName = "primary")
	EntityManager em;

	@Override
	public Compte add(Compte a) {
		try {
			List<Role> roles = null;
			if(a != null && a.getRoles() != null && a.getRoles().size() > 0) {
				roles = a.getRoles();
				a.setRoles(null);
			}
			em.persist(a);
			if(roles != null) {
				a.setRoles(roles);
				em.merge(a);
				for(Role role : a.getRoles()) {
					if(role.getComptes() == null) {
						role.setComptes(new ArrayList<Compte>());
					}
					role.getComptes().add(a);
				}
				/*em.merge(a);*/
			}
			return a;
		} catch (Exception e) {
			return null;
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
		if(a != null) {
			Compte c = em.find(Compte.class, a.getId());
			if(c.getRoles() != null) {
				for(Role r : c.getRoles()) {
					r.getComptes().remove(c);
				}
				c.getRoles().clear();
			}
			
			if(a.getRoles() != null) {
				if(c.getRoles() ==  null) {
					c.setRoles(new ArrayList<Role>());
				}
				for(Role r : a.getRoles()) {
					Role role = em.find(Role.class, r.getId());
					c.getRoles().add(role);
					if(role.getComptes() == null) {
						role.setComptes(new ArrayList<Compte>());
					}
					role.getComptes().add(c);
				}
			}
			c.setPassword(a.getPassword());
		}
		return a;
	}

	@Override
	public Compte login(String username, String pwd) {
		// TODO Auto-generated method stub
		Compte abs = em
				.createQuery("select c from Compte c where lower(c.username) = lower(:username)" + " and c.password= :password",
						Compte.class)
				.setParameter("username", username).setParameter("password", pwd).getSingleResult();

		return abs;
	}
	
	@Override
	public boolean existsUsername(String username) {
		try {
			Compte check = em
				.createQuery("select c from Compte c where lower(c.username) = lower(:username)",Compte.class)
				.setParameter("username", username)
				.getSingleResult();
			if(check == null) return false;
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

}
