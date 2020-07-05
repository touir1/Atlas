package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Absence;

@Remote
public interface IAbsence {

	public int AddAbsence(Absence a);
	public int RemoveAbsence(int idAbsence);
	public Absence getAbsence(int i);
	public List<Absence> getAll();
	public Absence updateAbsence(Absence a);
}