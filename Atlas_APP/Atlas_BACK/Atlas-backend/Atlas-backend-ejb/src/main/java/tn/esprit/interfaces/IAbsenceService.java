package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Absence;

@Remote
public interface IAbsenceService extends IBaseService<Absence> {
	public List<Absence> getListByStatusForManager(String status, long idManager);
	public List<Absence> getListByStatusForUser(String status, long idUser);
}