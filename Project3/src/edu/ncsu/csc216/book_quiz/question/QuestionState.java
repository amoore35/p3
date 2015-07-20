/**
 * 
 */
package edu.ncsu.csc216.book_quiz.question;

import java.util.List;

import edu.ncsu.csc216.question_library.Question;

/**
 * An abstract class that represents state in the FSM. QuestionState is extended by
 * private inner classes nested inside BookQuestions.
 * @author AlexiaMoore
 *
 */
public abstract class QuestionState {
	
	/** */
	private static final int FRONT = 0;
	
	/** */
	private List<Question> waitingQuestions;
	
	/** */
	private Question currentQuestion;
	
	/** */
	private List<Question> askedQuestions;
	
	/**
	 * 
	 * @param questions
	 */
	public QuestionState(List<Question> questions){
		
	}
	
	/**
	 * 
	 * @param answer
	 * @return
	 */
	public abstract String processAnswer(String answer);
	
	/**
	 * 
	 * @return
	 */
	public boolean hasMoreQuestions(){
		return false;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCurrentQuestionText(){
		return "";
	}
	
	/**
	 * 
	 * @return
	 */
	public String[] getCurrentQuestionChoices(){
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCurrentQuestionAnswer(){
		return "";
	}
	
	/**
	 * 
	 * @return
	 */
	public Question getCurrentQuestion(){
		return null;
	}
	
	/**
	 * 
	 */
	public void nextQuestion(){
		
	}
	
	/**
	 * 
	 * @param question
	 */
	public void addQuestion(Question question){
		
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Question> getQuestions(){
		return null;
		
	}

}
