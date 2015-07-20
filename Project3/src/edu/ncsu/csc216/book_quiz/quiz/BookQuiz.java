/**
 * 
 */
package edu.ncsu.csc216.book_quiz.quiz;

import java.util.List;

import edu.ncsu.csc216.book_quiz.question.BookQuestions;
import edu.ncsu.csc216.book_quiz.util.EmptyQuestionListException;
import edu.ncsu.csc216.question_library.AdvancedQuestion;
import edu.ncsu.csc216.question_library.ElementaryQuestion;
import edu.ncsu.csc216.question_library.QuestionException;
import edu.ncsu.csc216.question_library.QuestionReader;
import edu.ncsu.csc216.question_library.QuestionWriter;
import edu.ncsu.csc216.question_library.StandardQuestion;

/**
 * Implements QuizMaster.
 * @author AlexiaMoore
 *
 */
public class BookQuiz implements QuizMaster {
	
	/** A QuestionWriter to write questions to a file */
	private QuestionWriter writer;
	
	/** A QuestionReader to read questions from to create the BookQuiz */
	private QuestionReader reader;
	
	/** Keeps track of the questions in the BookQuiz */
	private BookQuestions questions;
	
	/**
	 * Initializes both reader and questions
	 * @param file the file to initialize the BookQuiz with
	 * @throws QuestionException if there is an error in reading the file
	 */
	public BookQuiz(String file) throws QuestionException{
		//Attempts to create a QuestionReader with the file
		try{
			this.reader = new QuestionReader(file);
		} catch (QuestionException e){
			throw new QuestionException();
		}
		
		//Creates lists from the question reader of each question type
		List<StandardQuestion> stds = reader.getStandardQuestions();
		List<ElementaryQuestion> elems = reader.getElementaryQuestions();
		List<AdvancedQuestion> advs = reader.getAdvancedQuestions();
		
		this.questions = new BookQuestions(stds, elems, advs);
		
	}

	/**
	 * Are there any more questions remaining in this quiz?
	 * @return true if there are, false if there are not
	 */
	public boolean hasMoreQuestions() {
		return questions.hasMoreQuestions();
	}

	/**
	 * Get the text for the current question.
	 * @return the current question text
	 * @throws EmptyQuestionListException if there is no current question
	 */
	public String getCurrentQuestionText() throws EmptyQuestionListException {
		return null;
	}

	/**
	 * Get the possible answers for the current question
	 * @return the possible answers for the current question -- each answer
	 *         is a separate array element
	 * @throws EmptyQuestionListException if there is no current question
	 */
	public String[] getCurrentQuestionChoices() throws EmptyQuestionListException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Process the user's answer to the current question.
	 * @param answer  the user's answer to the question
	 * @return the graded response to the question
	 * @throws EmptyQuestionListException if there is no current question
	 */
	public String processAnswer(String answer) throws EmptyQuestionListException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * How many questions has the user answered correctly?
	 * @return the number of correct answers
	 */
	public int getNumCorrectQuestions() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * How many questions has the user attempted to answer.
	 * @return the number of attempts
	 */
	public int getNumAttemptedQuestions() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Adds a StandardQuestion to the QuestionLibrary.
	 * @param questionText text of question
	 * @param questionChoices array of possible answers
	 * @param correctAnswer correct answer
	 * @throws IllegalArgumentException if any parameters contain null or empty strings
	 */
	public void addStandardQuestion(String questionText,
			String[] questionChoices, String correctAnswer) {
		
		
	}

	/**
	 * Adds an ElementaryQuestion to the QuestionLibrary.
	 * @param questionText text of question
	 * @param questionChoices array of possible answers
	 * @param correctAnswer correct answer
	 * @param hint a hint for the question
	 * @throws IllegalArgumentException if any parameters contain null or empty strings
	 */
	public void addElementaryQuestion(String questionText,
			String[] questionChoices, String correctAnswer, String hint) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Adds an AdvancedQuestion to the QuestionLibrary.
	 * @param questionText text of question
	 * @param questionChoices array of possible answers
	 * @param correctAnswer correct answer
	 * @param comment a message for answering the question right
	 * @throws IllegalArgumentException if any parameters contain null or empty strings
	 */
	public void addAdvancedQuestion(String questionText,
			String[] questionChoices, String correctAnswer, String comment) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Writes the Questions to the given file
	 * @param questionFile file name to write questions to
	 * @throws QuestionException if the questions cannot be written to the 
	 * given file
	 */
	public void writeQuestions(String questionFile) throws QuestionException {
		this.writer = new QuestionWriter(questionFile);
		
	}
	
	/**
	 * 
	 * @param string
	 */
	private void validateString(String string){
		
	}
	
	/**
	 * 
	 * @param array
	 */
	private void ValidateStringArray(String[] array){
		
	}

}
