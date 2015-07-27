/**
 * 
 */
package edu.ncsu.csc216.book_quiz.util;

/**
 * Extends Exception. Should be thrown when there are no more questions in the current 
 * state of questions.
 * @author AlexiaMoore
 *
 */
public class EmptyQuestionListException extends Exception {
	
	/** The default message of the exception */
	private static final String MESSAGE = "No more questions in list.";
	
	/** The serialVersionUID */
	private static final long serialVersionUID = 1;
	
	/**
	 * Constructs an EmptyQuestionListException with the default message.
	 */
	public EmptyQuestionListException(){
		super(MESSAGE);
	}
	
	/**
	 * Constructs an EmptyQuestionListException with a given message.
	 * @param message the message to pass to the exception
	 */
	public EmptyQuestionListException(String message){
		super(message);
	}

}
