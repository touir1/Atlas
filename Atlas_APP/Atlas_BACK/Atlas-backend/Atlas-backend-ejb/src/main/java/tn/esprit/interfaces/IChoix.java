package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Choix;


@Remote
public interface IChoix {
	public int AddChoix(Choix a);
	public int RemoveChoix(int idChoix);
	public Choix getChoix(int i);
	public List<Choix> getAll();
	public Choix updateChoix(Choix a);


}
