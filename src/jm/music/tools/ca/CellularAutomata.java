/* --------------------
* A mathematical engine for Cellular Automata
* @author Andrew Troedson
* Updated by Tim Opie 2017
*/
package jm.music.tools.ca;

public class CellularAutomata {

	 /*--------------------
   * CLASS VARIABLES
   * --------------------
   */

   int xSize; //the width of the grid
   int ySize; //the height of the grid
   boolean wrapAround; //the grid wrap Around switch
   boolean[][] cellStates; //stores the states of each cell in the grid
   int[][] cellSurrounds; //stores the number of living cells surrounding each cell in the grid

    /*--------------------
   * CONSTRUCTORS
   * --------------------
   */

   //wrapAround defaults to false, seed defaults to 25
   public CellularAutomata(int xSize, int ySize){
   	this(xSize, ySize, 25, false);
   }

   //wrapAround defaults to false
   public CellularAutomata(int xSize, int ySize, int seed){
   	this(xSize, ySize, seed, false);
   }

   //creates and fills a boolean[][] grid
   public CellularAutomata(int xSize, int ySize, int seed, boolean wrapAround){
   	this.xSize = xSize;
   	this.ySize = ySize;
   	this.wrapAround = wrapAround;
   	cellStates = new boolean[xSize][ySize];
   	cellSurrounds = new int[xSize][ySize];
   	this.setGrid(seed);
   }

   /*--------------------
   * METHODS
   * --------------------
   */

   /**
	 * Evolve all the cells according to a set of rules
	 */
   public void evolve(){
   	boolean[][] nextCellStates = new boolean[xSize][ySize];
   	for (int i=0; i<xSize; i++) {
   		for (int j=0; j<ySize; j++) {
   			//if cell is already alive, it must be surrounded by 3 live cells to stay alive
   			if(this.getState(i,j)){
   				if(this.getSurrounding(i,j)==2){
   					nextCellStates[i][j] = true;
   				}
   				else{
   					nextCellStates[i][j] = false;
   				}
   			}
   			//if cell is dead, it must be surrounded by 2 or 3 live cells to come alive
   			else if(this.getState(i,j)==false){
   				if((this.getSurrounding(i,j)==2)||(this.getSurrounding(i,j)==3)){
   					nextCellStates[i][j] = true;
   				}
   			}
   		}
   	}
   	this.cellStates = nextCellStates;
   }

   /**
	 * Return the state of a particular cell
	 * @param xPos the x coordinate
	 * @param yPos the y coordinate
	 * @return boolean the state of the cell at the specified coordinate
	 */
   public boolean getState(int xPos, int yPos){
   	return cellStates[xPos][yPos];
   }

   //get the states of all the cells as a boolean[][]
   public boolean[][] getAllStates(){
   	return cellStates;
   }

   //counts the number of living cells surrounding the selected cell
   public int getSurrounding(int xPos, int yPos){
   	int counter=0; //stores the current count

   	try{
   		if (cellStates[xPos-1][yPos-1]==true){//NW
   			counter++;}
   	}
   	catch (ArrayIndexOutOfBoundsException e){
   		if (wrapAround){
   			if((xPos-1 < 0)&&(yPos-1 < 0)){
   				if(cellStates[xSize-1][ySize-1]==true){
   					counter++;}
   			}
   			else if(xPos-1 < 0){
   				if(cellStates[xSize-1][yPos-1]==true){
   					counter++;}
   			}
   			else if(yPos-1 < 0){
   				if(cellStates[xPos-1][ySize-1]==true){
   					counter++;}
   			}
   		}
   	}

   	try{
   	if (cellStates[xPos][yPos-1]==true){//N
   		counter++;}
   	}
   	catch (ArrayIndexOutOfBoundsException e){
   		if (wrapAround){
   			if(cellStates[xPos][ySize-1]==true){
   				counter++;}
   		}
   	}

   	try{
   	if (cellStates[xPos+1][yPos-1]==true){//NE
   		counter++;}
   	}
   	catch (ArrayIndexOutOfBoundsException e){
   		if (wrapAround){
   			if((xPos+1 >= xSize)&&(yPos-1 < 0)){
   				if(cellStates[0][ySize-1]==true){
   					counter++;}
   			}
   			else if(xPos+1 >= xSize){
   				if(cellStates[0][yPos-1]==true){
   					counter++;}
   			}
   			else if(yPos-1 < 0){
   				if(cellStates[xPos+1][ySize-1]==true){
   					counter++;}
   			}
   		}
   	}

   	try{
   	if (cellStates[xPos-1][yPos]==true){//W
   		counter++;}
   	}
   	catch (ArrayIndexOutOfBoundsException e){
   		if (wrapAround){
   			if(cellStates[xSize-1][yPos]==true){
   				counter++;}
   		}
   	}

		try{
   	if (cellStates[xPos+1][yPos]==true){//E
   		counter++;}
   	}
   	catch (ArrayIndexOutOfBoundsException e){
   		if (wrapAround){
   			if(cellStates[0][yPos]==true){
   				counter++;}
   		}
   	}

		try{
   	if (cellStates[xPos-1][yPos+1]==true){//SW
   		counter++;}
   	}
   	catch (ArrayIndexOutOfBoundsException e){
   		if (wrapAround){
   			if((xPos-1 < 0)&&(yPos+1 >= ySize)){
   				if(cellStates[xSize-1][0]==true){
   					counter++;}
   			}
   			else if(xPos-1 < 0){
   				if(cellStates[xSize-1][yPos+1]==true){
   					counter++;}
   			}
   			else if(yPos+1 >= ySize){
   				if(cellStates[xPos-1][0]==true){
   					counter++;}
   			}
   		}
   	}

		try{
   	if (cellStates[xPos][yPos+1]==true){//S
   		counter++;}
   	}
   	catch (ArrayIndexOutOfBoundsException e){
   		if (wrapAround){
   			if(cellStates[xPos][0]==true){
   				counter++;}
   		}
   	}

		try{
   	if (cellStates[xPos+1][yPos+1]==true){//SE
   		counter++;}
   	}
   	catch (ArrayIndexOutOfBoundsException e){
   		if (wrapAround){
   			if((xPos+1 >= xSize)&&(yPos+1 >= ySize)){
   				if(cellStates[0][0]==true){
   					counter++;}
   			}
   			else if(xPos+1 >= xSize){
   				if(cellStates[0][yPos+1]==true){
   					counter++;}
   			}
   			else if(yPos+1 >= ySize){
   				if(cellStates[xPos+1][0]==true){
   					counter++;}
   			}
   		}
   	}

   	return counter;
   }

   /**
   * prints out the current grid
   */
   public void print(){
		 for (int i=0; i<this.xSize; i++) {
   			for (int j=0; j<this.ySize; j++) {
   				if (this.getState(i,j)==true) {
   					System.out.print("1");
   				}
   				else {
   					System.out.print("0");
   				}
   			}
   			System.out.println("");
   		}
    	System.out.println("");
   	}

   /**
   * fill the cellStates grid based on the seed probability
   */
   public void setGrid(int seed){

   	for (int i=0; i<xSize; i++) {
   		for (int j=0; j<ySize; j++) {
   			cellStates[i][j] = this.trueFalse(seed);
   		}
   	}
   }

   //a method which chooses true or false given a probability
   private boolean trueFalse(int seed){
  	    boolean choice;
   	    int randNum = (int)(java.lang.Math.random()*100);
   	    if (randNum<seed) {
   	    	choice = true;
   	    }
   	    else {
   	    	choice = false;
   	    }
   	    return choice;
    }
}