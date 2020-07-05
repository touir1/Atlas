package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Compte;

@Remote
public interface ICompteService {
	public int addCompte(Compte a);
	public int removeCompte(long idCompte);
	public Compte getCompte(long id);
	public List<Compte> getAll();
	public Compte updateCompte(Compte a);

}
