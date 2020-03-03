import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TicTacToeGUI extends JFrame {

//Domain Model Variables
TicTacToeModel model = new TicTacToeModel();


//GUI variables
//Panel dimensions for main window
final int PANEL_WIDTH = 300; //width of the main window panel
final int PANEL_HEIGHT = 300;  //height of the main window panel
final int BUTTON_WIDTH = PANEL_WIDTH/3;
final int BUTTON_HEIGHT = BUTTON_WIDTH;

final int ROWS = 3;
final int COLUMNS = 3;

Font labelFont = new Font("SansSerif", Font.BOLD, 30);


//Each button represents a positon on the tic-tac-toe board
//Rows are numbered 1,2,3 and columns are numbered 1,2,3

JButton button11 = new JButton(); //row 1 column 1
JButton button12 = new JButton(); //row 1 column 2
JButton button13 = new JButton(); //row 1 column 3
JButton button21 = new JButton(); //row 2 column 1
JButton button22 = new JButton(); //row 2 column 2
JButton button23 = new JButton(); //row 2 column 3
JButton button31 = new JButton(); //row 3 column 1
JButton button32 = new JButton(); //row 3 column 2
JButton button33 = new JButton(); //row 3 column 3

	public TicTacToeGUI (String title) {
		super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false); //make window non-resizeable
        getContentPane().setLayout(null); //use no layout management
        getContentPane().setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        pack();  //set window size to fit content pane
        setLocation(100,100);

        //set button size and location and add to content pane
        button11.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        button11.setLocation(0,0);
        button11.setFont(labelFont);
        add(button11);

        button12.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        button12.setLocation(BUTTON_WIDTH,0);
        button12.setFont(labelFont);
        add(button12);

        button13.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        button13.setLocation(2*BUTTON_WIDTH,0);
        button13.setFont(labelFont);
        add(button13);

        button21.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        button21.setLocation(0,BUTTON_HEIGHT);
        button21.setFont(labelFont);
        add(button21);

        button22.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        button22.setLocation(BUTTON_WIDTH,BUTTON_HEIGHT);
        button22.setFont(labelFont);
        add(button22);

        button23.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        button23.setLocation(2*BUTTON_WIDTH,BUTTON_HEIGHT);
        button23.setFont(labelFont);
        add(button23);

        button31.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        button31.setLocation(0,2*BUTTON_HEIGHT);
        button31.setFont(labelFont);
        add(button31);

        button32.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        button32.setLocation(BUTTON_WIDTH,2*BUTTON_HEIGHT);
        button32.setFont(labelFont);
        add(button32);

        button33.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        button33.setLocation(2*BUTTON_WIDTH,2*BUTTON_HEIGHT);
        button33.setFont(labelFont);
        add(button33);

        //add action listeners to buttons
        //1) MISSING CODE
        //add action listeners to each button




        //END MISSING CODE


	}

	public void actionPerformed(ActionEvent e) {

		//2) MISSING CODE
		//determine the which button produced the event
		//and ask the game model to play on the corresponing square
		//by invoking the game model's XPlays(row, column) method








		//END MISSING CODE

		update(); //cause the GUI to update
    }

    public void update(){

    	//This method is called whenever the GUI appearance should be
    	//updated to reflect the state of the game model

    	//This method determines and sets the label of each button
    	//based on the current state of the game model
    	//This method is complete and should not require modification for
    	//Problems 1 and 2

        button11.setText(model.symbolForPosition(1,1));
        button12.setText(model.symbolForPosition(1,2));
        button13.setText(model.symbolForPosition(1,3));

        button21.setText(model.symbolForPosition(2,1));
        button22.setText(model.symbolForPosition(2,2));
        button23.setText(model.symbolForPosition(2,3));

        button31.setText(model.symbolForPosition(3,1));
        button32.setText(model.symbolForPosition(3,2));
        button33.setText(model.symbolForPosition(3,3));


    	//END MISSING CODE
    }




	public static void main(String args[]) {
		TicTacToeGUI frame = new TicTacToeGUI("Tic Tac Toe");
		frame.setVisible(true);
	}
}
