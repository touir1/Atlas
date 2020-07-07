package tn.esprit.interfaces;

import javax.ejb.Remote;

import tn.esprit.entity.Facturation;

@Remote
public interface IFacturationService extends IBaseService<Facturation> {

}
