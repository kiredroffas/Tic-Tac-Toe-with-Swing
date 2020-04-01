/* Erik Safford
 * Friendly Tic-Tac-Toe
 * MAY 2018
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionAdapter;

public class MainJFrame extends JFrame {

	private MyNewPanel contentPane;  //MyNewPanel was JPanel, we use our own MyNewPanel that extends JPanel in order to use our own paint() method
	private final ButtonGroup buttonGroup = new ButtonGroup();  //Group the two radio buttons together to choose X/O
	private JTextField textField;   //Text field to show output to user
	private String myString;  //String to output system responses to user
	private int userSymbol = 0; //0 is X, 1 is O
	private int userFirst = 0;  //0 is computer first, 1 is user first
	private int button1 = 0;  //Button flags to keep track of where user has clicked, correspond to 9 squares of tic-tac-toe board
	private int button2 = 0;  //        1 2 3
	private int button3 = 0;  //        4 5 6
	private int button4 = 0;  //        7 8 9
	private int button5 = 0;
	private int button6 = 0;
	private int button7 = 0;
	private int button8 = 0;
	private int button9 = 0;
	private int compButton1 = 0;  //Button flags to keep track computers moves, correspond to 9 squares of tic-tac-toe board
	private int compButton2 = 0;  //    1 2 3
	private int compButton3 = 0;  //    4 5 6 
	private int compButton4 = 0;  //    7 8 9
	private int compButton5 = 0;
	private int compButton6 = 0;
	private int compButton7 = 0;
	private int compButton8 = 0;
	private int compButton9 = 0;
	private int state; //State flag to keep track of what the program should be doing, independent of the int state in MyNewPanel
	private int count = 0; //This keeps track of how many buttons are in use, once count = 9 the game is a DRAW

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainJFrame frame = new MainJFrame();  //This makes a JFrame
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainJFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 350);
		contentPane = new MyNewPanel();    //Create a new JPanel using MyNewPanel so we can override the JPanel paint() class
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)   //Whenever the JPanel is clicked 
			{
				if(state == -1)  //refresh state, go here if someone clicks on the same square twice
				{
					myString = "Try Again, click a valid square"; //Tell user invalid click
					textField.setText(myString);  //Set the text in the text field
					state = 1;                    //Send back to state 1 (user's turn)
					contentPane.repaint();        //Refresh the JPanel so our components appear correctly
				}
				else if(state == 0)  //Initial state, program just started or RESET button has been pressed
				{
					contentPane.repaint();   //Refresh the JPanel so our components appear correctly     
				}
				else if(state == 1)  //User's turn, user selected to go first or the computer has just made its move
				{	
					myString = "Comp is thinking... (move to continue)"; //Text doesn't appear until state = 2, so we put comp text here
					textField.setText(myString);                          
					contentPane.repaint();
					int lastX = e.getX();  //Get the x-coordinate of the user's click
					int lastY = e.getY();  //Get the y-coordinate of the user's click
					userMove(lastX,lastY); //userMove() makes the appropriate marker appear in a clicked empty square
					contentPane.repaint(); //Refresh	
				}
			}
		});
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {  //Whenever the mouse is moved on the JPanel
			@Override
			public void mouseMoved(MouseEvent arg0) 
			{
				int win = didCompWin();  //didCompWin checks current active compButtons to see if computer has won
				int win2 = didUserWin(); //didUserWin checks current active Buttons to see if user has won
				if(win == 1 || win2 == 1)  //If user or computer has won
				{
					state = 3;   //Go to win state
					contentPane.repaint();	//Refresh
				}
				if(state == 2)  //If it is the computer's turn, after the user has made a move
				{
						myString = "Your turn, click any empty square!";  //Text doesn't show up until state = 1 so we put it here
						textField.setText(myString);
						contentPane.repaint();
						compMove();  //compMove() checks to see which user buttons are active and makes a move accordingly
						if(count < 9)  //If there is still empty square on the game board
						{
							state = 1; //Go to user's turn
							contentPane.repaint(); //Refresh
						}
						else
						{
							state = 4;  //Go to DRAW state
						}	
				}
				else if(state == 3) //If somebody wins (gets 3 in a row)
				{
					if(win == 1)  //If computer wins
					{
						myString = "You lose! (Click Reset to play again)"; //Set text string to loss message
						contentPane.setState(2);  //Set state to 2 in MyNewPanel so paint() generates winning line
						contentPane.repaint();  //Refresh
					}
					else  //if win2 == 1, user wins
					{
						myString = "You win! (Click Reset to play again)";  //Set text string to victory message
						contentPane.setState(2);  //Set state to 2 in MyNewPanel so paint() generates winning line
						contentPane.repaint();  //Refresh
					}
					textField.setText(myString);  //Set text field to victory/loss message
					contentPane.repaint();        //Refresh
				}
				else if(state == 4)  //If count = 9 the game is a DRAW, dead state
				{
					myString = "DRAW";    ///Set text field to DRAW 
					textField.setText(myString);
					contentPane.repaint();
				}
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCheckBox WhoGoesFirst = new JCheckBox("Go first?");  //Creates a check box to determine if user or computer goes first
		WhoGoesFirst.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)  //When the check box is clicked 
			{
				if(state == 0)  //If the START button has not been pressed yet
				{
					if(WhoGoesFirst.isSelected() == true)  //If the check box is checked
					{
						userFirst = 1;   //Set userFirst flag to 1 (user goes first)
						myString = "User goes first";  
						textField.setText(myString);
						
					}
					else  //If the check box in unchecked
					{
						userFirst = 0;    //Set userFirst flag to 0 (computer goes first)
						myString = "Comp goes first";
						textField.setText(myString);
					}
				}
				else   //If state is not state 0 (game has started), we don't want user to change settings in game
				{
					myString = "Your already in a game!";
					textField.setText(myString);
				}
			}
		});
		WhoGoesFirst.setBounds(440, 9, 77, 23);
		contentPane.add(WhoGoesFirst);
		
		JRadioButton buttonX = new JRadioButton("X");  //This creates a radio button for the user to select X
		buttonX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{	
				if(state == 0)  //If START button has not been pressed yet
				{
					userSymbol = 0;  //Set userSymbol flag to 0 (X)
					myString = "User chooses X";
					textField.setText(myString);	
				}
				else  //if not in state 0, we don't want this button to do anything
				{
					myString = "Your already in a game!";
					textField.setText(myString);
				}
			}
		});
		buttonGroup.add(buttonX);
		buttonX.setBounds(6, 34, 109, 23);
		contentPane.add(buttonX);
		
		JRadioButton buttonO = new JRadioButton("O");  //Creates a radio button for the user to select O
		buttonO.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(state == 0)  //If START button has not been pressed yet
				{
					userSymbol = 1;  //Set userSymbol flag to 1 (O)
					myString = "User chooses O";
					textField.setText(myString);
				}
				else  //if not in state 0, we don't want this button to do anything
				{
					myString = "Your already in a game!";
					textField.setText(myString);
				}
			}
		});
		buttonGroup.add(buttonO);
		buttonO.setBounds(6, 60, 109, 23);
		contentPane.add(buttonO);
		
		JLabel lblChooseXOr = new JLabel("Choose X or O");  //This makes a text label appear above the X/O radio buttons
		lblChooseXOr.setBounds(6, 11, 84, 19);
		contentPane.add(lblChooseXOr);
		
		textField = new JTextField();  //This creates a text field that we can use to show text output to the user
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(159, 38, 235, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnStart = new JButton("START");  //This makes a START button to lock in the user's settings and start the game
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) //When the START button is clicked
			{
				if(state == 0)  //if the START button hasen't been clicked yet
				{
					contentPane.setuserSymbol(userSymbol);  //Set userSymbol in MyNewPanel so paint() knows which marker to print for user
					if(userFirst == 1)  //If the user selected to go first
					{
						state = 1;  //Go to user move state
						contentPane.setState(1);  //Set state = 1 in MyNewPanel so paint() makes the game board/markers appear
						myString = "You go first! (click to begin)";
						textField.setText(myString);
					}
					if(userFirst == 0)  //If the computer goes first
					{
						state = 2;  //Go to computer move state
						contentPane.setState(1);  //Set state = 1 in MyNewPanel so paint() makes the game board/markers appear
						myString = "AI Sequence Initiated... (move to begin)";
						textField.setText(myString);
					}
					contentPane.repaint();
					//START doesn't do anything outside of state = 0
				}
				
			}
		});
		btnStart.setBounds(233, 9, 89, 23);
		contentPane.add(btnStart);
		
		JButton btnReset = new JButton("Reset");  //This creates a RESET button to reset the selected settings and game to default
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)  //When the RESET button is clicked
			{
				state = 0;                       //Set all MainJFrame and MyNewPanel flags to 0
				contentPane.setState(state);
				button1 = 0;
				contentPane.setButton1(button1);
				button2 = 0;
				contentPane.setButton2(button2);
				button3 = 0;
				contentPane.setButton3(button3);
				button4 = 0;
				contentPane.setButton4(button4);
				button5 = 0;
				contentPane.setButton5(button5);
				button6 = 0;
				contentPane.setButton6(button6);
				button7 = 0;
				contentPane.setButton7(button7);
				button8 = 0;
				contentPane.setButton8(button8);
				button9 = 0;
				contentPane.setButton9(button9);
				compButton1 = 0;
				contentPane.setCompButton1(compButton1);
				compButton2 = 0;
				contentPane.setCompButton2(compButton2);
				compButton3 = 0;
				contentPane.setCompButton3(compButton3);
				compButton4 = 0;
				contentPane.setCompButton4(compButton4);
				compButton5 = 0;
				contentPane.setCompButton5(compButton5);
				compButton6 = 0;
				contentPane.setCompButton6(compButton6);
				compButton7 = 0;
				contentPane.setCompButton7(compButton7);
				compButton8 = 0;
				contentPane.setCompButton8(compButton8);
				compButton9 = 0;
				contentPane.setCompButton9(compButton9);
				count = 0;
				myString = "RESET";
				textField.setText(myString);
				contentPane.repaint();
			}
		});
		btnReset.setBounds(10, 277, 89, 23);
		contentPane.add(btnReset);
	}
	public void userMove(int lastX,int lastY)  //userMove() is passed the x,y coordinates of the user's click
	{	                                                        // 1 2 3
		if(count < 9)   //If all 9 buttons aren't pressed yet   // 4 5 6
		{	                                                    // 7 8 9
			if(lastX >= 170 && lastX <= 240 && lastY >= 90 && lastY <= 140)   //If user clicked in square 1
			{	
				if(button1 == 0 && compButton1 == 0)  //If there isn't already a mark in the space, mark the space for user
				{
					button1 = 1;  //Set user button 1 flag
					contentPane.setButton1(button1);  //Set in MyNewPanel to make marker appear in corresponding square
					state = 2;    //Go to computer's move state
					count++;      //Increment total marker count
				}
				else  //else if there is already a mark in the space, go to refresh state  
				{
					state = -1;
				}
			}
			else if(lastX >= 240 && lastX <= 310 && lastY >= 90 && lastY <= 140)  //If user clicked in square 2
			{
				if(button2 == 0 && compButton2 == 0)
				{
					button2 = 1;
					contentPane.setButton2(button2);
					state = 2;
					count++;
				}
				else
				{
					state = -1;
				}
			}
			else if(lastX >= 310 && lastX <= 370 && lastY >= 90 && lastY <= 140) //If user clicked in square 3
			{
				if(button3 == 0 && compButton3 == 0)
				{
					button3 = 1;
					contentPane.setButton3(button3);
					state = 2;
					count++;
				}
				else
				{
					state = -1;
				}
			}
			else if(lastX >= 170 && lastX <= 240 && lastY >= 140 && lastY <= 210)  //If user clicked in square 4
			{
				if(button4 == 0 && compButton4 == 0)
				{
					button4 = 1;
					contentPane.setButton4(button4);
					state = 2;
					count++;
				}
				else
				{
					state = -1;
				}
			}
			else if(lastX >= 240 && lastX <= 310 && lastY >= 140 && lastY <= 210) //If user clicked in square 5
			{
				if(button5 == 0 && compButton5 == 0)
				{
					button5 = 1;
					contentPane.setButton5(button5);
					state = 2;
					count++;
				}
				else
				{
					state = -1;
				}
			}
			else if(lastX >= 310 && lastX <= 370 && lastY >= 140 && lastY <= 210) //If user clicked in square 6
			{
				if(button6 == 0 && compButton6 == 0)
				{
					button6 = 1;
					contentPane.setButton6(button6);
					state = 2;
					count++;
				}
				else
				{
					state = -1;
				}
			}
			else if(lastX >= 170 && lastX <= 240 && lastY >= 190 && lastY <= 280) //If user clicked in square 7
			{
				if(button7 == 0 && compButton7 == 0)
				{
					button7 = 1;
					contentPane.setButton7(button7);
					state = 2;
					count++;
				}
				else
				{
					state = -1;
				}
			}
			else if(lastX >= 240 && lastX <= 310 && lastY >= 190 && lastY <= 280) //If user clicked in square 8
			{
				if(button8 == 0 && compButton8 == 0)
				{
					button8 = 1;
					contentPane.setButton8(button8);
					state = 2;
					count++;
				}
				else
				{
					state = -1;
				}
			}
			else if(lastX >= 310 && lastX <= 370 && lastY >= 190 && lastY <= 280) //If user clicked in square 9
			{
				if(button9 == 0 && compButton9 == 0)
				{
					button9 = 1;
					contentPane.setButton9(button9);
					state = 2;
					count++;
				}
				else
				{
					state = -1;
				}
			}
			else  //If the user clicks outside of the game board, or on a already selected square
			{
					myString = "Click an open square!";
					textField.setText(myString);
			}
		}
		else   //If all 9 buttons have been pressed, then the game is a DRAW
		{
			state = 4;  //Go to draw state
		}
	}
	public void compMove()  //compMove() performs a predetermined (friendly) set of moves based on the user's and computer's button flags
	{	
		if(count < 9)  //As long as not all squares have been clicked
		{
			if(button5 == 0 && compButton5 == 0)  //Mark square 5
			{
				compButton5 = 1;
				contentPane.setCompButton5(compButton5);
				count++;
			}
			else if(button9 == 0 && compButton9 == 0)  //Mark square 9
			{
				compButton9 = 1;
				contentPane.setCompButton9(compButton9);
				count++;
			}
			else if(button1 == 0 && compButton1 == 0) //Mark square 1
			{
				compButton1 = 1;
				contentPane.setCompButton1(compButton1);
				count++;
			}
			else if(button3 == 0 && compButton3 == 0) //Mark square 3
			{
				compButton3 = 1;
				contentPane.setCompButton3(compButton3);
				count++;
			}
			else if(button7 == 0 && compButton7 == 0) //Mark square 7
			{
				compButton7 = 1;
				contentPane.setCompButton7(compButton7);
				count++;
			}
			else if(button4 == 0 && compButton4 == 0) //Mark square 4
			{
				compButton4 = 1;
				contentPane.setCompButton4(compButton4);
				count++;
			}
			else if(button6 == 0 && compButton6 == 0) //Mark square 6
			{
				compButton6 = 1;
				contentPane.setCompButton6(compButton6);
				count++;
			}
			else if(button2 == 0 && compButton2 == 0) //Mark square 2
			{
				compButton2 = 1;
				contentPane.setCompButton2(compButton2);
				count++;
			}
			else if(button8 == 0 && compButton8 == 0) //Mark square 8
			{
				compButton8 = 1;
				contentPane.setCompButton8(compButton8);
				count++;
			}
		}
		else  //If all 9 buttons are pressed, then it's a DRAW
		{
			state = 4;  //go to draw state
		}
	}
	public int didCompWin()  //didCompWin checks the compButton flags to see if they make any of 8 win conditions
	{
		if(compButton1 == 1 && compButton2 == 1 && compButton3 == 1)
		{
			return(1);  //1 = win
		}
		if(compButton4 == 1 && compButton5 == 1 && compButton6 == 1)
		{
			return(1);
		}
		if(compButton7 == 1 && compButton8 == 1 && compButton9 == 1)
		{
			return(1);
		}
		if(compButton1 == 1 && compButton4 == 1 && compButton7 == 1)
		{
			return(1);
		}
		if(compButton2 == 1 && compButton5 == 1 && compButton8 == 1)
		{
			return(1);
		}
		if(compButton3 == 1 && compButton6 == 1 && compButton9 == 1)
		{
			return(1);
		}
		if(compButton1 == 1 && compButton5 == 1 && compButton9 == 1)
		{
			return(1);
		}
		if(compButton3 == 1 && compButton5 == 1 && compButton7 == 1)
		{
			return(1);
		}
			return(0);  //0 = no detected win
	}
	public int didUserWin()  //didUserWin checks the user's button flags to see if they make any of 8 win conditions
	{
		if(button1 == 1 && button2 == 1 && button3 == 1)
		{
			return(1);  //1 = win
		}
		if(button4 == 1 && button5 == 1 && button6 == 1)
		{
			return(1);
		}
		if(button7 == 1 && button8 == 1 && button9 == 1)
		{
			return(1);
		}
		if(button1 == 1 && button4 == 1 && button7 == 1)
		{
			return(1);
		}
		if(button2 == 1 && button5 == 1 && button8 == 1)
		{
			return(1);
		}
		if(button3 == 1 && button6 == 1 && button9 == 1)
		{
			return(1);
		}
		if(button1 == 1 && button5 == 1 && button9 == 1)
		{
			return(1);
		}
		if(button3 == 1 && button5 == 1 && button7 == 1)
		{
			return(1);
		}
		return(0);  //0 = no win detected
	}
}
