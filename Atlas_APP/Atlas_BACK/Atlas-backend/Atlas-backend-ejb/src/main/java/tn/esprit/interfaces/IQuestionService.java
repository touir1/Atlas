package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Question;


@Remote
public interface IQuestionService {
	
	public int addQuestion (Question  a);
	public int removeQuestion (long idQuestion);
	public Question  getQuestion(long i);
	public List<Question> getAll();
	public Question  updateQuestion(Question  a);

}
