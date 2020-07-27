package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Rapport;
import tn.esprit.interfaces.IRapportService;

@Stateless
@LocalBean
public class RapportService implements IRapportService {

	@PersistenceContext(unitName="primary")
	EntityManager em;
	@Override
	public Rapport add(Rapport a) {
		try {
			em.persist(a);
			return a;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public boolean remove(Rapport r) {
		try {
			if(r == null || r.getUser() == null || r.getRubrique() == null) return false;
			Rapport toRemove = em.createQuery("select r from Rapport r where r.user.id = :user"
					+ " and r.rubrique.id = :rubrique",Rapport.class)
					.setParameter("user", r.getUser().getId())
					.setParameter("rubrique", r.getRubrique().getId())
					.getSingleResult();
			em.remove(toRemove);
			return true;
		}catch(Exception e) {
			return false;	
		}
	}

	@Override
	public Rapport get(long idUser, long idRubrique) {
		Rapport abs = em.createQuery("select r from Rapport r where r.user.id = :user"
				+ " and r.rubrique.id = :rubrique",Rapport.class)
				.setParameter("user", idUser)
				.setParameter("rubrique", idRubrique)
				.getSingleResult();
		return abs;
	}

	@Override
	public List<Rapport> getAll() {
		return em.createQuery("from Rapport",Rapport.class)
				.getResultList();
	}

	@Override
	public Rapport update(Rapport a) {
		return em.merge(a);
	}

	@Override
	public Boolean validerRapport(long idUser, long idRubrique) {
		// TODO Auto-generated method stub
		Rapport abs = em.createQuery("select r from Rapport r where r.user.id = :user"
				+ " and r.rubrique.id = :rubrique",Rapport.class)
				.setParameter("user", idUser)
				.setParameter("rubrique", idRubrique)
				.getSingleResult();
		abs.setValider(true);
		return true;
	}

	@Override
	public List<Rapport> getRapportByuser(int semaine, int mois, int annee, long idUser) {
		// TODO Auto-generated method stub
		
		List<Rapport> rapports = em.createQuery("select r from Rapport r where r.semaine = :semaine"
				+ " and r.mois = :mois and r.annee = :annee and r.user.id = :user",Rapport.class)
				.setParameter("semaine", semaine)
				.setParameter("mois", mois)
				.setParameter("annee", annee)
				.setParameter("user", idUser)
				.getResultList();
		return rapports;
	}

	@Override
	public void deleteRapport(Rapport rapport) {
		try {
		
			Rapport toRemove = em.createQuery("select r from Rapport r where r.user.id = :user"
					+ " and r.rubrique.id = :rubrique and r.semaine= :s and r.annee = :an",Rapport.class)
					.setParameter("s", rapport.getSemaine())
					.setParameter("an", rapport.getAnnee())
					.setParameter("user", rapport.getUser().getId())
					.setParameter("rubrique", rapport.getRubrique().getId())
					.getSingleResult();
			em.remove(toRemove);
		
		}catch(Exception e) {
		
		
	}
	
	}

	@Override
	public List<Rapport> getAllRapportByUser(long idUser, int semaine) {
		// TODO Auto-generated method stub
		
		List<Rapport> rapports = em.createQuery("select r from Rapport r where r.semaine != :semaine"
				+ " and r.user.id = :user and r.valider = :valid",Rapport.class)
				.setParameter("semaine", semaine)
				.setParameter("valid", true)
				.setParameter("user", idUser)
				.getResultList();
		return rapports;	}
}