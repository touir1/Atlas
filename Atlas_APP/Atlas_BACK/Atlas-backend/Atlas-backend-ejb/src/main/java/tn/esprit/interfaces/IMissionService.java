package tn.esprit.interfaces;

import javax.ejb.Remote;

import tn.esprit.entity.Mission;

@Remote
public interface IMissionService extends IBaseService<Mission> {
}
