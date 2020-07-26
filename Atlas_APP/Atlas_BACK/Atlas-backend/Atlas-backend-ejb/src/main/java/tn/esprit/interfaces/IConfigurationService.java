package tn.esprit.interfaces;

import javax.ejb.Remote;

import tn.esprit.entity.Configuration;

@Remote
public interface IConfigurationService extends IBaseService<Configuration> {
	public Configuration getByKey(String key);
}
