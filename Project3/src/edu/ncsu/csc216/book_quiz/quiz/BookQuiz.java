/**
 * 
 */
package edu.ncsu.csc216.book_quiz.quiz;

import java.util.List;

import edu.ncsu.csc216.book_quiz.question.BookQuestions;
import edu.ncsu.csc216.book_quiz.util.EmptyQuestionListException;
import edu.ncsu.csc216.question_library.AdvancedQuestion;
import edu.ncsu.csc216.question_library.ElementaryQuestion;
import edu.ncsu.csc216.question_library.Question;
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
			throw new QuestionException("Invalid File.");
		}
		
		
		//Creates lists from the question reader of each question type
		List<StandardQuestion> stds = reader.getStandardQuestions();
		List<ElementaryQuestion> elems = reader.getElementaryQuestions();
		List<AdvancedQuestion> advs = reader.getAdvancedQuestions();
		
		this.questions = new BookQuestions(stds, elems, advs);
		
		this.writer = null;
		
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
		return questions.getCurrentQuestionText();
	}

	/**
	 * Get the possible answers for the current question
	 * @return the possible answers for the current question -- each answer
	 *         is a separate array element
	 * @throws EmptyQuestionListException if there is no current question
	 */
	public String[] getCurrentQuestionChoices() throws EmptyQuestionListException {
		return questions.getCurrentQuestionChoices();
	}

	/**
	 * Process the user's answer to the current question.
	 * @param answer  the user's answer to the question
	 * @return the graded response to the question
	 * @throws EmptyQuestionListException if there is no current question
	 */
	public String processAnswer(String answer) throws EmptyQuestionListException {
		return questions.processAnswer(answer);
	}

	/**
	 * How many questions has the user answered correctly?
	 * @return the number of correct answers
	 */
	public int getNumCorrectQuestions() {
		return questions.getNumCorrectQuestions();
	}

	/**
	 * How many questions has the user attempted to answer.
	 * @return the number of attempts
	 */
	public int getNumAttemptedQuestions() {
		return questions.getNumAttemptedQuestions();
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
		//Check for null inputs
		if (questionText == null || correctAnswer == null){
			throw new IllegalArgumentException("Cannot create question.");
		}
		
		if (questionChoices == null){
			throw new IllegalArgumentException("Cannot create question.");
		}
		if (questionChoices.length != 4){
			throw new IllegalArgumentException("Cannot create question.");
		}
		for (String choice : questionChoices){
			if (choice == null){
				throw new IllegalArgumentException("Cannot create question.");
			}
		}
		for (String choice : questionChoices){
			choice = choice.trim();
			if (choice.equals("")){
				throw new IllegalArgumentException("Cannot create question.");
			}
		}
		//Trim all strings to get rid of trailing and leading whitespace
		questionText = questionText.trim();
		correctAnswer = correctAnswer.trim();
		
		//Check for null or empty inputs
		if (questionText.equals("") || correctAnswer.equals("")){
			throw new IllegalArgumentException("Cannot create question.");
		}
		
		
		//Create new StandardQuestion and use setters
		StandardQuestion standardQuestion = new StandardQuestion();
		standardQuestion.setQuestion(questionText);
		standardQuestion.setChoiceA(questionChoices[0]);
		standardQuestion.setChoiceB(questionChoices[1]);
		standardQuestion.setChoiceC(questionChoices[2]);
		standardQuestion.setChoiceD(questionChoices[3]);
		standardQuestion.setAnswer(correctAnswer);
		questions.addStandardQuestion(standardQuestion);
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
		//Check for null inputs
		if (questionText == null || correctAnswer == null || hint == null){
			throw new IllegalArgumentException("Cannot create question.");
		}
		
		if (questionChoices == null){
			throw new IllegalArgumentException("Cannot create question.");
		}
		if (questionChoices.length != 4){
			throw new IllegalArgumentException("Cannot create question.");
		}
		for (String choice : questionChoices){
			if (choice == null){
				throw new IllegalArgumentException("Cannot create question.");
			}
		}
		for (String choice : questionChoices){
			choice = choice.trim();
			if (choice.equals("")){
				throw new IllegalArgumentException("Cannot create question.");
			}
		}
		//Trim all strings to get rid of trailing and leading whitespace
		questionText = questionText.trim();
		correctAnswer = correctAnswer.trim();
		hint = hint.trim();
		
		//Check for null or empty inputs
		if (questionText.equals("") || correctAnswer.equals("") || hint.equals("")){
			throw new IllegalArgumentException("Cannot create question.");
		}
		
		
		//Create new ElementaryQuestion and use setters
		ElementaryQuestion elementaryQuestion = new ElementaryQuestion();
		elementaryQuestion.setQuestion(questionText);
		elementaryQuestion.setChoiceA(questionChoices[0]);
		elementaryQuestion.setChoiceB(questionChoices[1]);
		elementaryQuestion.setChoiceC(questionChoices[2]);
		elementaryQuestion.setChoiceD(questionChoices[3]);
		elementaryQuestion.setAnswer(correctAnswer);
		elementaryQuestion.setHint(hint);
		questions.addElementaryQuestion(elementaryQuestion);
		
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
		
		//Check for null inputs
		if (questionText == null || correctAnswer == null || comment == null){
			throw new IllegalArgumentException("Cannot create question.");
		}
		//Trim all strings to get rid of trailing and leading whitespace
		questionText = questionText.trim();
		if (questionChoices == null){
			throw new IllegalArgumentException("Cannot create question.");
		}
		if (questionChoices.length != 4){
			throw new IllegalArgumentException("Cannot create question.");
		}
		for (int i = 0; i < questionChoices.length; i++){
			if (questionChoices[i] == null){
				throw new IllegalArgumentException("Cannot create question.");
			}
		}
		for (String choice : questionChoices){
			choice = choice.trim();
			if (choice.equals("")){
				throw new IllegalArgumentException("Cannot create question.");
			}
		}
		
		
		correctAnswer = correctAnswer.trim();
		comment = comment.trim();
		
		//Check for empty inputs
		if (questionText.equals("") || correctAnswer.equals("") || comment.equals("")){
			throw new IllegalArgumentException("Cannot create question.");
		}
		
		
		//Create new AdvancedQuestion and use setters
		AdvancedQuestion advancedQuestion = new AdvancedQuestion();
		advancedQuestion.setQuestion(questionText);
		advancedQuestion.setChoiceA(questionChoices[0]);
		advancedQuestion.setChoiceB(questionChoices[1]);
		advancedQuestion.setChoiceC(questionChoices[2]);
		advancedQuestion.setChoiceD(questionChoices[3]);
		advancedQuestion.setAnswer(correctAnswer);
		advancedQuestion.setComment(comment);
		questions.addAdvancedQuestion(advancedQuestion);
	}

	/**
	 * Writes the Questions to the given file
	 * @param questionFile file name to write questions to
	 * @throws QuestionException if the questions cannot be written to the 
	 * given file
	 */
	public void writeQuestions(String questionFile) throws QuestionException {
		//Attempt to create QuestionWriter with given file
		try{
			this.writer = new QuestionWriter(questionFile);
		} catch (QuestionException e){
			throw new QuestionException("Unable to write to file.");
		}
		List<Question> stdQuestions = questions.getStandardQuestions();
		for (Question q : stdQuestions){
			writer.addStandardQuestion((StandardQuestion) q);
		}
		List<Question> elemQuestions = questions.getElementaryQuestions();
		for (Question q : elemQuestions){
			writer.addElementaryQuestion((ElementaryQuestion) q);
		}
		List<Question> advQuestions = questions.getAdvancedQuestions();
		for (Question q : advQuestions){
			writer.addAdvancedQuestion((AdvancedQuestion) q);
		}
		
		writer.marshal();
	}

}
