package tn.esprit.services;

import java.util.List;

import javax.ejb.Stateless;

import tn.esprit.entity.Frais;
import tn.esprit.interfaces.IFrais;

@Stateless
public class FraisService implements IFrais {

	@Override
	public int AddFrais(Frais a) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int RemoveFrais(int idFrais) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Frais getFrais(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Frais> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
