/**
 * 
 */
package edu.ncsu.csc216.book_quiz.ui;

import edu.ncsu.csc216.book_quiz.quiz.BookQuiz;
import edu.ncsu.csc216.book_quiz.quiz.QuizMaster;
import edu.ncsu.csc216.book_quiz.util.EmptyQuestionListException;
import edu.ncsu.csc216.question_library.AdvancedQuestion;
import edu.ncsu.csc216.question_library.ElementaryQuestion;
import edu.ncsu.csc216.question_library.Question;
import edu.ncsu.csc216.question_library.QuestionException;
import edu.ncsu.csc216.question_library.StandardQuestion;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * The graphical user interface for the project.
 * @author AlexiaMoore
 *
 */
public class BookQuizGUI extends JFrame implements ActionListener {
	
	//Backend model
	private QuizMaster quizMaster;
	
	//Parameters for component sizes and spacings
	private static final int FRAME_WIDTH = 700; 
	private static final int FRAME_HEIGHT = 350; 
	private static final int FIELD_WIDTH = 20;

	
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
	private static final String ERROR = "Error";
	private static final String[] LABEL_ANSWER_CHOICES = {"A", "B", "C", "D"};
	private static final String[] LABEL_QUESTION_TYPE = {"Easy Question", "Standard Question", "Advanced Question"};
	
	//Buttons, combo box and radio box
	private JButton btnSubmit = new JButton(SUBMIT);
	private JButton btnNext = new JButton(NEXT_Q);
	private JButton btnDone = new JButton(DONE);
	private JButton btnDone2 = new JButton(DONE);
	private JButton btnQuit = new JButton(QUIT);
	private JButton btnQuit2 = new JButton(QUIT);
	private JButton btnQuit3 = new JButton(QUIT);
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
	private ButtonGroup group = new ButtonGroup();
	
	//Labels and combo
	private JLabel lblQuestionType = new JLabel("Question Type:");
	private JLabel lblQuestion = new JLabel("Question:");
	private JLabel lblChoiceA = new JLabel("Choice A:");
	private JLabel lblChoiceB = new JLabel("Choice B:");
	private JLabel lblChoiceC = new JLabel("Choice C:");
	private JLabel lblChoiceD = new JLabel("Choice D:");
	private JLabel lblAnswer = new JLabel("Answer:");
	private JLabel lblComment = new JLabel();
	private JLabel lblQuizQ = new JLabel();
	
	//Text fields
	private JTextField txtQ = new JTextField(FIELD_WIDTH);
	private JTextField txtChoiceA = new JTextField(FIELD_WIDTH);
	private JTextField txtChoiceB = new JTextField(FIELD_WIDTH);
	private JTextField txtChoiceC = new JTextField(FIELD_WIDTH);
	private JTextField txtChoiceD = new JTextField(FIELD_WIDTH);
	
	//Organizational and alignment boxes and panels
	private JPanel pnlMainPage = new JPanel(new FlowLayout());
	private JPanel pnlAddQFull = new JPanel(new BorderLayout());
	private JPanel pnlAddPage = new JPanel(new GridLayout(8, 2));
	private JPanel pnlQuizPage = new JPanel(new BorderLayout());
	private JPanel pnlQuizQAndAs = new JPanel(new GridLayout(6, 1));
	private JPanel pnlAddWriteBtns = new JPanel(new FlowLayout());
	private JPanel pnlDoneQuitBtns = new JPanel(new FlowLayout());
	private JPanel pnlQuizPgBottom = new JPanel(new FlowLayout());
	private JPanel bottom = new JPanel(new GridLayout(1, 2));
	
	
	
	
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
		} catch (Exception e){
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
		}
	} 
	
	/**
	 * 
	 * @param ae
	 */
	public void actionPerformed(ActionEvent ae){
		//Add questions button
		if (ae.getSource().equals(btnAddQs)){
			setContentPane(pnlAddQFull);
			invalidate();
			validate();
		}

		JTextField choiceA = null;
		JTextField choiceB = null;
		JTextField choiceC = null;
		JTextField choiceD = null;
		JTextField question = null;
		String answer = null;
		String questionType = "StandardQuestion";
		if (ae.getSource().equals(txtChoiceA)){
			choiceA = (JTextField)ae.getSource();
		}
		if (ae.getSource().equals(txtChoiceB)){
			choiceB = (JTextField)ae.getSource();
		}
		if (ae.getSource().equals(txtChoiceC)){
			choiceC = (JTextField)ae.getSource();
		}
		if (ae.getSource().equals(txtChoiceD)){
			choiceD = (JTextField)ae.getSource();
		}
		if (ae.getSource().equals(txtQ)){
			question = (JTextField)ae.getSource();
		}
		if (ae.getSource().equals(cmbAnswerChoices)){
			answer = ae.getSource().toString();
		}
		if (ae.getSource().equals(cmbQuestionType)){
			String type = ae.getSource().toString();
			if (type.equals("Standard Question")){
				questionType = "StandardQuestion";
			}
			if (type.equals("Elementary Question")){
				questionType = "ElementaryQuestion";
			}
			if (type.equals("Advanced Question")){
				questionType = "AdvancedQuestion";
			}
		}
		if (ae.getSource().equals(btnAdd)){
			if (questionType.equals("StandardQuestion")){
				String[] choices = {choiceA.getText(), choiceB.getText(), choiceC.getText(), choiceD.getText()};
				quizMaster.addStandardQuestion(question.getText(), choices, answer);
			}
		}

			

		
		//Quit button
		if (ae.getSource().equals(btnQuit)){
			stopExecution();
		}
		if (ae.getSource().equals(btnQuit2)){
			stopExecution();
		}
		if (ae.getSource().equals(btnQuit3)){
			stopExecution();
		}
		
		//Book Quiz button
		if (ae.getSource().equals(btnQuiz)){
			setContentPane(pnlQuizPage);
			invalidate();
			validate();
		}
		
		String quizAnswer = "";
		if (btn1.isSelected()){
			quizAnswer = "a";
		}
		if (btn2.isSelected()){
			quizAnswer = "b";
		}
		if (btn3.isSelected()){
			quizAnswer = "c";
		}
		if (btn4.isSelected()){
			quizAnswer = "d";
		}
		if (ae.getSource().equals(btnSubmit)){
			processAnswer(quizAnswer);
		}
		
		if (ae.getSource().equals(btnNext)){
			loadQuiz();
		}
		
		if (ae.getSource().equals(btnDone2)){
			String message = "You answered " + quizMaster.getNumCorrectQuestions() +
					" questions correctly out of " + quizMaster.getNumAttemptedQuestions() + " attempts.";
			JOptionPane.showMessageDialog(new JFrame(), message, "Message", JOptionPane.INFORMATION_MESSAGE);
				setContentPane(pnlMainPage);
				invalidate();
				validate();
		}
	
	}
	
	/**
	 * Private method that creates the user interface.
	 * @throws EmptyQuestionListException 
	 */
	private void initializeUI() throws EmptyQuestionListException{
		//Initialize the main frame parameters.
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle(WINDOW_TITLE);
		setLocation(100, 100);
		
		//Include all visual components.
		setBordersAndPanels();
		mainWindow.add(pnlMainPage, BorderLayout.NORTH);
		
		//Enable buttons to respond to events.
		addListeners();
		
		addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				stopExecution();
			}
		});   	
	}
	
	/**
	 * Private method that adds action listeners to all the buttons.
	 */
	private void addListeners(){
		btnAddQs.addActionListener(this);
		btnQuit.addActionListener(this);
		btnQuit2.addActionListener(this);
		btnQuit3.addActionListener(this);
		btnQuiz.addActionListener(this);
		btnAdd.addActionListener(this);
		btnDone.addActionListener(this);
		btnDone2.addActionListener(this);
		btnNext.addActionListener(this);
		btnOk.addActionListener(this);
		btnSubmit.addActionListener(this);
		btnWrite.addActionListener(this);
		txtChoiceA.addActionListener(this);
		txtChoiceB.addActionListener(this);
		txtChoiceC.addActionListener(this);
		txtChoiceD.addActionListener(this);
		txtQ.addActionListener(this);
		cmbAnswerChoices.addActionListener(this);
		cmbQuestionType.addActionListener(this);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		
		
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
		pnlAddPage.add(lblQuestionType);
		pnlAddPage.add(cmbQuestionType);
		pnlAddPage.add(lblQuestion);
		pnlAddPage.add(txtQ);
		pnlAddPage.add(lblChoiceA);
		pnlAddPage.add(txtChoiceA);
		pnlAddPage.add(lblChoiceB);
		pnlAddPage.add(txtChoiceB);
		pnlAddPage.add(lblChoiceC);
		pnlAddPage.add(txtChoiceC);
		pnlAddPage.add(lblChoiceD);
		pnlAddPage.add(txtChoiceD);
		pnlAddPage.add(lblAnswer);
		pnlAddPage.add(cmbAnswerChoices);
		pnlAddWriteBtns.add(btnAdd);
		pnlAddWriteBtns.add(btnWrite);
		pnlDoneQuitBtns.add(btnDone);
		pnlDoneQuitBtns.add(btnQuit2);
		pnlAddQFull.add(pnlAddPage, BorderLayout.CENTER);
		bottom.add(pnlAddWriteBtns);
		bottom.add(pnlDoneQuitBtns);
		pnlAddQFull.add(bottom, BorderLayout.SOUTH);

		
		//Set up quiz page panels
		lblQuizQ.setText(quizMaster.getCurrentQuestionText());
		pnlQuizQAndAs.add(lblQuizQ);
		String[] btns = quizMaster.getCurrentQuestionChoices();
		btn1.setText(btns[0]);
		btn2.setText(btns[1]);
		btn3.setText(btns[2]);
		btn4.setText(btns[3]);
		group.add(btn1);
		group.add(btn2);
		group.add(btn3);
		group.add(btn4);
		pnlQuizQAndAs.add(btn1);
		pnlQuizQAndAs.add(btn2);
		pnlQuizQAndAs.add(btn3);
		pnlQuizQAndAs.add(btn4);
		pnlQuizQAndAs.add(lblComment);
		pnlQuizPage.add(pnlQuizQAndAs, BorderLayout.CENTER);
		pnlQuizPgBottom.add(btnSubmit);
		pnlQuizPgBottom.add(btnNext);
		pnlQuizPgBottom.add(btnDone2);
		pnlQuizPgBottom.add(btnQuit3);
		pnlQuizPage.add(pnlQuizPgBottom, BorderLayout.SOUTH);
		btn1.setSelected(true);
		
	}
	
	private void loadQuiz() {
		String[] choices = null;
		try{
			lblQuizQ.setText(quizMaster.getCurrentQuestionText());
			choices = quizMaster.getCurrentQuestionChoices();
		} catch (EmptyQuestionListException e){
			
		}
		
		btn1.setText(choices[0]);
		btn2.setText(choices[1]);
		btn3.setText(choices[2]);
		btn4.setText(choices[3]);
		btn1.setSelected(true);
		lblComment.setText("");
		setContentPane(pnlQuizPage);
		invalidate();
		validate();
		btnSubmit.setEnabled(true);
		
	}
	
	private void processAnswer(String answer){
		try{
			lblComment.setText(quizMaster.processAnswer(answer));
		} catch (EmptyQuestionListException e){
			
		}
		setContentPane(pnlQuizPage);
		invalidate();
		validate();
		btnSubmit.setEnabled(false);
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
