/**
 * 
 */
package edu.ncsu.csc216.book_quiz.ui;

import edu.ncsu.csc216.book_quiz.quiz.BookQuiz;
import edu.ncsu.csc216.book_quiz.quiz.QuizMaster;
import edu.ncsu.csc216.question_library.QuestionException;

/**
 * The graphical user interface for the project.
 * @author AlexiaMoore
 *
 */
public class BookQuizGUI {
	
	private QuizMaster quizMaster;
	
	public BookQuizGUI(String file) throws QuestionException{
		quizMaster = new BookQuiz(file);
	}

}
