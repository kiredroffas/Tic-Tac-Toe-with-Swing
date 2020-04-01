# Tic-Tac-Toe-with-Swing
* This program implements a Tic-Tac-Toe game where the user can play against the computer. This is done by using Swing to create a graphical interface, and using the Graphics class for drawing the board, markers, and lines through the winning set of markers. 
* On program startup the user can select their marker (X or O) and if they want to go first, and then press START to lock in their settings and start the game. 
* The text field below the START button will display all relevant information such as: which marker you chose, whose going first, when the game has started, whose turn it is, if a move isn't valid, if the user has won/lost, if the game is a draw, and if the game is reset. 
* Once START is clicked MyNewPanel's paint() class is called and the game board is drawn. The user can then click any empty space (if it is their turn) to place their marker in the square. The computer will make its turn whenever the user moves the mouse after clicking. 
* Once either the user or computer gets three squares in a row a winning line is drawn through them and the winner is declared. 
* The user can click the RESET button at any time to reset the settings to default, change any settings they wish, and click the START button to play again. 
* Error checking is in place to not let the user make more then one move before the computer can make a move, not let the user continue playing after the game has ended, and only allow marks in valid, empty squares.
## Swing Interface
* The actions of the program are controlled by the use of states. MainJFrame and MyNewPanel both have independent states that work together to produce the desired output. 
* MyNewPanel() is the same as JPanel(), but I make it my own by extending it so I can override the JPanel()'s paint method. This allows for the creation of lines, circles, and text on the JPanel based on the current state of the game.
### MainJFrame States:
* -1 = User clicks outside of the game board or on a already selected square, sends back to state 1
* 0 = Initial State, make all JFrame/JPanel components appear
* 1 = User makes a move, tic-tac-toe board appears, markers appear
* 2 = Computer makes a move, tic-tac-toe board appears, markers appear
* 3 = Somebody wins, announce winner and draw winning line
* 4 = The game is a DRAW
### MyNewPanel States: 
* 0 = The program has just started/been reset and START has not been pressed yet, makes welcome text and JPanel components appear
* 1 = START has been pressed and it is user's/computer's turn, makes game board and markers appear (along w/ other JPanel components)
* 2 = A winner has been detected, makes the game winning line appear (along w/ JPanel components,board, and markers)