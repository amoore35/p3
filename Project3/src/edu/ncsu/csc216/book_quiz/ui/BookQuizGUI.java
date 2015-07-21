/**
 * 
 */
package edu.ncsu.csc216.book_quiz.ui;

import edu.ncsu.csc216.book_quiz.quiz.BookQuiz;

import edu.ncsu.csc216.book_quiz.quiz.QuizMaster;
import edu.ncsu.csc216.question_library.QuestionException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The graphical user interface for the project.
 * @author AlexiaMoore
 *
 */
public class BookQuizGUI extends JFrame {
	
	//Backend model
	private QuizMaster quizMaster;
	
	//Parameters for component sizes and spacings
	private static final int FRAME_WIDTH = 500; 
	private static final int FRAME_HEIGHT = 600; 
	private static final int FIELD_WIDTH = 20;
	private static final int VERTICAL_SPACER = 5;
	private static final int TOP_PAD = 5;
	private static final int LEFT_PAD = 10;
	private static final int RIGHT_PAD = 10;
	private static final int BOTTOM_PAD = 10;
	
	//Window titles
	private static final String WINDOW_TITLE = "Book Quiz";
	
	//Buttons, combo and text field strings
	private static final String SUBMIT = "Submit Answer";
	private static final String NEXT_Q = "Next Question";
	private static final String DONE = "Done";
	private static final String QUIT = "Quit";
	private static final String ADD_QS = "Add Questions";
	private static final String QUIZ = "Take Book Quiz";
	private static final String ADD = "Add";
	private static final String WRITE = "Write All";
	private static final String OK = "OK";
	private static final String MESSAGE = "Message";
	private static final String ERROR = "Error";
	private static final String[] LABEL_ANSWER_CHOICES = {"A", "B", "C", "D"};
	
	//Buttons and combo box
	private JButton btnSubmit = new JButton(SUBMIT);
	private JButton btnNext = new JButton(NEXT_Q);
	private JButton btnDone = new JButton(DONE);
	private JButton btnQuit = new JButton(QUIT);
	private JButton btnAddQs = new JButton(ADD_QS);
	private JButton btnQuiz = new JButton(QUIZ);
	private JButton btnAdd = new JButton(ADD);
	private JButton btnWrite = new JButton(WRITE);
	private JButton btnOk = new JButton(OK);
	
	//Labels and combo
	private JLabel lblQuestionType = new JLabel("Question Type:");
	private JLabel lblQuestion = new JLabel("Question:");
	private JLabel lblChoiceA = new JLabel("Choice A:");
	private JLabel lblChoiceB = new JLabel("Choice B:");
	private JLabel lblChoiceC = new JLabel("Choice C:");
	private JLabel lblChoiceD = new JLabel("Choice D:");
	private JLabel lblAnswer = new JLabel("Answer:");
	
	//Text fields
	private JTextField txtQ = new JTextField(FIELD_WIDTH);
	private JTextField txtChoiceA = new JTextField(FIELD_WIDTH);
	private JTextField txtChoiceB = new JTextField(FIELD_WIDTH);
	private JTextField txtChoiceC = new JTextField(FIELD_WIDTH);
	private JTextField txtChoiceD = new JTextField(FIELD_WIDTH);
	
	//Organizational and alignment boxes and panels
	private JPanel pnlCenter = new JPanel();
	
	
	
	
	
	public BookQuizGUI(String file) throws QuestionException{
		quizMaster = new BookQuiz(file);
	}

}
