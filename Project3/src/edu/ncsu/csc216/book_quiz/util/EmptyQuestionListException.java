/**
 * 
 */
package edu.ncsu.csc216.book_quiz.util;

/**
 * Extends Exception.[SEE UC6, E1]
 * @author AlexiaMoore
 *
 */
public class EmptyQuestionListException extends Exception {
	
	/** */
	private static final String MESSAGE = "Exception: Empty Question List";
	
	/** */
	private static final long serialVersionUID = 0;
	
	/**
	 * 
	 */
	public EmptyQuestionListException(){
		super(MESSAGE);
	}
	
	/**
	 * 
	 * @param message
	 */
	public EmptyQuestionListException(String message){
		super(message);
	}

}
