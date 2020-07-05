package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Facturation;


@Remote
public interface IFacturationService {
	public int addFacturation(Facturation a);
	public int removeFacturation(long idFacturation);
	public Facturation getFacturation(long id);
	public List<Facturation> getAll();
	public Facturation updateFacturation(Facturation a);

}
