package tn.esprit.interfaces;


import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Permission;



@Remote
public interface IPermission {
	
	public int AddPermission(Permission a);
	public int RemovePermission(int idPermission);
	public Permission getPermission(int i);
	public List<Permission> getAll();
	public Permission updatePermission(Permission a);
}
