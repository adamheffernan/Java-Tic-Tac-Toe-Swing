import java.util.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 * A class modelling a tic-tac-toe (noughts and crosses, Xs and Os) game.
 * 
 * @author Adam Heffernan
 * @version November 27, 2017
 */

 public class TicTacToe implements ActionListener{

    
   private JTextArea status; // text area to print game status
   private JScrollPane scroll; //scoll pane 
   private JFrame frame; 
   private JMenuItem quitItem;
   private JMenuItem resetItem;
   private JMenu fileMenu; 
   private JTextField winnerText; 
   public  ImageIcon PLAYER_X ; // player using "X"
   public  ImageIcon PLAYER_O ; // player using "O"
   public static final ImageIcon EMPTY =  null;  // empty cell
   public  ImageIcon TIE; // game ended in a tie
   private boolean haveAWinner = false;
   private ImageIcon player = PLAYER_X;   // current player (PLAYER_X or PLAYER_O)
   private JPanel textPanel; 
   private JPanel buttonPanel; 
   private ImageIcon winner = null;   // winner: PLAYER_X, PLAYER_O, TIE, EMPTY = in progress
   private String oSound = "zero.wav";
   private String xSound = "mnf_ready_4_football.wav";
   private String winSound = "win.wav";
   private int numFreeSquares = 9; // number of squares still free
   private JTextField statusText; 
   private JFrame board; 
   private JButton[] boardLay[];// 3x3 array representing the board
   private int oWins= 0; 
   private int xWins=0; 
   private int ties = 0; 
   private JTextField winCol; 
  
   /** 
    * Constructs a new Tic-Tac-Toe board and initializes 
    * GUI, all buttons text fields, menu bars setting colors. 
    */
   public TicTacToe()
   { 
       
       JMenuBar menuBar = new JMenuBar(); 
       buttonPanel = new JPanel(); 
       textPanel = new JPanel(); 
       boardLay = new JButton[3][3];
       boardLay[0][0] = new JButton();
       boardLay[0][1] = new JButton();
       boardLay[0][2] = new JButton();
       boardLay[1][0] = new JButton();
       boardLay[1][1] = new JButton();
       boardLay[1][2] = new JButton();
       boardLay[2][0] = new JButton();
       boardLay[2][1] = new JButton();
       boardLay[2][2] = new JButton();
      TIE = new ImageIcon("","Tie");
      PLAYER_X = new ImageIcon("x.jpg","X");
      PLAYER_O = new ImageIcon("o.jpg","O");
      
      player = new ImageIcon();
      player = PLAYER_X; 
      boardLay[0][2].addActionListener(this);
      boardLay[0][1].addActionListener(this);
      boardLay[0][0].addActionListener(this);
      boardLay[1][1].addActionListener(this);
      boardLay[1][0].addActionListener(this);
      boardLay[1][2].addActionListener(this);
      boardLay[2][0].addActionListener(this);
      boardLay[2][1].addActionListener(this);
      boardLay[2][2].addActionListener(this);
      fileMenu = new JMenu("Options"); // create a menu
      menuBar.add(fileMenu); // and add to our menu bar
      JMenuItem quitItem = new JMenuItem("Quit");
      JMenuItem resetItem = new JMenuItem ("New Game");
      JMenuItem changeItem = new JMenuItem ("Change Starting Player");
      fileMenu.add(quitItem);
      fileMenu.add(resetItem);
      
      fileMenu.add(changeItem);
      board = new JFrame("TicTacToe");
       buttonPanel.setLayout(new GridLayout(3,3));
       board.setExtendedState(JFrame.MAXIMIZED_BOTH); 
       winCol = new JTextField("O Wins: " + oWins+ "\nX Wins: " +xWins +"\nTies: " + ties );
       textPanel.setLayout(new GridLayout(1,3));
       board.setJMenuBar(menuBar);
       winnerText = new JTextField("");
       statusText = new JTextField("Push on a square to begin (X Starts)");
       buttonPanel.add(boardLay[0][0]);
       buttonPanel.add(boardLay[0][1]);
       buttonPanel.add(boardLay[0][2]);
       buttonPanel.add(boardLay[1][0]);
       buttonPanel.add(boardLay[1][1]);
       buttonPanel.add(boardLay[1][2]);
       buttonPanel.add(boardLay[2][0]);
       buttonPanel.add(boardLay[2][1]);
       buttonPanel.add(boardLay[2][2]);
       textPanel.add(winnerText);
       textPanel.add(winCol);
       textPanel.add(statusText);
       board.setLayout(new BorderLayout());
       winCol.setEditable(false);
       statusText.setEditable(false);
       winnerText.setEditable(false);
       statusText.setBackground(Color.CYAN);
       winnerText.setBackground(Color.CYAN);
       winCol.setBackground(Color.CYAN);
       winCol.setFont(new Font("Arial",Font.PLAIN,20));
       statusText.setFont(new Font("Arial",Font.PLAIN,20));
       winnerText.setFont(new Font("Arial",Font.PLAIN,20));
       statusText.setForeground(Color.DARK_GRAY);
       winnerText.setForeground(Color.DARK_GRAY);
       buttonPanel.setBackground(new Color(200,20,50));
       textPanel.setOpaque(true);
       textPanel.setBackground(new Color(200,20,50));
       textPanel.setForeground(new Color(100,10,50));
       board.add(buttonPanel,BorderLayout.CENTER);
       board.add(textPanel, BorderLayout.SOUTH);
       board.setVisible(true);
      
       final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(); 
       resetItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, SHORTCUT_MASK));//Creating shortcut cmd cmd_N keys
       quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));//Creating shortcut cmd cmd_Q keys
       changeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, SHORTCUT_MASK));//Creating shortcut cmd cmd_B keys
       resetItem.addActionListener(new ActionListener() // create an anonymous inner class
           { // start of anonymous subclass of ActionListener
          // this allows us to put the code for this action here  
            public void actionPerformed(ActionEvent event)
            {
                clearBoard(); // clear the board
            }
          } // end of anonymous subclass
        ); 
         changeItem.addActionListener(new ActionListener() // create an anonymous inner class
           { // start of anonymous subclass of ActionListener
          // this allows us to put the code for this action here  
            public void actionPerformed(ActionEvent event)
            {
               switchPlayer(); // switch the player
               statusText.setText("Push on a square to begin (O Starts)");
            }
          } // end of anonymous subclass
        );
      quitItem.addActionListener(new ActionListener() // create an anonymous inner class
        { // start of anonymous subclass of ActionListener
          // this allows us to put the code for this action here  
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0); // quit
            }
        } // end of anonymous subclass
      ); // end of addActionListener parameter list and statement 
   }
   /**
    * Plays a sound when a player makes a move, or after the game is won.  
    *
     * @param soundName needs the string of a sound file within the current folder.
     */
    public void playSound(String soundName)
      {
       if(haveAWinner==false){//as long as we dont haveAWinner play sound from this method.
           try 
          {
    
           AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
           Clip clip = AudioSystem.getClip( );
           clip.open(audioInputStream);
           clip.start( );
          }
   
          catch(Exception ex)
          {
           
          }
     }

   }
 
      /**
      * Switches the ImageIcon of the current player `
    */
      private void switchPlayer(){
          
     
      if(player == PLAYER_X){//switching the player ImageIcon
        player = PLAYER_O;
      }
      else if(player == PLAYER_O){//switching the player ImageIcon
       player = PLAYER_X; 
       }
    
   }
    /**
    * Clears the board, no matter what point in the game this is pushed, sets 
    * icons to null, and re-enables all buttons. sets haveAWinner to false,
    * numFreeSquares to 9, winners imageicon to be empty. set the status text 
    * to say a new game is now in process. set the current player to playerx, 
    * set the winners text field to empty. 
    */
      private void clearBoard()
      {
          for (int i=0;i<3;i++){
          for(int j=0; j<3;j++){
            boardLay[i][j].setIcon(null);//Enabling all icons and setting 
            boardLay[i][j].setEnabled(true);//all icons to null
          }
        }
      
     
      haveAWinner= false;//haveAWinner set to false
      winner = EMPTY;//Setting winner to empty
      numFreeSquares = 9;//Number of free squares to 9
      statusText.setText("Push on a square to begin(X Starts)");// Player X always has the first turn.
      player = PLAYER_X;//Current player is player x, as they always begin a new board
      winnerText.setText("");//clear the winner text field
   }
    


   /**
    * Returns true if filling the given square gives us a winner, and false
    * otherwise.
    *
    * @param int row of square just set
    * @param int col of square just set
    * 
    * @return true if we have a winner, false otherwise
    */
   private boolean haveWinner(int row, int col) 
   {
       // unless at least 5 squares have been filled, we don't need to go any further
       // (the earliest we can have a winner is after player X's 3rd move).
      if (numFreeSquares>5){
          winner = null; 
          haveAWinner=false;
          return false;
      }
       // Note: We don't need to check all rows, columns, and diagonals, only those
       // that contain the latest filled square.  We know that we have a winner 
       // if all 3 squares are the same, as they can't all be blank (as the latest
       // filled square is one of them).
      try{//try all of these conditions seperately as they can all throw NullPointerExceptions when they don't have an ImageIcon
          // check row "row"
         if ( boardLay[row][0].getIcon().equals(boardLay[row][1].getIcon()) &&
           boardLay[row][0].getIcon().equals(boardLay[row][2].getIcon()) ) {winner=player;haveAWinner=true;return true;}
        }
        catch(NullPointerException e){
        }
       // check column "col"
       try{
            if ( boardLay[0][col].getIcon().equals(boardLay[1][col].getIcon()) &&
              boardLay[0][col].getIcon().equals(boardLay[2][col].getIcon()) ) {winner = player;haveAWinner=true; return true;}
        }
      catch(NullPointerException e){
      }
       // if row=col check one diagonal
      try{
          if (row==col)
            if ( boardLay[0][0].getIcon().equals(boardLay[1][1].getIcon()) &&
               boardLay[0][0].getIcon().equals(boardLay[2][2].getIcon()) ) { winner = player; haveAWinner=true; return true;}
            }
      catch(NullPointerException e){
      }
      try{
         // if row=2-col check other diagonal
         if (row==2-col)
           if ( boardLay[0][2].getIcon().equals(boardLay[1][1].getIcon()) &&
              boardLay[0][2].getIcon().equals(boardLay[2][0].getIcon()) ) {winner =player;haveAWinner=true; return true;}
        }
      catch(NullPointerException e){
      }
      try{
         if(numFreeSquares-1==0){
           winner = TIE; 
         haveAWinner=true; 
           return true; 
        }
        }
      catch(NullPointerException e){
        }
        winner = null; 
        haveAWinner=false;
        return false;
   } 
     
      public void actionPerformed(ActionEvent click){
           Object o = click.getSource();
        if(haveAWinner == false){   //as long as haveAWinner=false we can perform an action on the button.
             if (o instanceof JButton){//if o is an instance of a jbutton
            JButton button = (JButton)o;//button is the object o typeset to a JButton
               for (int i= 0;i<3;i++) {//check all buttons so we can run haveWinner(row,col)
                  for(int j=0;j<3;j++){ 
                     if(button == boardLay[i][j] ){
                         boardLay[i][j].setIcon(player); //set of the button clicked to the current player
                         boardLay[i][j].setEnabled(false);//disable the button
                          boardLay[i][j].setDisabledIcon(player);//set the disabled icon to the same as the enabled icon
                         if(haveWinner(i,j)){//if have a winner is true play the winner song.
                             
                         try{
                          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(winSound).getAbsoluteFile( ));
                          Clip clip = AudioSystem.getClip();
                          clip.open(audioInputStream);
                          clip.start(); 
                           }
                           catch(Exception e)
                          {
                            
                           }
                         winnerText.setText("The Winner is Player.... " + winner.getDescription() );//set the winnerText field
                         if (winner.getDescription() == "X"){
                           xWins++; 
                           winCol.setText("O Wins: " + oWins +"\nX Wins: " + xWins+"\nTies: " + ties );//set the winCol count if xWins
                         }
                         else if(winner.getDescription() == "O"){
                           oWins++;
                           winCol.setText("O Wins: " + oWins +"\nX Wins: " + xWins +"\nTies: " + ties );//set the winCol count if oWins
                          }
                          else{
                            ties++; 
                            winCol.setText("O Wins: " + oWins +"\nX Wins: " + xWins +"\nTies: " + ties );//set the winCol count if a Tie
                        }
                      
                     }
                     switchPlayer(); //switch the player
                     numFreeSquares--; //decrement the number of free squares
                     statusText.setText("Game is in Progress, Player " + player.getDescription() + "'s Turn" );//output the current players turn
                     if(player.getDescription() == "O")//if player O went player xSound if player X went play oSound
                     playSound(xSound);
                     else if(player.getDescription()=="X")
                     playSound(oSound);
                     
                    }
                    
                  } 
              
            }
           
          }  
         } 
        if(haveAWinner == true){//if haveAWinner=true statusText outputs game is over
                 
                  statusText.setText("Game is over");
                  for(int i=0; i<3;i++){
                    for(int j=0;j<3;j++){
                    boardLay[i][j].setEnabled(false);//disable all the buttons on the board when the game is over
                    }
                  }
         }
   }    
}
 

  
 

    


