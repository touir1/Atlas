package tn.esprit.interfaces;

import javax.ejb.Remote;

import tn.esprit.entity.Formation;

@Remote
public interface IFormationService extends IBaseService<Formation> {

}
