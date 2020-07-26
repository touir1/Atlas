package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Rubrique;

@Remote
public interface IRubriqueService extends IBaseService<Rubrique> {
	public List<Rubrique> getRubriqueByProjet(long idProjet);

}
