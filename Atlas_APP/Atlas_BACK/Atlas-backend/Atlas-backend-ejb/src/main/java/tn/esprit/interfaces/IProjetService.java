package tn.esprit.interfaces;

import javax.ejb.Remote;

import tn.esprit.entity.Projet;

@Remote
public interface IProjetService extends IBaseService<Projet> {

}
