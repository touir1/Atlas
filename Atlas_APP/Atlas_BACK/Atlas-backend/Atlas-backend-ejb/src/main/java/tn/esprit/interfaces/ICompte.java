package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Compte;

@Remote
public interface ICompte {
	public int AddCompte(Compte a);
	public int RemoveCompte(int idCompte);
	public Compte getCompte(int i);
	public List<Compte> getAll();
	public Compte updateCompte(Compte a);

}
