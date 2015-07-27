
package edu.ncsu.csc216.book_quiz.question;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.book_quiz.util.EmptyQuestionListException;
import edu.ncsu.csc216.question_library.AdvancedQuestion;
import edu.ncsu.csc216.question_library.ElementaryQuestion;
import edu.ncsu.csc216.question_library.Question;
import edu.ncsu.csc216.question_library.QuestionReader;
import edu.ncsu.csc216.question_library.StandardQuestion;

/**
 * Test for the BookQuestions class to determine if the methods return the expected results.
 * The file "questions1.xml" can be viewed to verify where the expected results come from.
 * @author AlexiaMoore
 *
 */
public class BookQuestionsTest {
	
	/** Two QuestionReaders to keep track of for different tests */
	private QuestionReader reader;
	private QuestionReader reader2;
	
	/** Two BookQuestions objects to test */
	private BookQuestions questions;
	private BookQuestions questions2;
	
	/** Lists of different types of questions to manipulate */
	private List<AdvancedQuestion> advQs;
	private List<ElementaryQuestion> elemQs;
	private List<StandardQuestion> stdQs;
	private List<AdvancedQuestion> advQs2;
	private List<ElementaryQuestion> elemQs2;
	private List<StandardQuestion> stdQs2;
	
	/** Size of the lists for the used file */
	private static final int ELEM_SIZE = 4;
	private static final int ADV_SIZE = 2;
	private static final int STD_SIZE = 3;

	/**
	 * Sets up all of the data members to manipulate throughout the tests with a question file
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
	 * Test to determine if the hasMoreQuestions() method returns true when it should
	 */
	@Test
	public void testHasMoreQuestions() {
		//Assert that there are more questions initially
		assertTrue(questions.hasMoreQuestions());
		
	}

	/**
	 * Test to determine if the getCurrentQuestionText() method returns the text of the current question
	 * @throws EmptyQuestionListException if there isn't a current question
	 */
	@Test
	public void testGetCurrentQuestionText() throws EmptyQuestionListException {
		String question1 = stdQs.get(0).getQuestion();
		assertEquals(question1, questions.getCurrentQuestionText());
	}

	/**
	 * Test to determine if the getCurrentQuestionChoices() method returns the choices of the current question
	 * @throws EmptyQuestionListException if there isn't a current question
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
	 * Test to determine if the processAnswer() method returns the correct responses based on answering questions
	 * correctly and incorrectly.
	 * @throws EmptyQuestionListException if there isn't a current question to process
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
			assertEquals("No more questions in list.", e.getMessage());
		}
		try{
			questions2.getCurrentQuestionText();
		} catch (EmptyQuestionListException e){
			assertEquals("No more questions in list.", e.getMessage());
		}
		
		
		
		StandardQuestion oneQ = new StandardQuestion();
		oneQ.setAnswer("A");
		oneQ.setChoiceA("A");
		oneQ.setChoiceB("B");
		oneQ.setChoiceC("C");
		oneQ.setChoiceD("D");
		oneQ.setQuestion("Question");
		List<StandardQuestion> stand1Q = new ArrayList<StandardQuestion>();
		stand1Q.add(oneQ);
		List<ElementaryQuestion> emptyElems = new ArrayList<ElementaryQuestion>();
		List<AdvancedQuestion> emptyAdvs = new ArrayList<AdvancedQuestion>();
		BookQuestions questions3 = new BookQuestions(stand1Q, emptyElems, emptyAdvs);
		assertEquals("Question", questions3.getCurrentQuestionText());
		questions3.processAnswer("wrong");
		
		
		assertFalse(questions2.hasMoreQuestions());
		questions2.addStandardQuestion(oneQ);
		assertTrue(questions2.hasMoreQuestions());
	
	}

	/**
	 * Test to determine if getNumCorrectQuestions() returns the number of correct questions answered
	 * @throws EmptyQuestionListException if there isn't a current question to process for testing purposes
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
	 * Test to determine if the getNumAttemptedQuestion() method returns the number of attempts in the quiz
	 * @throws EmptyQuestionListException if there isn't a current question to process for testing purposes
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
	 * Test to determine if the addStandardQuestion() method adds the question to the list
	 */
	@Test
	public void testAddStandardQuestion() {
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
	 * Test to determine if the addElementaryQuestion() method adds the question to the list
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
		
		List<Question> elemQues = questions.getElementaryQuestions();
		assertFalse(elemQues.contains(elemQ));
		questions.addElementaryQuestion(elemQ);
		
		elemQues = questions.getElementaryQuestions();
		assertTrue(elemQues.contains(elemQ));
	}

	/**
	 * Test to determine if the addAdvancedQuestion() method adds the question to the list
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
		
		List<Question> advanQs = questions.getAdvancedQuestions();
		assertFalse(advanQs.contains(advQ));
		questions.addAdvancedQuestion(advQ);
		
		advanQs = questions.getAdvancedQuestions();
		assertTrue(advanQs.contains(advQ));
	}

	/**
	 * Test to determine if the getStandardQuestions() method returns all the standard questions
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
	 * Test to determine if the getStandardQuestions() method returns all the elementary questions
	 */
	@Test
	public void testGetElementaryQuestions() {
		List<Question> elemQuests = questions.getElementaryQuestions();
		assertEquals("Here is a hint. The correct answer is d.", 
				((ElementaryQuestion) elemQuests.get(0)).getHint());
		assertEquals(ELEM_SIZE, elemQuests.size());
	}

	/**
	 * Test to determine if the getStandardQuestions() method returns all the advanced questions
	 */
	@Test
	public void testGetAdvancedQuestions() {
		List<Question> advQuests = questions.getAdvancedQuestions();
		assertEquals("Great work!", ((AdvancedQuestion) advQuests.get(0)).getComment());
		assertEquals(ADV_SIZE, advQuests.size());
	}

}
