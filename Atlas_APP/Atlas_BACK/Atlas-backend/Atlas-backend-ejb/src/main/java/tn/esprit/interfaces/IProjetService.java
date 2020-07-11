package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Projet;
import tn.esprit.entity.User;

@Remote
public interface IProjetService extends IBaseService<Projet> {
	public List<Projet> getProjectByManager(long idManager);
	public List<User> getMembreByProject(long idProject);
	public Boolean affecterUserToProject(long idProject, long idUser);

}
