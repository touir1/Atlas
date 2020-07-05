package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Facturation;


@Remote
public interface IFacturation {
	public int AddFacturation(Facturation a);
	public int RemoveFacturation(int idFacturation);
	public Facturation getFacturation(int i);
	public List<Facturation> getAll();
	public Facturation updateFacturation(Facturation a);

}
