package tn.esprit.interfaces;


import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Permission;



@Remote
public interface IPermissionService {
	
	public int addPermission(Permission a);
	public int removePermission(long idPermission);
	public Permission getPermission(long i);
	public List<Permission> getAll();
	public Permission updatePermission(Permission a);
}
