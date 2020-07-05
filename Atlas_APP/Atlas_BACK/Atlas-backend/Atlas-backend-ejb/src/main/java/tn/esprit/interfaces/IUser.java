package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.User;



@Remote
public interface IUser {
	
	public int AddUser  (User a);
	public int RemoveUser  (int idUser);
	public User getUser (int i);
	public List<User> getAll();
	public User updateUser(User a);

}
