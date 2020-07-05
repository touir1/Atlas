package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Frais;



@Remote
public interface IFraisService {
	
	public int addFrais(Frais a);
	public int removeFrais(long idFrais);
	public Frais getFrais(long id);
	public List<Frais> getAll();
	public Frais updateFrais(Frais a);

}
