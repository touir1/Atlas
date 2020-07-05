package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Choix;


@Remote
public interface IChoixService {
	public int addChoix(Choix a);
	public int removeChoix(long idChoix);
	public Choix getChoix(long id);
	public List<Choix> getAll();
	public Choix updateChoix(Choix a);


}
