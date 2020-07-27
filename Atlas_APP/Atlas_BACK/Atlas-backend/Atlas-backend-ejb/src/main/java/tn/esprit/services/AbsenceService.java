package tn.esprit.services;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entity.Absence;
import tn.esprit.entity.User;
import tn.esprit.interfaces.IAbsenceService;

@Stateless
@LocalBean
public class AbsenceService  implements IAbsenceService{
	
	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Override
	public Absence add(Absence a) {
		try {
			em.persist(a);
			return a;
		} catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public boolean remove(Absence a) {
		try {
			if(a == null) return false;
			em.remove(em.find(Absence.class, a.getId()));
			return true;
		}catch(Exception e) {
			return false;	
		}
		
	}

	@Override
	public Absence get(long id) {
		Absence abs = em.find(Absence.class, id);
		return abs;
	}

	@Override
	public List<Absence> getAll() {
		return em.createQuery("from Absence",Absence.class)
				.getResultList();
	}

	@Override
	public Absence update(Absence a) {
		return em.merge(a);
	}

	@Override
	public List<Absence> getListByStatusForManager(String status, long idManager) {
		return em.createQuery("select a from Absence a where a.status = :status"
				+ " and a.user.chef.id = :manager",Absence.class)
				.setParameter("status", status)
				.setParameter("manager", idManager)
				.getResultList();
	}

	@Override
	public List<Absence> getListByStatusForUser(String status, long idUser) {
		return em.createQuery("select a from Absence a where a.status = :status"
				+ " and a.user.id = :user",Absence.class)
				.setParameter("status", status)
				.setParameter("user", idUser)
				.getResultList();
	}
	
	@Override
	public List<Absence> getListByStatus(String status) {
		return em.createQuery("select a from Absence a where a.status = :status",Absence.class)
				.setParameter("status", status)
				.getResultList();
	}
	
	@Override
	public List<Absence> getListForUser(long idUser){
		return em.createQuery("select a from Absence a where a.user.id = :user",Absence.class)
				.setParameter("user", idUser)
				.getResultList();
	}
	
	@Override
	public float getSoldeCongee(long idUser) {
		Calendar dateDebutContrat = new GregorianCalendar();
		dateDebutContrat.setTime(em.find(User.class, idUser).getDateContrat());
		Calendar today = new GregorianCalendar();
		
		int yearsInBetween = today.get(Calendar.YEAR)
				- dateDebutContrat.get(Calendar.YEAR);
		int monthsDiff = today.get(Calendar.MONTH)
				- dateDebutContrat.get(Calendar.MONTH);
		long ageContratInMonths = yearsInBetween * 12 + monthsDiff;
		
		long soldeCongeTotal = ageContratInMonths * 2;
		List<Absence> congeeList = em.createQuery("from Absence a where lower(a.type) = lower(:type)"
				+ " and lower(a.status) != lower(:refus) and a.user.id = :user" , Absence.class)
				.setParameter("type", "Congé")
				.setParameter("refus", "Refusé")
				.setParameter("user", idUser)
				.getResultList();
		float resultat = (float) soldeCongeTotal;
		if(congeeList != null) {
			for(int i=0;i<congeeList.size();i++) {
				resultat -= (congeeList.get(i).getHeures() / 8);
			}
		}
		return resultat;
	}
	
	@Override
	public float getSoldeCongeeTotale(long idUser) {
		Calendar dateDebutContrat = new GregorianCalendar();
		dateDebutContrat.setTime(em.find(User.class, idUser).getDateContrat());
		Calendar today = new GregorianCalendar();
		
		int yearsInBetween = today.get(Calendar.YEAR)
				- dateDebutContrat.get(Calendar.YEAR);
		int monthsDiff = today.get(Calendar.MONTH)
				- dateDebutContrat.get(Calendar.MONTH);
		long ageContratInMonths = yearsInBetween * 12 + monthsDiff;
		
		long soldeCongeTotal = ageContratInMonths * 2;
		List<Absence> congeeList = em.createQuery("from Absence a where lower(a.type) = lower(:type)"
				+ " and lower(a.status) != lower(:refus) and lower(a.status) != lower(:encours)"
				+ " and a.user.id = :user" , Absence.class)
				.setParameter("type", "Congé")
				.setParameter("refus", "Refusé")
				.setParameter("encours", "A valider")
				.setParameter("user", idUser)
				.getResultList();
		float resultat = (float) soldeCongeTotal;
		if(congeeList != null) {
			for(int i=0;i<congeeList.size();i++) {
				resultat -= (congeeList.get(i).getHeures() / 8);
			}
		}
		return resultat;
	}

}
