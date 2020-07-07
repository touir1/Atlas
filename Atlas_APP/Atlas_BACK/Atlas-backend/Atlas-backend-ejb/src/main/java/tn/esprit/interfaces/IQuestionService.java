package tn.esprit.interfaces;

import javax.ejb.Remote;

import tn.esprit.entity.Question;

@Remote
public interface IQuestionService extends IBaseService<Question> {

}
