package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Absence;
import tn.esprit.entity.Projet;
import tn.esprit.entity.User;
import tn.esprit.interfaces.IProjetService;

@Stateless
@LocalBean
public class ProjetService implements IProjetService {

	@PersistenceContext(unitName="primary")
	EntityManager em;
	@Override
	public boolean add(Projet a) {
		try {
			em.persist(a);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean remove(Projet p) {
		try {
			if(p == null) return false;
			em.remove(em.find(Projet.class, p.getId()));
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public Projet get(long id) {
		Projet abs = em.find(Projet.class, id);
		return abs;
	}

	@Override
	public List<Projet> getAll() {
		return em.createQuery("from Projet",Projet.class)
				.getResultList();
	}

	@Override
	public Projet update(Projet a) {
		return em.merge(a);
	}

	@Override
	public List<Projet> getProjectByManager(long idManager) {
		// TODO Auto-generated method stub
		return em.createQuery("select p from Projet p where p.createdBy.id = :manager",Projet.class)
				.setParameter("manager", idManager)
				.getResultList();
	}

	@Override
	public List<User> getMembreByProject(long idProject) {
		// TODO Auto-generated method stub
		return em.createQuery("select m from Projet p join p.membres m where p.id = :projet",User.class)
				.setParameter("projet", idProject)
				.getResultList();
	}

	@Override
	public Boolean affecterUserToProject(long idProject, long idUser) {
		// TODO Auto-generated method stub
		Projet p = em.find(Projet.class, idProject);
		User u = em.find(User.class, idUser);
		p.getMembres().add(u);
		return true;
	}

}
