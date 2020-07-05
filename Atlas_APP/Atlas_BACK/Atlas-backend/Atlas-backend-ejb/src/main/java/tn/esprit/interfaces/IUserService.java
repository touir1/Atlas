package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.User;



@Remote
public interface IUserService {
	
	public boolean addUser  (User a);
	public boolean removeUser  (long idUser);
	public User getUser (long i);
	public List<User> getAll();
	public User updateUser(User a);

}
