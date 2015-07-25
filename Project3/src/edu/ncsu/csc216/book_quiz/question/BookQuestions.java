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
	public static final String SEPARATOR = " ";
	
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
			state = advState;
		}
		
		/**
		 * 
		 * @param answer
		 * @return
		 * @throws EmptyQuestionListException 
		 */
		public String processAnswer(String answer) throws EmptyQuestionListException{
			if (advState.getCurrentQuestionAnswer().equals(answer)){
				numAttemptQuests++;
				numCorrectAnswers++;
				String comment = ((AdvancedQuestion) (advState.getCurrentQuestion())).getComment();
				nextQuestion();
				return CORRECT + SEPARATOR + comment;
			}
			else{
				numAttemptQuests++;
				state = stdState;
				nextQuestion();
				return INCORRECT;
			}
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
			state = elemState;
			attempts = 0;
			numCorrectInRow = 0;
		}
		
		/**
		 * 
		 * @param answer
		 * @return
		 * @throws EmptyQuestionListException 
		 */
		public String processAnswer(String answer) throws EmptyQuestionListException{
			if (elemState.getCurrentQuestionAnswer().equals(answer)){
				numCorrectAnswers++;
				if (attempts == 0){
					numCorrectInRow++;
					numAttemptQuests++;
				}
				if (numCorrectInRow == 1 || numCorrectInRow == 0){
					attempts = 0;
					nextQuestion();
					return CORRECT;
				}
				if (numCorrectInRow == 2){
					state = stdState;
					//Reset local variables
					numCorrectInRow = 0;
					attempts = 0;
					nextQuestion();
					return CORRECT;
				}
			}
			else{
				numCorrectInRow = 0;
				attempts++;
				if (attempts == 1){
					numAttemptQuests++;
					String hint = ((ElementaryQuestion) (state.getCurrentQuestion())).getHint();
					return INCORRECT + SEPARATOR + hint;
				}
				else{
					attempts = 0;
					nextQuestion();
					return INCORRECT;
				}
			}
			throw new EmptyQuestionListException();
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
			state = stdState;
			numCorrectInRow = 0;
		}
		
		/**
		 * 
		 * @param answer
		 * @return
		 * @throws EmptyQuestionListException 
		 */
		public String processAnswer(String answer) throws EmptyQuestionListException{
			if (state.getCurrentQuestionAnswer().equals(answer)){
				numCorrectAnswers++;
				numAttemptQuests++;
				numCorrectInRow++;
				if (numCorrectInRow < 2){	
					nextQuestion();
					return CORRECT;
				}
				else{
					//Reset numCorrectInRow in case the state returns to standard
					numCorrectInRow = 0;
					state = advState;
					nextQuestion();
					return CORRECT;
				}
			}
			else{
				numAttemptQuests++;
				numCorrectInRow = 0;
				state = elemState;
				nextQuestion();
				return INCORRECT;
			}
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
		if (!state.hasMoreQuestions()){
			throw new EmptyQuestionListException();
		}
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
	 * Returns a list of standard questions
	 * @return a list of standard questions
	 */
	public List<Question> getStandardQuestions(){
		return stdState.getQuestions();
		
	}
	
	/**
	 * Returns a list of elementary questions
	 * @return a list of elementary questions
	 */
	public List<Question> getElementaryQuestions(){
		return elemState.getQuestions();
		
	}
	
	/**
	 * Returns a list of advanced questions
	 * @return a list of advanced questions
	 */
	public List<Question> getAdvancedQuestions(){
		return advState.getQuestions();
		
	}

}
