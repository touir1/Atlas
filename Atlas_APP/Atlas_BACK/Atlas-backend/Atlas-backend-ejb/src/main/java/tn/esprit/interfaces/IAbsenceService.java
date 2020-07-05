package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Absence;

@Remote
public interface IAbsenceService {

	public int addAbsence(Absence a);
	public int removeAbsence(long idAbsence);
	public Absence getAbsence(long i);
	public List<Absence> getAll();
	public Absence updateAbsence(Absence a);
}