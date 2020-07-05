package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;


import tn.esprit.entity.UserFormation;

@Remote
public interface IUserFormation {
	
	public int AddUserFormation  (UserFormation a);
	public int RemoveUserFormation  (int idUserFormation);
	public UserFormation getUserFormation (int i);
	public List<UserFormation> getAll();
	public UserFormation updateUserFormation(UserFormation a);

}
