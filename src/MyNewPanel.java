import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class MyNewPanel extends JPanel 
{
	private int userSymbol;   //Every time a flag is set in MainJFrame (with the exception of state), the flag is mirrored in MyNewPanel using setters
	private int button1 = 0;
	private int button2 = 0;
	private int button3 = 0;
	private int button4 = 0;
	private int button5 = 0;
	private int button6 = 0;
	private int button7 = 0;
	private int compButton1 = 0;
	private int compButton2 = 0;
	private int compButton3 = 0;
	private int compButton4 = 0;
	private int compButton5 = 0;
	private int compButton6 = 0;
	private int compButton7 = 0;
	private int compButton8 = 0;
	private int compButton9 = 0;
	private int button8 = 0;
	private int button9 = 0;
	private int state;
	
	public void setButton1(int button1) {
		this.button1 = button1;
	}
	public void setButton2(int button2) {
		this.button2 = button2;
	}
	public void setButton3(int button3) {
		this.button3 = button3;
	}
	public void setButton4(int button4) {
		this.button4 = button4;
	}
	public void setButton5(int button5)
	{
		this.button5 = button5;
	}
	public void setButton6(int button6)
	{
		this.button6 = button6;
	}
	public void setButton7(int button7) {
		this.button7 = button7;
	}
	public void setButton8(int button8) {
		this.button8 = button8;
	}
	public void setButton9(int button9) {
		this.button9 = button9;
	}
	public void setCompButton1(int compButton1) {
		this.compButton1 = compButton1;
	}
	public void setCompButton2(int compButton2) {
		this.compButton2 = compButton2;
	}
	public void setCompButton3(int compButton3) {
		this.compButton3 = compButton3;
	}
	public void setCompButton4(int compButton4) {
		this.compButton4 = compButton4;
	}
	public void setCompButton5(int compButton5) {
		this.compButton5 = compButton5;
	}
	public void setCompButton6(int compButton6) {
		this.compButton6 = compButton6;
	}
	public void setCompButton7(int compButton7) {
		this.compButton7 = compButton7;
	}
	public void setCompButton8(int compButton8) {
		this.compButton8 = compButton8;
	}
	public void setCompButton9(int compButton9) {
		this.compButton9 = compButton9;
	}
	public void setState(int state) {
		this.state = state;
	}
	public void setuserSymbol(int userSymbol)
	{
		this.userSymbol = userSymbol;
	}

	public void paint(Graphics g)  //This will override JPanel's paint method
	{
		super.paint(g);  //Calls original paint method in JPanel
		if(state == 0)  //If program has just started, or RESET button has been clicked
		{
			g.drawString("Welcome to Friendly Tic-Tac-Toe!",185,150);    //Draw welcome messages
			g.drawString("Choose X(default) or O and if you want to go first, then press START",90,170);
			g.drawString("Click Reset to reset settings to default and start over",140,190);
		}
		if(state == 1)  //Once user has locked in settings, this makes board and X/O 
		{
			playGame(g);
		}
		if(state == 2)  //This prints out the winning line when someone wins
		{
			playGame(g);  //this makes board and X/O
			g.setColor(new Color(255,0,0));  //Make winning line color red
			if(button1 == 1 && button2 == 1 && button3 == 1)   //Winning lines
			{
				g.fillRect(170, 112, 200, 2);  //123
			}
			if(compButton1 == 1 && compButton2 == 1 && compButton3 == 1)
			{
				g.fillRect(170, 112, 200, 2);  //123
			}
			if(button4 == 1 && button5 == 1 && button6 == 1)
			{
				g.fillRect(170, 175, 200, 2);  //456
			}
			if(compButton4 == 1 && compButton5 == 1 && compButton6 == 1)
			{
				g.fillRect(170, 175, 200, 2);  //456
			}
			if(button7 == 1 && button8 == 1 && button9 == 1)
			{
				g.fillRect(170, 242, 200, 2);  //789
			}
			if(compButton7 == 1 && compButton8 == 1 && compButton9 == 1)
			{
				g.fillRect(170, 242, 200, 2);  //789
			}
			if(button1 == 1 && button4 == 1 && button7 == 1)
			{
				g.fillRect(205, 90, 2, 180);  //147
			}
			if(compButton1 == 1 && compButton4 == 1 && compButton7 == 1)
			{
				g.fillRect(205, 90, 2, 180);  //147
			}
			if(button2 == 1 && button5 == 1 && button8 == 1)
			{
				g.fillRect(275, 90, 2, 180);  //258
			}
			if(compButton2 == 1 && compButton5 == 1 && compButton8 == 1)
			{
				g.fillRect(275, 90, 2, 180);  //258
			}
			if(button3 == 1 && button6 == 1 && button9 == 1)
			{
				g.fillRect(345, 90, 2, 180);  //369
			}
			if(compButton3 == 1 && compButton6 == 1 && compButton9 == 1)
			{
				g.fillRect(345, 90, 2, 180);  //369
			}
			if(button1 == 1 && button5 == 1 && button9 == 1)
			{
				g.drawLine(175,85,365,265);   //159
			}
			if(compButton1 == 1 && compButton5 == 1 && compButton9 == 1)
			{
				g.drawLine(175,85,365,265);   //159
			}
			if(button3 == 1 && button5 == 1 && button7 == 1)
			{
				g.drawLine(374, 93, 175, 265);  //357
			}
			if(compButton3 == 1 && compButton5 == 1 && compButton7 == 1)
			{
				g.drawLine(374, 93, 175, 265);  //357
			}
		}
	}
	public void playGame(Graphics g)  // this makes board and X/O
	{
		g.fillRect(310, 90, 3, 180);
		g.fillRect(240, 90, 3, 180);
		g.fillRect(170, 140, 200, 3);
		g.fillRect(170, 210, 200, 3);
		if(button1 == 1)
		{
			if(userSymbol == 0)
			{
				g.drawLine(180, 90,230,135);
				g.drawLine(180, 135,230,90);
			}
			if(userSymbol == 1)   //draw O
			{
				g.drawOval(180,85,50,50);
			}
		}
		if(button2 == 1)
		{
			if(userSymbol == 0)
			{
				g.drawLine(250, 90,300,135);
				g.drawLine(250, 135,300,90);
			}
			if(userSymbol == 1)   //draw O
			{
				g.drawOval(250,85,50,50);
			}
		}
		if(button3 == 1)
		{
			if(userSymbol == 0)
			{
				g.drawLine(320, 90,370,135);
				g.drawLine(320, 135,370,90);
			}
			if(userSymbol == 1)   //draw O
			{
				g.drawOval(320,85,50,50);
			}
		}
		if(button4 == 1)
		{
			if(userSymbol == 0)
			{
				g.drawLine(180, 150,230,200);
				g.drawLine(180, 200,230,150);
			}
			if(userSymbol == 1)   //draw O
			{
				g.drawOval(180,150,50,50);
			}
		}
		if(button5 == 1)
		{
			if(userSymbol == 0)
			{
				g.drawLine(250, 150,300,200);
				g.drawLine(250, 200,300,150);
			}
			if(userSymbol == 1)   //draw O
			{
				g.drawOval(250,150,50,50);
			}
		}
		if(button6 == 1)
		{
			if(userSymbol == 0)
			{
				g.drawLine(320, 150,370,200);
				g.drawLine(320, 200,370,150);
			}
			if(userSymbol == 1)   //draw O
			{
				g.drawOval(320,150,50,50);
			}
		}
		if(button7 == 1)
		{
			if(userSymbol == 0)
			{
				g.drawLine(180, 220,230,265);
				g.drawLine(180, 265,230,220);
			}
			if(userSymbol == 1)   //draw O
			{
				g.drawOval(180,215,50,50);
			}
		}
		if(button8 == 1)
		{
			if(userSymbol == 0)
			{
				g.drawLine(250, 220,300,265);
				g.drawLine(250, 265,300,220);
			}
			if(userSymbol == 1)   //draw O
			{
				g.drawOval(250,215,50,50);
			}
		}
		if(button9 == 1)
		{
			if(userSymbol == 0)
			{
				g.drawLine(320, 220,370,265);
				g.drawLine(320, 265,370,220);
			}
			if(userSymbol == 1)   //draw O
			{
				g.drawOval(320,215,50,50);
			}
		}
		if(compButton1 == 1)
		{
			if(userSymbol == 1)
			{
				g.drawLine(180, 90,230,135);
				g.drawLine(180, 135,230,90);
			}
			if(userSymbol == 0)   //draw O
			{
				g.drawOval(180,85,50,50);
			}
		}
		if(compButton2 == 1)
		{
			if(userSymbol == 1)
			{
				g.drawLine(250, 90,300,135);
				g.drawLine(250, 135,300,90);
			}
			if(userSymbol == 0)   //draw O
			{
				g.drawOval(250,85,50,50);
			}
		}
		if(compButton3 == 1)
		{
			if(userSymbol == 1)
			{
				g.drawLine(320, 90,370,135);
				g.drawLine(320, 135,370,90);
			}
			if(userSymbol == 0)   //draw O
			{
				g.drawOval(320,85,50,50);
			}
		}
		if(compButton4 == 1)
		{
			if(userSymbol == 1)
			{
				g.drawLine(180, 150,230,200);
				g.drawLine(180, 200,230,150);
			}
			if(userSymbol == 0)   //draw O
			{
				g.drawOval(180,150,50,50);
			}
		}
		if(compButton5 == 1)
		{
			if(userSymbol == 1)
			{
				g.drawLine(250, 150,300,200);
				g.drawLine(250, 200,300,150);
			}
			if(userSymbol == 0)   //draw O
			{
				g.drawOval(250,150,50,50);
			}
		}
		if(compButton6 == 1)
		{
			if(userSymbol == 1)
			{
				g.drawLine(320, 150,370,200);
				g.drawLine(320, 200,370,150);
			}
			if(userSymbol == 0)   //draw O
			{
				g.drawOval(320,150,50,50);
			}
		}
		if(compButton7 == 1)
		{
			if(userSymbol == 1)
			{
				g.drawLine(180, 220,230,265);
				g.drawLine(180, 265,230,220);
			}
			if(userSymbol == 0)   //draw O
			{
				g.drawOval(180,215,50,50);
			}
		}
		if(compButton8 == 1)
		{
			if(userSymbol == 1)
			{
				g.drawLine(250, 220,300,265);
				g.drawLine(250, 265,300,220);
			}
			if(userSymbol == 0)   //draw O
			{
				g.drawOval(250,215,50,50);
			}
		}
		if(compButton9 == 1)
		{
			if(userSymbol == 1)
			{
				g.drawLine(320, 220,370,265);
				g.drawLine(320, 265,370,220);
			}
			if(userSymbol == 0)   //draw O
			{
				g.drawOval(320,215,50,50);
			}
		}
	}
}
