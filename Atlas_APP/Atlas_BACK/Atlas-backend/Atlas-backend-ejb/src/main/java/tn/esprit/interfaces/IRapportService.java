package tn.esprit.interfaces;

import javax.ejb.Remote;

import tn.esprit.entity.Rapport;

@Remote
public interface IRapportService extends IDoubleBaseService<Rapport> {

}
