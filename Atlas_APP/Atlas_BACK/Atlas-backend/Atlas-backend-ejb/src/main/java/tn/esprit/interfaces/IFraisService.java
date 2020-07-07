package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Compte;
import tn.esprit.entity.Frais;



@Remote
public interface IFraisService extends IBaseService<Frais> {

}
