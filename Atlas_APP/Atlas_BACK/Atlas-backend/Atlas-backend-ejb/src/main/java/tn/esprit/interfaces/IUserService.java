package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.User;

@Remote
public interface IUserService extends IBaseService<User> {
	public List<User> getUsersByManager(long idManager);
}
