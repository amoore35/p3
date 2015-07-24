/**
 * 
 */
package edu.ncsu.csc216.book_quiz.quiz;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.book_quiz.question.BookQuestions;
import edu.ncsu.csc216.book_quiz.util.EmptyQuestionListException;
import edu.ncsu.csc216.question_library.ElementaryQuestion;
import edu.ncsu.csc216.question_library.QuestionException;
import edu.ncsu.csc216.question_library.QuestionReader;
import edu.ncsu.csc216.question_library.StandardQuestion;

/**
 * @author AlexiaMoore
 *
 */
public class BookQuizTest {
	
	private BookQuiz quiz;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		quiz = new BookQuiz("questions1.xml");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.quiz.BookQuiz#BookQuiz(java.lang.String)}.
	 */
	@Test
	public void testBookQuiz() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.quiz.BookQuiz#hasMoreQuestions()}.
	 */
	@Test
	public void testHasMoreQuestions() {
		//Assert that hasMoreQuestions() originally returns true
		assertTrue(quiz.hasMoreQuestions());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.quiz.BookQuiz#getCurrentQuestionText()}.
	 * @throws EmptyQuestionListException 
	 */
	@Test
	public void testGetCurrentQuestionText() throws EmptyQuestionListException {
		//Assert that first question is the first standard question in the xml file
		assertEquals("Standard Question 1", quiz.getCurrentQuestionText());
		//Answer a standard question with a wrong answer, which should lead to elementary 
		quiz.processAnswer("wrong answer");
		
		//Assert that current question changes to first elementary question in xml file
		assertEquals("Elementary Question 1", quiz.getCurrentQuestionText());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.quiz.BookQuiz#getCurrentQuestionChoices()}.
	 * @throws EmptyQuestionListException 
	 */
	@Test
	public void testGetCurrentQuestionChoices() throws EmptyQuestionListException {
		String[] expectedChoices = {"Choice a", "Choice b", "Choice c", "Choice d"};
		String[] actualChoices = quiz.getCurrentQuestionChoices();
		assertEquals(expectedChoices[0], actualChoices[0]);
		assertEquals(expectedChoices[1], actualChoices[1]);
		assertEquals(expectedChoices[2], actualChoices[2]);
		assertEquals(expectedChoices[3], actualChoices[3]);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.quiz.BookQuiz#processAnswer(java.lang.String)}.
	 * @throws EmptyQuestionListException 
	 */
	@Test
	public void testProcessAnswer() throws EmptyQuestionListException {
		//Assert that first question is standard question 1
		assertEquals("Standard Question 1", quiz.getCurrentQuestionText());
		//Assert that first question, standard question, returns incorrect
		assertEquals(BookQuestions.INCORRECT, quiz.processAnswer("wrong"));
		
		//Assert that next question is elementary question 1
		assertEquals("Elementary Question 1", quiz.getCurrentQuestionText());
		String hint = "Here is a hint. The correct answer is d.";
		//Assert that elementary question returns incorrect plus a hint when answered wrong
		assertEquals(BookQuestions.INCORRECT + BookQuestions.SEPARATOR + hint,
				quiz.processAnswer("wrong"));
		//Assert that next question is same question
		assertEquals("Elementary Question 1", quiz.getCurrentQuestionText());
		//Assert that this time processAnswer() just returns incorrect without a hint
		assertEquals(BookQuestions.INCORRECT, quiz.processAnswer("still wrong"));
		
		//Assert that next question is elementary question 2
		assertEquals("Elementary Question 2", quiz.getCurrentQuestionText());
		String hint2 = "Here is a hint. The correct answer is c.";
		//Assert that elementary question 2 returns incorrect plus a hint when answered wrong
		assertEquals(BookQuestions.INCORRECT + BookQuestions.SEPARATOR + hint2,
				quiz.processAnswer("wrong"));
		//Assert next question is same question
		assertEquals("Elementary Question 2", quiz.getCurrentQuestionText());
		//Assert that this time processAnswer() returns correct
		assertEquals(BookQuestions.CORRECT, quiz.processAnswer("c"));
		
		//Assert that next question is elementary question 1
		assertEquals("Elementary Question 1", quiz.getCurrentQuestionText());
		//Answer correctly on first try
		assertEquals(BookQuestions.CORRECT, quiz.processAnswer("a"));
		//Assert next question is elementary question 1
		assertEquals("Elementary Question 1", quiz.getCurrentQuestionText());
		//Answer correctly on first try
		assertEquals(BookQuestions.CORRECT, quiz.processAnswer("b"));
		
		//Assert that next question is standard question 2
		assertEquals("Standard Question 2", quiz.getCurrentQuestionText());
		//Answer correctly
		assertEquals(BookQuestions.CORRECT, quiz.processAnswer("c"));
		//Assert that next question is standard question 3
		assertEquals("Standard Question 3", quiz.getCurrentQuestionText());
		//Answer correctly
		assertEquals(BookQuestions.CORRECT, quiz.processAnswer("a"));
		
		//Assert that next question is advanced question 1
		assertEquals("Advanced Question 1", quiz.getCurrentQuestionText());
		//Answer correctly
		String comment = "Great work!";
		assertEquals(BookQuestions.CORRECT + BookQuestions.SEPARATOR + comment,
				quiz.processAnswer("d"));
		
		//Assert that next question is advanced question 1
		assertEquals("Advanced Question 1", quiz.getCurrentQuestionText());
		//Answer incorrectly
		assertEquals(BookQuestions.INCORRECT, quiz.processAnswer("wrong"));
		
		//Assert that there are no more questions left
		assertFalse(quiz.hasMoreQuestions());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.quiz.BookQuiz#getNumCorrectQuestions()}.
	 * @throws EmptyQuestionListException 
	 */
	@Test
	public void testGetNumCorrectQuestions() throws EmptyQuestionListException {
		//Assert that getNumCorrectQuestions() returns 0 originally
		assertEquals(0, quiz.getNumCorrectQuestions());
		
		//Assert that first question is standard question 1
		assertEquals("Standard Question 1", quiz.getCurrentQuestionText());
		//Assert that first question, standard question, returns incorrect
		assertEquals(BookQuestions.INCORRECT, quiz.processAnswer("wrong"));
		
		//Assert that getNumCorrectQuestions() returns 0
		assertEquals(0, quiz.getNumCorrectQuestions());
		
		//Assert that next question is elementary question 1
		assertEquals("Elementary Question 1", quiz.getCurrentQuestionText());
		String hint = "Here is a hint. The correct answer is d.";
		//Assert that elementary question returns incorrect plus a hint when answered wrong
		assertEquals(BookQuestions.INCORRECT + BookQuestions.SEPARATOR + hint,
				quiz.processAnswer("wrong"));
		
		//Assert that getNumCorrectQuestions() returns 0
		assertEquals(0, quiz.getNumCorrectQuestions());
		
		//Assert that next question is same question
		assertEquals("Elementary Question 1", quiz.getCurrentQuestionText());
		//Assert that this time processAnswer() just returns incorrect without a hint
		assertEquals(BookQuestions.INCORRECT, quiz.processAnswer("still wrong"));
		
		//Assert that getNumCorrectQuestions() returns 0
		assertEquals(0, quiz.getNumCorrectQuestions());
		
		//Assert that next question is elementary question 2
		assertEquals("Elementary Question 2", quiz.getCurrentQuestionText());
		String hint2 = "Here is a hint. The correct answer is c.";
		//Assert that elementary question 2 returns incorrect plus a hint when answered wrong
		assertEquals(BookQuestions.INCORRECT + BookQuestions.SEPARATOR + hint2,
				quiz.processAnswer("wrong"));
		
		//Assert that getNumCorrectQuestions() returns 0
		assertEquals(0, quiz.getNumCorrectQuestions());
		
		//Assert next question is same question
		assertEquals("Elementary Question 2", quiz.getCurrentQuestionText());
		//Assert that this time processAnswer() returns correct
		assertEquals(BookQuestions.CORRECT, quiz.processAnswer("c"));
		
		//Assert that getNumCorrectQuestions() returns 1
		assertEquals(1, quiz.getNumCorrectQuestions());
		
		
		//Assert that next question is elementary question 1
		assertEquals("Elementary Question 1", quiz.getCurrentQuestionText());
		//Answer correctly on first try
		assertEquals(BookQuestions.CORRECT, quiz.processAnswer("a"));
		
		//Assert that getNumCorrectQuestions() returns 2
		assertEquals(2, quiz.getNumCorrectQuestions());
		
		//Assert next question is elementary question 1
		assertEquals("Elementary Question 1", quiz.getCurrentQuestionText());
		//Answer correctly on first try
		assertEquals(BookQuestions.CORRECT, quiz.processAnswer("b"));
		
		//Assert that getNumCorrectQuestions() returns 3
		assertEquals(3, quiz.getNumCorrectQuestions());
		
		
		//Assert that next question is standard question 2
		assertEquals("Standard Question 2", quiz.getCurrentQuestionText());
		//Answer correctly
		assertEquals(BookQuestions.CORRECT, quiz.processAnswer("c"));
		
		//Assert that getNumCorrectQuestions() returns 4
		assertEquals(4, quiz.getNumCorrectQuestions());
		
		//Assert that next question is standard question 3
		assertEquals("Standard Question 3", quiz.getCurrentQuestionText());
		//Answer correctly
		assertEquals(BookQuestions.CORRECT, quiz.processAnswer("a"));
		
		//Assert that getNumCorrectQuestions() returns 5
		assertEquals(5, quiz.getNumCorrectQuestions());
		
		//Assert that next question is advanced question 1
		assertEquals("Advanced Question 1", quiz.getCurrentQuestionText());
		//Answer correctly
		String comment = "Great work!";
		assertEquals(BookQuestions.CORRECT + BookQuestions.SEPARATOR + comment,
				quiz.processAnswer("d"));
		
		//Assert that getNumCorrectQuestions() returns 6
		assertEquals(6, quiz.getNumCorrectQuestions());
		
		//Assert that next question is advanced question 1
		assertEquals("Advanced Question 1", quiz.getCurrentQuestionText());
		//Answer incorrectly
		assertEquals(BookQuestions.INCORRECT, quiz.processAnswer("wrong"));
		
		//Assert that getNumCorrectQuestions() returns 6
		assertEquals(6, quiz.getNumCorrectQuestions());
		
		//Assert that there are no more questions left
		assertFalse(quiz.hasMoreQuestions());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.quiz.BookQuiz#getNumAttemptedQuestions()}.
	 */
	@Test
	public void testGetNumAttemptedQuestions() throws EmptyQuestionListException {
		//Assert that getNumAttemptedQuestions() returns 0 originally
		assertEquals(0, quiz.getNumAttemptedQuestions());
		
		//Assert that first question is standard question 1
		assertEquals("Standard Question 1", quiz.getCurrentQuestionText());
		//Assert that first question, standard question, returns incorrect
		assertEquals(BookQuestions.INCORRECT, quiz.processAnswer("wrong"));
		
		//Assert that getNumAttemptedQuestions() returns 1
		assertEquals(1, quiz.getNumAttemptedQuestions());
		
		//Assert that next question is elementary question 1
		assertEquals("Elementary Question 1", quiz.getCurrentQuestionText());
		String hint = "Here is a hint. The correct answer is d.";
		//Assert that elementary question returns incorrect plus a hint when answered wrong
		assertEquals(BookQuestions.INCORRECT + BookQuestions.SEPARATOR + hint,
				quiz.processAnswer("wrong"));
		
		//Assert that getNumAttemptedQuestions() returns 2
		assertEquals(2, quiz.getNumAttemptedQuestions());
		
		//Assert that next question is same question
		assertEquals("Elementary Question 1", quiz.getCurrentQuestionText());
		//Assert that this time processAnswer() just returns incorrect without a hint
		assertEquals(BookQuestions.INCORRECT, quiz.processAnswer("still wrong"));
		
		//Assert that getNumAttemptedQuestions() returns 2 still
		assertEquals(2, quiz.getNumAttemptedQuestions());
				
		//Assert that next question is elementary question 2
		assertEquals("Elementary Question 2", quiz.getCurrentQuestionText());
		String hint2 = "Here is a hint. The correct answer is c.";
		//Assert that elementary question 2 returns incorrect plus a hint when answered wrong
		assertEquals(BookQuestions.INCORRECT + BookQuestions.SEPARATOR + hint2,
				quiz.processAnswer("wrong"));
		
		//Assert that getNumAttemptedQuestions() returns 3
		assertEquals(3, quiz.getNumAttemptedQuestions());
				
		//Assert next question is same question
		assertEquals("Elementary Question 2", quiz.getCurrentQuestionText());
		//Assert that this time processAnswer() returns correct
		assertEquals(BookQuestions.CORRECT, quiz.processAnswer("c"));
		
		//Assert that getNumAttemptedQuestions() returns 3 still
		assertEquals(3, quiz.getNumAttemptedQuestions());
				
		//Assert that next question is elementary question 1
		assertEquals("Elementary Question 1", quiz.getCurrentQuestionText());
		//Answer correctly on first try
		assertEquals(BookQuestions.CORRECT, quiz.processAnswer("a"));
		
		//Assert that getNumAttemptedQuestions() returns 4
		assertEquals(4, quiz.getNumAttemptedQuestions());
				
		//Assert next question is elementary question 1
		assertEquals("Elementary Question 1", quiz.getCurrentQuestionText());
		//Answer correctly on first try
		assertEquals(BookQuestions.CORRECT, quiz.processAnswer("b"));
		
		//Assert that getNumAttemptedQuestions() returns 5
		assertEquals(5, quiz.getNumAttemptedQuestions());
				
		//Assert that next question is standard question 2
		assertEquals("Standard Question 2", quiz.getCurrentQuestionText());
		//Answer correctly
		assertEquals(BookQuestions.CORRECT, quiz.processAnswer("c"));
		
		//Assert that getNumAttemptedQuestions() returns 6
		assertEquals(6, quiz.getNumAttemptedQuestions());
				
		//Assert that next question is standard question 3
		assertEquals("Standard Question 3", quiz.getCurrentQuestionText());
		//Answer correctly
		assertEquals(BookQuestions.CORRECT, quiz.processAnswer("a"));
		
		//Assert that getNumAttemptedQuestions() returns 7
		assertEquals(7, quiz.getNumAttemptedQuestions());
				
		//Assert that next question is advanced question 1
		assertEquals("Advanced Question 1", quiz.getCurrentQuestionText());
		//Answer correctly
		String comment = "Great work!";
		assertEquals(BookQuestions.CORRECT + BookQuestions.SEPARATOR + comment,
				quiz.processAnswer("d"));
		
		//Assert that getNumAttemptedQuestions() returns 8
		assertEquals(8, quiz.getNumAttemptedQuestions());
		
		//Assert that next question is advanced question 1
		assertEquals("Advanced Question 1", quiz.getCurrentQuestionText());
		//Answer incorrectly
		assertEquals(BookQuestions.INCORRECT, quiz.processAnswer("wrong"));
		
		//Assert that getNumAttemptedQuestions() returns 9
		assertEquals(9, quiz.getNumAttemptedQuestions());
				
		//Assert that there are no more questions left
		assertFalse(quiz.hasMoreQuestions());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.quiz.BookQuiz#addStandardQuestion(java.lang.String, java.lang.String[], java.lang.String)}.
	 * @throws EmptyQuestionListException 
	 */
	@Test
	public void testAddStandardQuestion() throws QuestionException, EmptyQuestionListException{

		String[] choices = {"A", "B", "C", "D"};
		quiz.addStandardQuestion("Q1", choices, "A");
		
		quiz.writeQuestions("questions1 copy.xml");
		
		QuestionReader reader1 = new QuestionReader("questions1.xml");
		QuestionReader reader2 = new QuestionReader("questions1 copy.xml");
		
		assertEquals(3, reader1.getStandardQuestions().size());
		assertEquals(4, reader2.getStandardQuestions().size());
		assertTrue(reader2.getStandardQuestions().get(3).getQuestion().equals("Q1"));
		assertTrue(reader2.getStandardQuestions().get(3).getAnswer().equals("A"));
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.quiz.BookQuiz#addElementaryQuestion(java.lang.String, java.lang.String[], java.lang.String, java.lang.String)}.
	 * @throws QuestionException 
	 */
	@Test
	public void testAddElementaryQuestion() throws QuestionException {
		String[] choices = {"A", "B", "C", "D"};
		quiz.addElementaryQuestion("Q1", choices, "B", "Hint");

		quiz.writeQuestions("questions1 copy.xml");
		
		QuestionReader reader1 = new QuestionReader("questions1.xml");
		QuestionReader reader2 = new QuestionReader("questions1 copy.xml");
		
		assertEquals(4, reader1.getElementaryQuestions().size());
		assertEquals(5, reader2.getElementaryQuestions().size());
		assertTrue(reader2.getElementaryQuestions().get(4).getQuestion().equals("Q1"));
		assertTrue(reader2.getElementaryQuestions().get(4).getAnswer().equals("B"));
		assertTrue(reader2.getElementaryQuestions().get(4).getHint().equals("Hint"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.quiz.BookQuiz#addAdvancedQuestion(java.lang.String, java.lang.String[], java.lang.String, java.lang.String)}.
	 * @throws QuestionException 
	 */
	@Test
	public void testAddAdvancedQuestion() throws QuestionException {
		String[] choices = {"A", "B", "C", "D"};
		quiz.addAdvancedQuestion("Q1", choices, "C", "Comment");

		quiz.writeQuestions("questions1 copy.xml");
		
		QuestionReader reader1 = new QuestionReader("questions1.xml");
		QuestionReader reader2 = new QuestionReader("questions1 copy.xml");
		
		assertEquals(2, reader1.getAdvancedQuestions().size());
		assertEquals(3, reader2.getAdvancedQuestions().size());
		assertTrue(reader2.getAdvancedQuestions().get(2).getQuestion().equals("Q1"));
		assertTrue(reader2.getAdvancedQuestions().get(2).getAnswer().equals("C"));
		assertTrue(reader2.getAdvancedQuestions().get(2).getComment().equals("Comment"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.quiz.BookQuiz#writeQuestions(java.lang.String)}.
	 * @throws QuestionException 
	 */
	@Test
	public void testWriteQuestions() throws QuestionException {
		BookQuiz testQuiz = new BookQuiz("questions1 copy 2.xml");
		String[] choices = {"A", "B", "C", "D"};
		
		QuestionReader reader = new QuestionReader("questions1 copy 2.xml");
		int origSizeAdv = reader.getAdvancedQuestions().size();
		int origSizeElem = reader.getElementaryQuestions().size();
		int origSizeStd = reader.getStandardQuestions().size();
		
		testQuiz.addAdvancedQuestion("AQ", choices, "A", "Comment");
		testQuiz.addElementaryQuestion("EQ", choices, "B", "Hint");
		testQuiz.addStandardQuestion("SQ", choices, "C");
		
		testQuiz.writeQuestions("questions1 copy 2.xml");
		
		QuestionReader reader2 = new QuestionReader("questions1 copy 2.xml");
		assertEquals(origSizeStd + 1, reader2.getStandardQuestions().size());
		assertEquals(origSizeElem + 1, reader2.getElementaryQuestions().size());
		assertEquals(origSizeAdv + 1, reader2.getAdvancedQuestions().size());
		
		

	}

}
