package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Rapport;

@Remote
public interface IRapportService extends IDoubleBaseService<Rapport> {
	public Boolean validerRapport(long idUser, long idRubrique);
	public List<Rapport> getRapportByuser(int semaine,int mois,int annee, long idUser);
	public void deleteRapport(Rapport rapport);
	public List<Rapport>  getAllRapportByUser(long idUser, int semaine);
}
