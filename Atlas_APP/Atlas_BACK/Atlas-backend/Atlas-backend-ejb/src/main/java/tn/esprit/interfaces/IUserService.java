package tn.esprit.interfaces;

import javax.ejb.Remote;

import tn.esprit.entity.User;

@Remote
public interface IUserService extends IBaseService<User> {

}
