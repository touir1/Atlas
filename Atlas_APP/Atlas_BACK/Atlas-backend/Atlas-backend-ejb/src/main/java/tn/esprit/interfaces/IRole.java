package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Role;



@Remote
public interface IRole {
	
	
	public int AddRole  (Role a);
	public int RemoveRole  (int idRole);
	public Role  getRole (int i);
	public List<Role> getAll();

}
