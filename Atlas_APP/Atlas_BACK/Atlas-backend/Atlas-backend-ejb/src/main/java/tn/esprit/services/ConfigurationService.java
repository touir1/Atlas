package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Configuration;
import tn.esprit.entity.Role;
import tn.esprit.interfaces.IConfigurationService;

@Stateless
@LocalBean
public class ConfigurationService implements IConfigurationService {

	@PersistenceContext(unitName = "primary")
	EntityManager em;
	
	@Override
	public Configuration add(Configuration entity) {
		try {
			em.persist(entity);
			return entity;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Configuration update(Configuration entity) {
		return em.merge(entity);
	}

	@Override
	public boolean remove(Configuration entity) {
		try {
			if(entity == null) return false;
			em.remove(em.find(Configuration.class, entity.getId()));
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public Configuration get(long id) {
		Configuration config = em.find(Configuration.class, id);
		return config;
	}

	@Override
	public List<Configuration> getAll() {
		return em.createQuery("from Configuration",Configuration.class)
				.getResultList();
	}
	
	@Override
	public Configuration getByKey(String key) {
		return em.createQuery("select c from Configuration c where lower(c.key) = lower(:key)", Configuration.class)
				.setParameter("key", key)
				.getSingleResult();
	}

}
