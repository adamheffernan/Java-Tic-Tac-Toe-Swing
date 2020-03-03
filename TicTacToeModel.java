public class TicTacToeModel{
	//This class represents the tic tic toe game without knowledge of
	//what kind of interface will be running the game
	//This class is our "Game Model"


	//This is the data structure that represents the game board
	//Notice the game board is represented in the model by a simple 1D array
	//(i.e. not a two-dimensional structure)
	//position 0 in the array is NOT USED

	private String [] positions = {"*", "1","2", "3", "4", "5", "6", "7", "8", "9"}; // "board" positions


	public TicTacToeModel(){ //Constructor
	}

	//Public methods used by an interface to use the game model
	//---------------------------------------------------------
	public String symbolForPosition(int row, int column){
		//answer the symbol "X", "O", or " " that should be drawn
		//on the row and column on the game board. The rows are
		//numberered 1,2,3 and the columns are numbered 1,2,3

		//convert the row and column to single array position
		int position = (row-1)*3 + 1 + (column -1);

		if(isX(position)) return "X";
		else if(isO(position)) return "O";
		else return "";

	}

	public void XPlays(int row, int column){
	//This is the public method that an GUI interface would call to make a move for X
	//This method is expecting a row and column for the game board with rows numbered 1,2,3
	//and columns numbered 1,2,3.

	//If X made a legal move the model will make a defending move

	//If the game is over the model will print the results to the console

	  //convert the row and column to a single position that corresponds to the
	  //array used by the ticTacToe model

	  int position = (row-1)*3 + 1 + (column -1);

      boolean moveWasLegal = XPlays(position); //make the challengers move

      if(moveWasLegal) makeADefendingMove(); //make a defending move

      //print the results if the game is over
      if(isGameOver()){
      	   if(hasWon("X"))
             System.out.println("CHALLENGER WINS ---no mark yet");
           else if(hasWon("O"))
             System.out.println("PROGRAMMER WINS");
           else
              System.out.println("TIE GAME");

      }
	}

	//Private methods used by the game model
	//These methods will not be called by the GUI interface, rather they are used by the
	//game model itself only

	//---------------------------------------
	private boolean hasWon(String anXorO){
		//Answer whether string anXorO is in a winning position
		//String anXorO is expected to be either an "X" or an "O"

		//check the rows for XXX or OOO
		if((positions[1].equals(anXorO))&&(positions[2].equals(anXorO))&&positions[3].equals(anXorO)) return true;
		else if((positions[4].equals(anXorO))&&(positions[5].equals(anXorO))&&positions[6].equals(anXorO)) return true;
		else if((positions[7].equals(anXorO))&&(positions[8].equals(anXorO))&&positions[9].equals(anXorO)) return true;

	    //check the columns for XXX or OOO
		else if((positions[1].equals(anXorO))&&(positions[4].equals(anXorO))&&positions[7].equals(anXorO)) return true;
		else if((positions[2].equals(anXorO))&&(positions[5].equals(anXorO))&&positions[8].equals(anXorO)) return true;
		else if((positions[3].equals(anXorO))&&(positions[6].equals(anXorO))&&positions[9].equals(anXorO)) return true;

	    //check the diagonals for XXX or OOO
		else if((positions[1].equals(anXorO))&&(positions[5].equals(anXorO))&&positions[9].equals(anXorO)) return true;
		else if((positions[3].equals(anXorO))&&(positions[5].equals(anXorO))&&positions[7].equals(anXorO)) return true;

	    return false;
	}

	private boolean isGameOver(){
	//Answer whether the game is over
	//The game is over if either "X" or "O" have a winning position or
	//there are no more free positions

		if(!hasFreePositions()) return true;
		if(hasWon("X")) return true;
		if(hasWon("O")) return true;

		return false;
	}
	private boolean hasFreePositions(){
		//Answer whether there are still free places to play on
		boolean hasFreePlacesToPlay = false;
		for(int i= 1; i<positions.length; i++)
		   if(isFree(i)) hasFreePlacesToPlay = true;

		return hasFreePlacesToPlay;
	}

    private int numberOfFreePositions(){
		//Answer the number of free places left to play on
		int numberFree = 0;
		for(int i= 1; i<positions.length; i++)
		  if(isFree(i)) numberFree++;

		return numberFree;
	}



	private boolean isFree(int aPosition){
		//Answer whether position aPosition is free to play on
		//It is not free if there is an "X" or "O" on it

		if((aPosition < 1)|| (aPosition > 9)) return false; //positions are out of range
		return !((positions[aPosition].equals("X")) || (positions[aPosition].equals("O")));
	}

	private boolean isX(int aPosition){
		//Answer whether position aPosition  has an "X" on it

		if((aPosition < 1)|| (aPosition > 9)) return false; //positions are out of range
		return positions[aPosition].equals("X");
	}

	private boolean isO(int aPosition){
		//Answer whether position aPosition  has an "O" on it

		if((aPosition < 1)|| (aPosition > 9)) return false; //positions are out of range
		return positions[aPosition].equals("O");
	}



    private boolean XPlays(int aPosition){
    	//Make a move for the challenger X
    	//Mark position aPosition with an X if it's free
    	//if the position is not free return false indicating the move did not succeed

    	if(isFree(aPosition)){
    		positions[aPosition] = "X";
    		return true;
    	}
    	return false;
    }
    private boolean OPlays(int aPosition){
    	//Make a move for the defender O
    	//Mark position aPosition with an "O" if it's free
    	//if the position is not free return false indicating the move did not succeed

    	if(isFree(aPosition)){
    		positions[aPosition] = "O";
    		return true;
    	}
    	return false;
    }

    private void makeADefendingMove(){

    	 /*
    	 *This is where the program decides where "O" (the defender) should play.
    	 *If there is a free place to play then "O" must play.
    	 *
    	 *This code can be written by making use of if and if-else statements and
    	 *making use of the methods isX(), isY(), isFree(), and numberOfFreeSpaces()
    	 *
   	*/



    	//Basic Strategy: play on first available free position that can be found
    	//but prefer the corners when available

    	if(isFree(1)) OPlays(1);
    	else if(isFree(3)) OPlays(3);
     	else if(isFree(7)) OPlays(7);
     	else if(isFree(9)) OPlays(9);
  	    else if(isFree(2)) OPlays(2);
    	else if(isFree(4)) OPlays(4);
    	else if(isFree(5)) OPlays(5);
    	else if(isFree(6)) OPlays(6);
    	else if(isFree(8)) OPlays(8);


    }


	public void print(){
		//This method prints the game board on the console

		System.out.println("" );
		System.out.println("" );
		System.out.println("        | "+ "  | " );
		System.out.println("      "+ positions[1] + " | " + positions[2] + " | " + positions[3]);
		System.out.println("        | "+ "  | " );
		System.out.println("     -----------");
		System.out.println("        | "+ "  | " );
		System.out.println("      "+ positions[4] + " | " + positions[5] + " | " + positions[6]);
		System.out.println("        | "+ "  | " );
		System.out.println("     -----------");
		System.out.println("        | "+ "  | " );
		System.out.println("      "+ positions[7] + " | " + positions[8] + " | " + positions[9]);
		System.out.println("        | "+ "  | " );
	}





} //end class TicTacToeModel

