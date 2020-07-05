package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Mission;


@Remote
public interface IMissionService {

	
	public int addMission(Mission a);
	public int removeMission(long idMission);
	public Mission getMission(long i);
	public List<Mission> getAll();
	public Mission updateMission(Mission a);
}
