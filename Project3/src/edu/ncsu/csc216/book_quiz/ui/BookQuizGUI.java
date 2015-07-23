/**
 * 
 */
package edu.ncsu.csc216.book_quiz.ui;

import edu.ncsu.csc216.book_quiz.quiz.BookQuiz;
import edu.ncsu.csc216.book_quiz.quiz.QuizMaster;
import edu.ncsu.csc216.book_quiz.util.EmptyQuestionListException;
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
	private static final String[] LABEL_QUESTION_TYPE = {"Easy Question", "Standard Question", "Advanced Question"};
	
	//Buttons, combo box and radio box
	private JButton btnSubmit = new JButton(SUBMIT);
	private JButton btnNext = new JButton(NEXT_Q);
	private JButton btnDone = new JButton(DONE);
	private JButton btnQuit = new JButton(QUIT);
	private JButton btnAddQs = new JButton(ADD_QS);
	private JButton btnQuiz = new JButton(QUIZ);
	private JButton btnAdd = new JButton(ADD);
	private JButton btnWrite = new JButton(WRITE);
	private JButton btnOk = new JButton(OK);
	private JComboBox<String> cmbAnswerChoices = new JComboBox<String>(LABEL_ANSWER_CHOICES);
	private JComboBox<String> cmbQuestionType = new JComboBox<String>(LABEL_QUESTION_TYPE);
	private JRadioButton btn1 = new JRadioButton();
	private JRadioButton btn2 = new JRadioButton();
	private JRadioButton btn3 = new JRadioButton();
	private JRadioButton btn4 = new JRadioButton();
	
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
	private JPanel pnlMainPage = new JPanel(new FlowLayout());
	private JPanel pnlAddPage = new JPanel(new BorderLayout());
	private JPanel pnlQuizPage = new JPanel();
	private JPanel pnlQuizQAndAs = new JPanel(new GridLayout(6, 1));
	private JPanel pnlQuizPgBtns = new JPanel(new FlowLayout());
	private JPanel pnlAddPgTxtLbls = new JPanel(new GridLayout(7, 1));
	private JPanel pnlAddPgInputs = new JPanel(new GridLayout(7, 1));
	private JPanel pnlAddWriteBtns = new JPanel(new FlowLayout());
	private JPanel pnlDoneQuitBtns = new JPanel(new FlowLayout());
	private JPanel pnlAddPgBottom = new JPanel(new FlowLayout());
	private CardLayout mainCardLayout = new CardLayout(1, 1);
	
	
	
	//Main window
	private Container mainWindow = getContentPane();
	
	
	
	
	
	public BookQuizGUI(String file) throws QuestionException, EmptyQuestionListException{
		try{
			if (file == null){
				String userPickFilename = null;
				JFileChooser fc = new JFileChooser(".");
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int returnVal = fc.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION){
					userPickFilename = fc.getSelectedFile().getName();
				}
				quizMaster = new BookQuiz(userPickFilename);
			}
			else{
				quizMaster = new BookQuiz(file);
			}
			initializeUI();
			this.setVisible(true);
		} catch (IllegalArgumentException e){
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
		}
	} 
	
	/**
	 * 
	 * @param ae
	 */
	public void actionPerformed(ActionEvent ae){
	
	}
	
	/**
	 * Private method that creates the user interface.
	 * @throws EmptyQuestionListException 
	 */
	private void initializeUI() throws EmptyQuestionListException{
		//Initialize the main frame parameters.
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle(WINDOW_TITLE);
		
		//Include all visual components.
		setBordersAndPanels();
		mainWindow.add(pnlMainPage, BorderLayout.NORTH);
		
		//Enable buttons to respond to events.
		addListeners();
	}
	
	/**
	 * Private method that adds action listeners to all the buttons.
	 */
	private void addListeners(){
		
		
	}
	
	/**
	 * Private method that adds most of the components to the interface and formats them appropriately
	 * @throws EmptyQuestionListException 
	 */
	private void setBordersAndPanels() throws EmptyQuestionListException{
		//Set up main page buttons
		pnlMainPage.add(btnAddQs);
		pnlMainPage.add(btnQuiz);
		pnlMainPage.add(btnQuit);
		
		//Set up add page panels
		pnlAddPgTxtLbls.add(lblQuestionType);
		pnlAddPgTxtLbls.add(lblQuestion);
		pnlAddPgTxtLbls.add(lblChoiceA);
		pnlAddPgTxtLbls.add(lblChoiceB);
		pnlAddPgTxtLbls.add(lblChoiceC);
		pnlAddPgTxtLbls.add(lblChoiceD);
		pnlAddPgTxtLbls.add(lblAnswer);
		pnlAddPage.add(pnlAddPgTxtLbls, BorderLayout.WEST);
		pnlAddPgInputs.add(cmbQuestionType);
		pnlAddPgInputs.add(txtQ);
		pnlAddPgInputs.add(txtChoiceA);
		pnlAddPgInputs.add(txtChoiceB);
		pnlAddPgInputs.add(txtChoiceC);
		pnlAddPgInputs.add(txtChoiceD);
		pnlAddPgInputs.add(cmbAnswerChoices);
		pnlAddPage.add(pnlAddPgInputs, BorderLayout.EAST);
		pnlAddPgBottom.add(pnlAddWriteBtns);
		pnlAddPgBottom.add(pnlDoneQuitBtns);
		pnlAddPage.add(pnlAddPgBottom, BorderLayout.SOUTH);
		
		//Set up quiz page panels
		pnlQuizQAndAs.add(new JLabel(quizMaster.getCurrentQuestionText()));
		String[] btns = quizMaster.getCurrentQuestionChoices();
		btn1.setText(btns[0]);
		btn2.setText(btns[1]);
		btn3.setText(btns[2]);
		btn4.setText(btns[3]);
		pnlQuizQAndAs.add(btn1, 1);
		pnlQuizQAndAs.add(btn2, 2);
		pnlQuizQAndAs.add(btn3, 3);
		pnlQuizQAndAs.add(btn4, 4);
		pnlQuizPage.add(pnlQuizQAndAs, BorderLayout.EAST);
		
		
		
		//Add main page, quiz, and add question panels to the UI.
		pnlCenter.setLayout(mainCardLayout);
		
	}
	
	/**
	 * Private method that sets up the main window panel
	 */
	private void setUpMainWindowPanel(){
		pnlMainPage.setLayout(new BoxLayout(pnlMainPage, getDefaultCloseOperation()));
		
		
	}
	
	/**
	 * Private method that sets up the addQuestionsPanel
	 */
	private void setUpAddQuestionsPanel(){
		
	}
	
	/**
	 * Private method that sets up the bookQuiz panel
	 */
	private void setUpBookQuizPanel(){
		
	}
	
	/**
	 * Private method that exits the program
	 */
	private static void stopExecution(){
		System.exit(0);
	}
	
	/**
	 * Starts the program.
	 * @param args command line args
	 * @throws QuestionException 
	 * @throws EmptyQuestionListException 
	 */
	public static void main(String[] args) throws QuestionException, EmptyQuestionListException{
		try{
			if (args.length > 0){
				new BookQuizGUI(args[0]);
			}
			else{
				new BookQuizGUI(null);
			}
		} catch (IllegalArgumentException e){
			JOptionPane.showMessageDialog(new JFrame(),  "Incorrect Question File Specified");
			stopExecution();
		} catch (QuestionException q){
			stopExecution();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
