import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel{
	
	boolean[][] game;
	// The width and height of the panel
	int width, height;
	// The number of rows and cols to display
	int numCols, numRows;
	// Height factor of state pixels, default of 4
	int heightFactor;

	public GridPanel(){
		super();
		this.game = MainSetup.game;
		heightFactor = 4;
		numRows = MainSetup.rowMax;
		numCols = MainSetup.colMax;

		createBounds();

		width = MainSetup.colMax;
		height = MainSetup.rowMax;
	}

	// Helper function that determines pixel display related information
	public void createBounds(){

		numRows = MainSetup.rowMax / heightFactor;
		numCols = MainSetup.colMax / heightFactor;
	}

	// Method called each repaint
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);

		displayPixels(g2);
	}

	// Displays each life as a rectangle
	public void displayPixels(Graphics2D g2){

		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numCols; j++){

				if(game[i][j]) g2.fill(new Rectangle(j * heightFactor, i * heightFactor, heightFactor, heightFactor));
				
			}
		}
	}

	public void setHeightFactor(int heightFactor){
		this.heightFactor = heightFactor;
	}


}