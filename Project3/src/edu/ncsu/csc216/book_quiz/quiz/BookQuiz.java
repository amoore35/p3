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
	
	/** */
	private QuestionWriter writer;
	
	/** */
	private QuestionReader reader;
	
	/** */
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
	 * @return
	 */
	public boolean hasMoreQuestions() {
		return false;
	}

	/**
	 * @return
	 * @throws
	 */
	public String getCurrentQuestionText() throws EmptyQuestionListException {
		return null;
	}

	/**
	 * @return
	 */
	public String[] getCurrentQuestionChoices()
			throws EmptyQuestionListException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param
	 * @return
	 */
	public String processAnswer(String answer)
			throws EmptyQuestionListException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return
	 */
	public int getNumCorrectQuestions() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return
	 */
	public int getNumAttemptedQuestions() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @param
	 * @param
	 * @param
	 */
	public void addStandardQuestion(String questionText,
			String[] questionChoices, String correctAnswer) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param
	 * @param
	 * @param
	 * @param
	 */
	public void addElementaryQuestion(String questionText,
			String[] questionChoices, String correctAnswer, String hint) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param
	 * @param
	 * @param
	 * @param
	 */
	public void addAdvancedQuestion(String questionText,
			String[] questionChoices, String correctAnswer, String comment) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param
	 * @throws
	 */
	public void writeQuestions(String questionFile) throws QuestionException {
		// TODO Auto-generated method stub
		
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
