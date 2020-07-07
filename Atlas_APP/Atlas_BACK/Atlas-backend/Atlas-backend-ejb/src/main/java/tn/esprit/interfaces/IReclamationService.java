package tn.esprit.interfaces;

import javax.ejb.Remote;

import tn.esprit.entity.Reclamation;

@Remote
public interface IReclamationService extends IBaseService<Reclamation> {

}
