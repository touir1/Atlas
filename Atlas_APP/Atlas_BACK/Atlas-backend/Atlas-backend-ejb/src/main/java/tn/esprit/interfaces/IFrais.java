package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Frais;



@Remote
public interface IFrais {
	
	public int AddFrais(Frais a);
	public int RemoveFrais(int idFrais);
	public Frais getFrais(int i);
	public List<Frais> getAll();

}
