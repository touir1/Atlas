package tn.esprit.interfaces;

import javax.ejb.Remote;

import tn.esprit.entity.Absence;

@Remote
public interface IAbsenceService extends IBaseService<Absence> {

}