/**
 * 
 */
package edu.ncsu.csc216.book_quiz.question;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.book_quiz.util.EmptyQuestionListException;
import edu.ncsu.csc216.question_library.AdvancedQuestion;
import edu.ncsu.csc216.question_library.ElementaryQuestion;
import edu.ncsu.csc216.question_library.Question;
import edu.ncsu.csc216.question_library.QuestionException;
import edu.ncsu.csc216.question_library.QuestionReader;
import edu.ncsu.csc216.question_library.QuestionWriter;
import edu.ncsu.csc216.question_library.StandardQuestion;

/**
 * @author AlexiaMoore
 *
 */
public class BookQuestionsTest {
	
	private QuestionReader reader;
	private QuestionReader reader2;
	private BookQuestions questions;
	private BookQuestions questions2;
	private List<AdvancedQuestion> advQs;
	private List<ElementaryQuestion> elemQs;
	private List<StandardQuestion> stdQs;
	private List<AdvancedQuestion> advQs2;
	private List<ElementaryQuestion> elemQs2;
	private List<StandardQuestion> stdQs2;
	private static final int ELEM_SIZE = 4;
	private static final int ADV_SIZE = 2;
	private static final int STD_SIZE = 3;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		reader = new QuestionReader("questions1.xml");
		advQs = reader.getAdvancedQuestions();
		elemQs = reader.getElementaryQuestions();
		stdQs = reader.getStandardQuestions();
		questions = new BookQuestions(stdQs, elemQs, advQs);
		
		reader2 = new QuestionReader("questions1.xml");
		advQs2 = reader2.getAdvancedQuestions();
		elemQs2 = reader2.getElementaryQuestions();
		stdQs2 = reader2.getStandardQuestions();
		questions2 = new BookQuestions(stdQs2, elemQs2, advQs2);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#hasMoreQuestions()}.
	 */
	@Test
	public void testHasMoreQuestions() {
		//Assert that there are more questions initially
		assertTrue(questions.hasMoreQuestions());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#getCurrentQuestionText()}.
	 * @throws EmptyQuestionListException 
	 */
	@Test
	public void testGetCurrentQuestionText() throws EmptyQuestionListException {
		String question1 = stdQs.get(0).getQuestion();
		assertEquals(question1, questions.getCurrentQuestionText());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#getCurrentQuestionChoices()}.
	 * @throws EmptyQuestionListException 
	 */
	@Test
	public void testGetCurrentQuestionChoices() throws EmptyQuestionListException {
		String[] questionChoices = new String[4];
		questionChoices[0] = stdQs.get(0).getChoiceA();
		questionChoices[1] = stdQs.get(0).getChoiceB();
		questionChoices[2] = stdQs.get(0).getChoiceC();
		questionChoices[3] = stdQs.get(0).getChoiceD();
		String[] currentChoices = questions.getCurrentQuestionChoices();
		assertTrue(questionChoices[0].equals(currentChoices[0]));
		assertTrue(questionChoices[1].equals(currentChoices[1]));
		assertTrue(questionChoices[2].equals(currentChoices[2]));
		assertTrue(questionChoices[3].equals(currentChoices[3]));
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#processAnswer(java.lang.String)}.
	 * @throws EmptyQuestionListException 
	 */
	@Test
	public void testProcessAnswer() throws EmptyQuestionListException {
		//The following questions answer everything correctly. See XML file in order
		//to see that these are correct answers and they have correct comments.
		
		//Answer two standard questions correctly
		assertEquals(BookQuestions.CORRECT, questions.processAnswer("d"));
		assertEquals(BookQuestions.CORRECT, questions.processAnswer("c"));
		
		//Transition to advanced question and answer two correctly
		assertEquals(BookQuestions.CORRECT + BookQuestions.SEPARATOR + "Great work!",
				questions.processAnswer("d"));
		assertEquals(BookQuestions.CORRECT + BookQuestions.SEPARATOR + "Good job!",
				questions.processAnswer("c"));
		
		//Assert that there are no more questions to ask in the quiz
		assertFalse(questions.hasMoreQuestions());
		
		
		//The following questions use the same XML file
		
		//Answer standard question incorrectly
		assertEquals(BookQuestions.INCORRECT, questions2.processAnswer("a"));
		//Transition to elementary and answer one incorrectly
		assertEquals(BookQuestions.INCORRECT + " Here is a hint. The correct answer is d.",
				questions2.processAnswer("c"));
		//Answer correctly on second try
		assertEquals(BookQuestions.CORRECT, questions2.processAnswer("d"));
		//Answer incorrectly
		assertEquals(BookQuestions.INCORRECT + " Here is a hint. The correct answer is c.",
				questions2.processAnswer("b"));
		//Answer incorrectly on second try
		assertEquals(BookQuestions.INCORRECT, questions2.processAnswer("a"));
		//Answer two correctly in a row first try (elementary)
		assertEquals(BookQuestions.CORRECT, questions2.processAnswer("a"));
		assertEquals(BookQuestions.CORRECT, questions2.processAnswer("b"));
		//Transition to standard and answer 2 correctly
		assertEquals(BookQuestions.CORRECT, questions2.processAnswer("c"));
		assertEquals(BookQuestions.CORRECT, questions2.processAnswer("a"));
		//Transition to advanced and answer incorrectly
		assertEquals(BookQuestions.INCORRECT, questions2.processAnswer("a"));
		//Assert that there are no more standard questions to transition to
		assertFalse(questions2.hasMoreQuestions());
		
		//Attempt to process an answer when there are no more questions
		try{
			questions2.processAnswer("a");
		} catch (EmptyQuestionListException e){
			assertEquals("No more questions to ask.", e.getMessage());
		}
		
		try{
			questions2.getCurrentQuestionChoices();
		} catch (EmptyQuestionListException e){
			assertEquals("No more questions to ask.", e.getMessage());
		}

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#getNumCorrectQuestions()}.
	 * @throws EmptyQuestionListException 
	 */
	@Test
	public void testGetNumCorrectQuestions() throws EmptyQuestionListException {
		//Assert no questions have been answered correctly yet
		assertEquals(0, questions2.getNumCorrectQuestions());
		
		//Answer standard question incorrectly
		assertEquals(BookQuestions.INCORRECT, questions2.processAnswer("a"));
		//Assert getNumCorrectQuestions() returns 0
		assertEquals(0, questions2.getNumCorrectQuestions());
		//Transition to elementary and answer one incorrectly
		assertEquals(BookQuestions.INCORRECT + " Here is a hint. The correct answer is d.",
				questions2.processAnswer("c"));
		//Assert getNumCorrectQuestions() returns 0
		assertEquals(0, questions2.getNumCorrectQuestions());
		//Answer correctly on second try
		assertEquals(BookQuestions.CORRECT, questions2.processAnswer("d"));
		//Assert getNumCorrectQuestions() returns 1
		assertEquals(1, questions2.getNumCorrectQuestions());
		//Answer incorrectly
		assertEquals(BookQuestions.INCORRECT + " Here is a hint. The correct answer is c.",
				questions2.processAnswer("b"));
		//Assert getNumCorrectQuestions() returns 1
		assertEquals(1, questions2.getNumCorrectQuestions());
		//Answer incorrectly on second try
		assertEquals(BookQuestions.INCORRECT, questions2.processAnswer("a"));
		//Assert getNumCorrectQuestions() returns 1
		assertEquals(1, questions2.getNumCorrectQuestions());
		//Answer two correctly in a row first try (elementary)
		assertEquals(BookQuestions.CORRECT, questions2.processAnswer("a"));
		//Assert getNumCorrectQuestions() returns 2
		assertEquals(2, questions2.getNumCorrectQuestions());
		assertEquals(BookQuestions.CORRECT, questions2.processAnswer("b"));
		//Assert getNumCorrectQuestions() returns 3
		assertEquals(3, questions2.getNumCorrectQuestions());
		//Transition to standard and answer 2 correctly
		assertEquals(BookQuestions.CORRECT, questions2.processAnswer("c"));
		//Assert getNumCorrectQuestions() returns 4
		assertEquals(4, questions2.getNumCorrectQuestions());
		assertEquals(BookQuestions.CORRECT, questions2.processAnswer("a"));
		//Assert getNumCorrectQuestions() returns 5
		assertEquals(5, questions2.getNumCorrectQuestions());
		//Transition to advanced and answer incorrectly
		assertEquals(BookQuestions.INCORRECT, questions2.processAnswer("a"));
		//Assert getNumCorrectQuestions() returns 5
		assertEquals(5, questions2.getNumCorrectQuestions());
		//Assert that there are no more standard questions to transition to
		assertFalse(questions2.hasMoreQuestions());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#getNumAttemptedQuestions()}.
	 * @throws EmptyQuestionListException 
	 */
	@Test
	public void testGetNumAttemptedQuestions() throws EmptyQuestionListException {
		//Assert getNumAttemptedQuestions returns 0
		assertEquals(0, questions2.getNumAttemptedQuestions());
		
		//Answer standard question incorrectly
		assertEquals(BookQuestions.INCORRECT, questions2.processAnswer("a"));
		//Assert getNumAttemptedQuestions returns 1
		assertEquals(1, questions2.getNumAttemptedQuestions());
		//Transition to elementary and answer one incorrectly
		assertEquals(BookQuestions.INCORRECT + " Here is a hint. The correct answer is d.",
				questions2.processAnswer("c"));
		//Assert getNumAttemptedQuestions returns 2
		assertEquals(2, questions2.getNumAttemptedQuestions());
		//Answer correctly on second try
		assertEquals(BookQuestions.CORRECT, questions2.processAnswer("d"));
		//Assert getNumAttemptedQuestions returns 2 still
		assertEquals(2, questions2.getNumAttemptedQuestions());
		//Answer incorrectly
		assertEquals(BookQuestions.INCORRECT + " Here is a hint. The correct answer is c.",
				questions2.processAnswer("b"));
		//Assert getNumAttemptedQuestions returns 3
		assertEquals(3, questions2.getNumAttemptedQuestions());
		//Answer incorrectly on second try
		assertEquals(BookQuestions.INCORRECT, questions2.processAnswer("a"));
		//Assert getNumAttemptedQuestions returns 3 still
		assertEquals(3, questions2.getNumAttemptedQuestions());
		//Answer two correctly in a row first try (elementary)
		assertEquals(BookQuestions.CORRECT, questions2.processAnswer("a"));
		//Assert getNumAttemptedQuestions returns 4
		assertEquals(4, questions2.getNumAttemptedQuestions());
		assertEquals(BookQuestions.CORRECT, questions2.processAnswer("b"));
		//Assert getNumAttemptedQuestions returns 5
		assertEquals(5, questions2.getNumAttemptedQuestions());
		//Transition to standard and answer 2 correctly
		assertEquals(BookQuestions.CORRECT, questions2.processAnswer("c"));
		//Assert getNumAttemptedQuestions returns 6
		assertEquals(6, questions2.getNumAttemptedQuestions());
		assertEquals(BookQuestions.CORRECT, questions2.processAnswer("a"));
		//Assert getNumAttemptedQuestions returns 7
		assertEquals(7, questions2.getNumAttemptedQuestions());
		//Transition to advanced and answer incorrectly
		assertEquals(BookQuestions.INCORRECT, questions2.processAnswer("a"));
		//Assert getNumAttemptedQuestions returns 8
		assertEquals(8, questions2.getNumAttemptedQuestions());
		//Assert that there are no more standard questions to transition to
		assertFalse(questions2.hasMoreQuestions());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#addStandardQuestion(edu.ncsu.csc216.question_library.StandardQuestion)}.
	 * @throws QuestionException 
	 */
	@Test
	public void testAddStandardQuestion() throws QuestionException {
		StandardQuestion stdQ = new StandardQuestion();
		stdQ.setAnswer("a");
		stdQ.setChoiceA("a");
		stdQ.setChoiceB("b");
		stdQ.setChoiceC("c");
		stdQ.setChoiceD("d");
		stdQ.setQuestion("What is the answer to this test question?");
		
		List<Question> standQs = questions.getStandardQuestions();
		assertFalse(standQs.contains(stdQ));
		questions.addStandardQuestion(stdQ);
		
		standQs = questions.getStandardQuestions();
		assertTrue(standQs.contains(stdQ));
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#addElementaryQuestion(edu.ncsu.csc216.question_library.ElementaryQuestion)}.
	 */
	@Test
	public void testAddElementaryQuestion() {
		ElementaryQuestion elemQ = new ElementaryQuestion();
		elemQ.setAnswer("a");
		elemQ.setChoiceA("a");
		elemQ.setChoiceB("b");
		elemQ.setChoiceC("c");
		elemQ.setChoiceD("d");
		elemQ.setQuestion("What is the answer to this test question?");
		elemQ.setHint("Hint: it's not b.");
		
		List<Question> elemQs = questions.getElementaryQuestions();
		assertFalse(elemQs.contains(elemQ));
		questions.addElementaryQuestion(elemQ);
		
		elemQs = questions.getElementaryQuestions();
		assertTrue(elemQs.contains(elemQ));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#addAdvancedQuestion(edu.ncsu.csc216.question_library.AdvancedQuestion)}.
	 */
	@Test
	public void testAddAdvancedQuestion() {
		AdvancedQuestion advQ = new AdvancedQuestion();
		advQ.setAnswer("a");
		advQ.setChoiceA("a");
		advQ.setChoiceB("b");
		advQ.setChoiceC("c");
		advQ.setChoiceD("d");
		advQ.setQuestion("What is the answer to this test question?");
		advQ.setComment("Nice job.");
		
		List<Question> advQs = questions.getAdvancedQuestions();
		assertFalse(advQs.contains(advQ));
		questions.addAdvancedQuestion(advQ);
		
		advQs = questions.getAdvancedQuestions();
		assertTrue(advQs.contains(advQ));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#getStandardQuestions()}.
	 */
	@Test
	public void testGetStandardQuestions() {
		List<Question> standQuests = questions.getStandardQuestions();
		assertEquals("Standard Question 1", standQuests.get(0).getQuestion());
		assertEquals("Choice a", standQuests.get(0).getChoiceA());
		assertEquals("Choice b", standQuests.get(0).getChoiceB());
		assertEquals("Choice c", standQuests.get(0).getChoiceC());
		assertEquals("Choice d", standQuests.get(0).getChoiceD());
		assertEquals("d", standQuests.get(0).getAnswer());
		assertEquals(STD_SIZE, standQuests.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#getElementaryQuestions()}.
	 */
	@Test
	public void testGetElementaryQuestions() {
		List<Question> elemQuests = questions.getElementaryQuestions();
		assertEquals("Here is a hint. The correct answer is d.", 
				(((ElementaryQuestion) elemQuests.get(0)).getHint()));
		assertEquals(ELEM_SIZE, elemQuests.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#getAdvancedQuestions()}.
	 */
	@Test
	public void testGetAdvancedQuestions() {
		List<Question> advQuests = questions.getAdvancedQuestions();
		assertEquals("Great work!", (((AdvancedQuestion) advQuests.get(0)).getComment()));
		assertEquals(ADV_SIZE, advQuests.size());
	}

}
