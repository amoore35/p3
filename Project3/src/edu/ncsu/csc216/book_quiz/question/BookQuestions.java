/**
 * 
 */
package edu.ncsu.csc216.book_quiz.question;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.book_quiz.util.EmptyQuestionListException;
import edu.ncsu.csc216.question_library.AdvancedQuestion;
import edu.ncsu.csc216.question_library.ElementaryQuestion;
import edu.ncsu.csc216.question_library.Question;
import edu.ncsu.csc216.question_library.StandardQuestion;

/**
 * Implements a Finite State Machine for question sequencing, additions, and transitions.
 * [SEE UC4 AND UC7]
 * @author AlexiaMoore
 *
 */
public class BookQuestions {
	
	/** Keeps track of the number of questions answered correctly */
	private int numCorrectAnswers;
	
	/** Keeps track of the number of questions attempted */
	private int numAttemptQuests;
	
	/** A String constant to represent what is displayed when an answer is correct */
	public static final String CORRECT = "Correct!";
	
	/** A String constant to represent what is displayed when an answer is incorrect */
	public static final String INCORRECT = "Incorrect!";
	
	/** */
	public static final String SEPARATOR = "";
	
	/** Represents the elementary question state */
	private QuestionState elemState;
	
	/** Represents the standard question state */
	private QuestionState stdState;
	
	/** Represents the advanced question state */
	private QuestionState advState;
	
	/** Keeps track of the current state */
	private QuestionState state;
	
	/**
	 * 
	 * @author AlexiaMoore
	 *
	 */
	public class AdvancedQuestionState extends QuestionState{
		
		/**
		 * 
		 * @param questions
		 */
		public AdvancedQuestionState(List<AdvancedQuestion> advQuestions){
			super(new ArrayList<Question>(advQuestions));
			
		}
		
		/**
		 * 
		 * @param answer
		 * @return
		 */
		public String processAnswer(String answer){
			return "";
		}
		
		
	}
	
	/**
	 * 
	 * @author AlexiaMoore
	 *
	 */
	public class ElementaryQuestionState extends QuestionState{
		
		/** */
		private int attempts;
		
		/** */
		private int numCorrectInRow;
		
		/**
		 * 
		 * @param questions
		 */
		public ElementaryQuestionState(List<ElementaryQuestion> elemQuestions){
			super(new ArrayList<Question>(elemQuestions));
		}
		
		/**
		 * 
		 * @param answer
		 * @return
		 */
		public String processAnswer(String answer){
			return "";
		}
		
	}
	
	/**
	 * 
	 * @author AlexiaMoore
	 *
	 */
	public class StandardQuestionState extends QuestionState{
		
		/** */
		private int numCorrectInRow;
		
		/**
		 * 
		 * @param questions
		 */
		public StandardQuestionState(List<StandardQuestion> stdQuestions){
			super(new ArrayList<Question>(stdQuestions));
		}
		
		/**
		 * 
		 * @param answer
		 * @return
		 */
		public String processAnswer(String answer){
			return "";
		}
		
	}
	
	
	/**
	 * 
	 * @param standard
	 * @param elem
	 * @param advanced
	 */
	public BookQuestions(List<StandardQuestion> standard, List<ElementaryQuestion> elem,
			List<AdvancedQuestion> advanced){
		
		this.elemState = new ElementaryQuestionState(elem);
		this.stdState = new StandardQuestionState(standard);
		this.advState = new AdvancedQuestionState(advanced);
		this.state = this.stdState;
		
	}
	
	/**
	 * True if there are any more questions remaining in the quiz, false otherwise.
	 * @return true if more questions remaining in quiz, false otherwise
	 */
	public boolean hasMoreQuestions(){
		return state.hasMoreQuestions();
	}
	
	/**
	 * Returns the text of the current question
	 * @return the string of the current question
	 * @throws EmptyQuestionListException if the list is empty
	 */
	public String getCurrentQuestionText() throws EmptyQuestionListException{
		return state.getCurrentQuestionText();
	}
	
	/**
	 * Returns the question choices of the current question
	 * @return an array of strings that contain the current question choices
	 * @throws EmptyQuestionListException if the list is empty
	 */
	public String[] getCurrentQuestionChoices() throws EmptyQuestionListException{
		return state.getCurrentQuestionChoices();
		
	}
	
	/**
	 * Processes the answer submitted by the user. BookQuestions relies on its current state
	 * to handle the processing. This method may throw an EmptyQuestionListException if the
	 * answer cannot be processed.
	 * @param answer the answer to be processed
	 * @return the string that should be returned based on the given answer
	 * @throws EmptyQuestionListException if the answer cannot be processed
	 */
	public String processAnswer(String answer) throws EmptyQuestionListException{
		return state.processAnswer(answer);
	}
	
	/**
	 * Returns the number of questions answered correctly in the book quiz
	 * @return numCorrectAnswers the number of questions answered correctly
	 */
	public int getNumCorrectQuestions(){
		return numCorrectAnswers;
	}
	
	/**
	 * Returns the number of questions attempted in the book quiz
	 * @return numAttemptedQuests the number of attempted questions
	 */
	public int getNumAttemptedQuestions(){
		return numAttemptQuests;
	}
	
	/**
	 * Adds a standard question to the list of questions
	 * @param question the question to add
	 */
	public void addStandardQuestion(StandardQuestion question){
		stdState.addQuestion(question);
	}
	
	/**
	 * Adds an elementary question to the list of questions
	 * @param question the question to add
	 */
	public void addElementaryQuestion(ElementaryQuestion question){
		elemState.addQuestion(question);
	}
	
	/**
	 * Adds an advanced question to the list of questions
	 * @param question the question to add
	 */
	public void addAdvancedQuestion(AdvancedQuestion question){
		advState.addQuestion(question);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Question> getStandardQuestions(){
		return stdState.getQuestions();
		
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Question> getElementaryQuestions(){
		return elemState.getQuestions();
		
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Question> getAdvancedQuestions(){
		return advState.getQuestions();
		
	}

}
