package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Absence;
import tn.esprit.entity.Choix;
import tn.esprit.entity.Evaluation;


@Remote
public interface IChoixService  extends IBaseService<Choix> {
	
	public List<Choix> getChoixByQuestion(long idQuestion);

}
