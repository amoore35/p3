/**
 * 
 */
package edu.ncsu.csc216.book_quiz.question;

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
	
	/** */
	private int numCorrectAnswers;
	
	/** */
	private int numAttemptQuests;
	
	/** */
	public static final String CORRECT = "";
	
	/** */
	public static final String INCORRECT = "";
	
	/** */
	public static final String SEPARATOR = "";
	
	/** */
	private QuestionState elemState;
	
	/** */
	private QuestionState stdState;
	
	/** */
	private QuestionState advState;
	
	/** */
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
		public AdvancedQuestionState(List<AdvancedQuestion> questions){
			
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
		public ElementaryQuestionState(List<ElementaryQuestion> questions){
			
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
		public StandardQuestionState(List<StandardQuestion> questions){
			
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
	 * @param string
	 * @return
	 */
	public String processAnswer(String string){
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
