package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Role;



@Remote
public interface IRoleService {
	
	
	public int addRole  (Role a);
	public int removeRole  (long idRole);
	public Role  getRole (long i);
	public List<Role> getAll();
	public Role updateRole(Role a);

}
