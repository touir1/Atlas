package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Rapport;

@Remote
public interface IRapportService extends IDoubleBaseService<Rapport> {
	public Boolean validerRapport(long idUser, long idRubrique);

}
