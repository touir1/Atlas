package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Question;

@Remote
public interface IQuestionService extends IBaseService<Question> {
	public List<Question> getQuestionBySujet(long idSujet);

}
