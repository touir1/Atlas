package tn.esprit.interfaces;

import javax.ejb.Remote;

import tn.esprit.entity.Compte;

@Remote
public interface ICompteService extends IBaseService<Compte> {

}
