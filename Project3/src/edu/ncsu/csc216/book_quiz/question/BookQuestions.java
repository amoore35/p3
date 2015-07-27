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
	
	/** Used for spacing */
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
	 * Handles the advanced state of questions
	 * @author AlexiaMoore
	 *
	 */
	public class AdvancedQuestionState extends QuestionState {
		
		/**
		 * Sets the current state and constructs an ArrayList of the advanced questions
		 * @param advQuestions the list of advanced questions
		 */
		public AdvancedQuestionState(List<AdvancedQuestion> advQuestions){
			super(new ArrayList<Question>(advQuestions));
			state = advState;
		}
		
		/**
		 * Handles the response given from processing an answer while in the advanced state
		 * @param answer the answer to process
		 * @return correct with a comment or incorrect depending on the answer given
		 * @throws EmptyQuestionListException if there are no more questions to process
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
	 * Handles the elementary state of questions.
	 * @author AlexiaMoore
	 *
	 */
	public class ElementaryQuestionState extends QuestionState {
		
		/** Keeps track of the number of attempts for a specific question */
		private int attempts;
		
		/** Keeps track of the number of correct answers in a row on the first try */
		private int numCorrectInRow;
		
		/**
		 * Sets the current state to the elementary state and creates an ArrayList of the questions
		 * @param elemQuestions the list of elementary questions
		 */
		public ElementaryQuestionState(List<ElementaryQuestion> elemQuestions){
			super(new ArrayList<Question>(elemQuestions));
			state = elemState;
			attempts = 0;
			numCorrectInRow = 0;
		}
		
		/**
		 * Handles the response given from processing an answer while in the elementary state.
		 * Transitions to the standard state if two questions are answered correctly in a row
		 * @param answer the answer to process
		 * @return correct or incorrect and a hint on the first try
		 * @throws EmptyQuestionListException if there are no more questions to process
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
	 * Handles the standard state of questions
	 * @author AlexiaMoore
	 *
	 */
	public class StandardQuestionState extends QuestionState {
		
		/** Keeps track of the number of questions answered correctly in a row */
		private int numCorrectInRow;
		
		/**
		 * Sets the current state to standard and creates an ArrayList of standard questions
		 * @param stdQuestions the list of standard questions
		 */
		public StandardQuestionState(List<StandardQuestion> stdQuestions){
			super(new ArrayList<Question>(stdQuestions));
			state = stdState;
			numCorrectInRow = 0;
		}
		
		/**
		 * Handles the response given from answering a question while in the standard state.
		 * Transitions to the advanced state if two questions are answered correctly in a row,
		 * and to the elementary state if one question is answered incorrectly.
		 * @param answer the answer to process
		 * @return correct or incorrect
		 * @throws EmptyQuestionListException if there are no more questions to process 
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
	 * Constructs the BookQuestions object with three lists and sets the internal states
	 * @param standard the list of standard questions
	 * @param elem the list of elementary questions
	 * @param advanced the list of advanced questions
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
		try{
			state.getCurrentQuestionText();
		} catch (EmptyQuestionListException e){
			throw new EmptyQuestionListException(e.getMessage());
		}
		return state.getCurrentQuestionText();
	}
	
	/**
	 * Returns the question choices of the current question
	 * @return an array of strings that contain the current question choices
	 * @throws EmptyQuestionListException if the list is empty
	 */
	public String[] getCurrentQuestionChoices() throws EmptyQuestionListException{
		try{
			state.getCurrentQuestion();
		} catch (EmptyQuestionListException e){
			throw new EmptyQuestionListException();
		}
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
		try{
			state.getCurrentQuestionAnswer();
		} catch (EmptyQuestionListException e){
			throw new EmptyQuestionListException("No more questions to ask.");
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
