/* --------------------
* A tool for displaying Cellular Automata in
* a window.
* @author Andrew Troedson and Andrew Brown* Updated by Tim Opie 2017
* ---------------------
*/
package jm.music.tools.ca;

import java.awt.*;import jm.music.tools.ca.CellularAutomata;
import java.awt.event.*;

public class DrawCA extends Frame implements WindowListener {
	int xPos;
	int yPos;
	int width;
	int height;
	int cellSize;
	CellDrawArea da;

	//--------------
	//constructors
	//--------------
	public DrawCA(CellularAutomata CA) {
		this(CA, "Cellular Automata");
	}

	public DrawCA(CellularAutomata CA, String title) {
		this(CA, title, 5); //5 is the default cellSize
	}

	public DrawCA(CellularAutomata CA, String title, int cellSize) {
		this(CA, title, cellSize, 10, 10); // default window position
	}

	public DrawCA(CellularAutomata CA, String title, int cellSize, int xPos, int yPos) {
		super(title);
		this.width = CA.cellStates.length;
		this.height = CA.cellStates[0].length;
		this.cellSize = cellSize;
		this.xPos = xPos;
		this.yPos = yPos;

		//register the closebox event
		this.addWindowListener(this);

		//add a Panel called pan with a Canvas called da inside it
		Panel pan = new Panel();
		pan.setSize(width*cellSize, height*cellSize);
		da = new CellDrawArea(CA, cellSize);
		pan.add(da);
		this.add(pan);

		//construct and display
		this.setSize(width*cellSize, height*cellSize);
		this.setResizable(false);
		this.setLocation(xPos,yPos);
		this.pack();
		this.show();
		this.toFront();
	}

	//--------------
	// Class Methods
	//--------------

	public void repaint() {
		da.repaint();
	}

	// Deal with the window closebox
	public void windowClosing(WindowEvent we) {
		this.dispose(); //System.exit(0);
	}
	//other WindowListener interface methods
	//They do nothing but are required to be present
	public void windowActivated(WindowEvent we) {};
	public void windowClosed(WindowEvent we) {};
	public void windowDeactivated(WindowEvent we) {};
	public void windowIconified(WindowEvent we) {};
	public void windowDeiconified(WindowEvent we) {};
	public void windowOpened(WindowEvent we) {};

}

//--------------
//second class!!
//--------------
class CellDrawArea extends Canvas {
	CellularAutomata CA;
	int cellSize;

	public CellDrawArea(CellularAutomata CA, int cellSize){
		super();
		this.CA = CA;
		this.cellSize = cellSize;
		this.setSize(CA.cellStates.length*cellSize,CA.cellStates[0].length*cellSize);
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		for(int i=0;i<CA.cellStates.length;i++) {
			for(int j=0;j<CA.cellStates[0].length;j++) {
			if(CA.cellStates[i][j] == true) g.fillRect(cellSize*i,cellSize*j,cellSize-1,cellSize-1);
			}
		}
	}
}