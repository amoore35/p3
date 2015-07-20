/**
 * 
 */
package edu.ncsu.csc216.book_quiz.question;

import java.util.ArrayList;
import java.util.List;

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
		
	}
	
	/**
	 * True if there are any more questions remaining in the quiz.
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
	 * Processes the answer submitted by the user. BookQuestions relies on its current state
	 * to handle the processing. This method may throw an EmptyQuestionListException if the
	 * answer cannot be processed.
	 * @param string
	 * @return
	 * @throws EmptyQuestionListException
	 */
	public String processAnswer(String answer){
		return "";
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumCorrectQuestions(){
		return 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumAttemptedQuestions(){
		return 0;
	}
	
	/**
	 * 
	 * @param question
	 */
	public void addStandardQuestion(StandardQuestion question){
		
	}
	
	/**
	 * 
	 * @param question
	 */
	public void addElementaryQuestion(ElementaryQuestion question){
		
	}
	
	/**
	 * 
	 * @param question
	 */
	public void addAdvancedQuestion(AdvancedQuestion question){
		
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Question> getStandardQuestions(){
		return null;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Question> getElementaryQuestions(){
		return null;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Question> getAdvancedQuestions(){
		return null;
		
	}

}
