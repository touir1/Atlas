package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Absence;
import tn.esprit.entity.Choix;


@Remote
public interface IChoixService  extends IBaseService<Choix> {

}
