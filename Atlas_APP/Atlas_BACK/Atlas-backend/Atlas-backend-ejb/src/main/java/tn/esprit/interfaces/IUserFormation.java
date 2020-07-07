package tn.esprit.interfaces;

import javax.ejb.Remote;

import tn.esprit.entity.UserFormation;

@Remote
public interface IUserFormation extends IDoubleBaseService<UserFormation> {

}
