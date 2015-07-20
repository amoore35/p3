/**
 * 
 */
package edu.ncsu.csc216.book_quiz.question;

import java.util.List;

import edu.ncsu.csc216.book_quiz.util.EmptyQuestionListException;
import edu.ncsu.csc216.question_library.Question;

/**
 * An abstract class that represents state in the FSM. QuestionState is extended by
 * private inner classes nested inside BookQuestions.
 * @author AlexiaMoore
 *
 */
public abstract class QuestionState {
	
	/** Represents the front index of the list, which is 0. */
	private static final int FRONT = 0;
	
	/** 
	 * Contains all questions that are unasked. Any new questions the user creates are 
	 * added to the end of this collection.
	 */
	private List<Question> waitingQuestions;
	
	/** 
	 * A references to the current question that would be asked of the user if the quiz
	 * is in the given state.
	 */
	private Question currentQuestion;
	
	/** 
	 * Contains all questions that have been asked. The last question in this list is
	 * the currentQuestion.
	 */
	private List<Question> askedQuestions;
	
	/**
	 * 
	 * @param questions
	 */
	public QuestionState(List<Question> questions){
		
	}
	
	/**
	 * Abstract method to process the user's answer. Throws an EmptyQuestionListException if
	 * currentQuestion is null. This method corresponds to transitions in the FSM. Each 
	 * concrete state (nested classes inside BookQuestions) defines this method according to
	 * the transitions that go from that state. What should happen in each concrete state is
	 * defined in UC7, S1.
	 * @param answer
	 * @return
	 */
	public abstract String processAnswer(String answer);
	
	/**
	 * True if currentQuestion is not null or waitingQuestions is not empty.
	 * @return
	 */
	public boolean hasMoreQuestions(){
		if (currentQuestion == null){
			return false;
		}
		if (waitingQuestions.isEmpty()){
			return false;
		}
		return true;
		
	}
	
	/**
	 * 
	 * @return
	 * @throws EmptyQuestionListException
	 */
	public String getCurrentQuestionText() throws EmptyQuestionListException{
		if (currentQuestion == null){
			throw new EmptyQuestionListException();
		}
		return "";
	}
	
	/**
	 * 
	 * @return
	 * @throws EmptyQuestionListException if currentQuestion is null
	 */
	public String[] getCurrentQuestionChoices() throws EmptyQuestionListException{
		if (currentQuestion == null){
			throw new EmptyQuestionListException();
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 * @throws EmptyQuestionListException if currentQuestion is null
	 */
	public String getCurrentQuestionAnswer() throws EmptyQuestionListException{
		if (currentQuestion == null){
			throw new EmptyQuestionListException();
		}
		return "";
	}
	
	/**
	 * 
	 * @return
	 * @throws EmptyQuestionListException if currentQuestion is null
	 */
	public Question getCurrentQuestion() throws EmptyQuestionListException{
		if (currentQuestion == null){
			throw new EmptyQuestionListException();
		}
		return null;
	}
	
	/**
	 * Sets currentQuestion to the next item in the waitinQuestions list, or null if there
	 * are no more questions in the list. The currentQuestion is added to the end of the 
	 * askedQuestions list.
	 */
	public void nextQuestion(){
		if (!this.hasMoreQuestions()){
			currentQuestion = null;
		}
		else{
			currentQuestion = waitingQuestions.remove(FRONT);
			askedQuestions.add(currentQuestion);
		}
	}
	
	/**
	 * Adds the given question to the end of the waitingQuestions list. If currentQuestion is
	 * null, implying there are no more questions of that type to ask, nextQuestion() is
	 * executed because there is now a question to ask.
	 * @param question the question to add to the end of the waitingQuestions list
	 */
	public void addQuestion(Question question){
		
	}
	
	/**
	 * Returns a list of questions. The list of questions combines the askedQuestions with the
	 * waitingQuestions. The askedQuestions are added to the joint list first.
	 * @return
	 */
	public List<Question> getQuestions(){
		return null;
		
	}

}
