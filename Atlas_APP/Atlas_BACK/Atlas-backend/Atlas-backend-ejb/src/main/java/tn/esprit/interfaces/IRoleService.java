package tn.esprit.interfaces;

import javax.ejb.Remote;

import tn.esprit.entity.Role;

@Remote
public interface IRoleService extends IBaseService<Role> {
	public boolean existsRole(String libelle);
}
