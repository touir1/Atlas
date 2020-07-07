package tn.esprit.interfaces;

import javax.ejb.Remote;

import tn.esprit.entity.Permission;

@Remote
public interface IPermissionService extends IBaseService<Permission> {
}
