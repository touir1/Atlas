package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Question;


@Remote
public interface IQuestion {
	
	public int AddQuestion (Question  a);
	public int RemoveQuestion (int idQuestion);
	public Question  getQuestion(int i);
	public List<Question> getAll();
	public Question  updateQuestion(Question  a);

}
