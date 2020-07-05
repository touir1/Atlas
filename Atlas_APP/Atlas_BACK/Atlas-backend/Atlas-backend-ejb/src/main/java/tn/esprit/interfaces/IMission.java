package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Mission;


@Remote
public interface IMission {

	
	public int AddMission(Mission a);
	public int RemoveMission(int idMission);
	public Mission getMission(int i);
	public List<Mission> getAll();
	public Mission updateMission(Mission a);
}
