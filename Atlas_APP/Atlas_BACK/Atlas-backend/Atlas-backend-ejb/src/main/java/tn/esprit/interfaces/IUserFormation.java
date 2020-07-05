package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;


import tn.esprit.entity.UserFormation;

@Remote
public interface IUserFormation {
	
	public int addUserFormation  (UserFormation a);
	public int removeUserFormation  (long idUserFormation);
	public UserFormation getUserFormation (long i);
	public List<UserFormation> getAll();
	public UserFormation updateUserFormation(UserFormation a);

}
