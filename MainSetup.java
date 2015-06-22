import java.io.*;

public class MainSetup{

	// The array for the game. 1 indicates alive, 0 indicates dead
	static boolean[][] game;
	static int rowMax = 600;
	static int colMax = 600;
	static int lineCount;
	static int colCount;

	public static void main(String[] args){
		// Gets the filename
		String filename;
		if(args.length != 0) filename = args[0];
		else filename = "test.txt";
		game = new boolean[rowMax][colMax];
		readGame(filename); 
		setGame();
		Simulation.setField();

		MainDisplay mainDisplay = new MainDisplay();
	}

	// Reads in the seed from a text file
	public static void readGame(String filename){
		lineCount = getLineNumbers(filename);
		colCount = -1;

		int i = 0, j = 0;

		// Create the reader
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(filename));
		}catch(FileNotFoundException e){
			System.out.println("Error: file not found");
			System.exit(0);
		}

		// Determines the number of columns
		try{
			colCount = reader.readLine().length();
			reader.close();
		}catch(IOException e){	
			System.out.println("Error: Unable to determine number of columns");
			System.exit(0);
		}

		// Re-creates the reader
		try{
			reader = new BufferedReader(new FileReader(filename));
		}catch(FileNotFoundException e){
			System.out.println("Error: file not found");
			System.exit(0);
		}

		// Parse the game array
		try{

			String line = null;
			// Reads in the game array
			for(i = 0; i < lineCount; i++){
				line = reader.readLine();
				for(j = 0; j < colCount; j++){
					char c = line.charAt(j);

					if(c == '0') game[i][j] = false;
					else if(c == '1') game[i][j] = true;
					else{
						System.out.println("Error: Character other than 0 or 1 present in file");
						System.exit(0);
					}
				}
			}

			// Close the reader
			reader.close();
		}catch(IOException e){
			System.out.println("Error: IO error occured during seed parse");
			System.exit(0);
		}catch(StringIndexOutOfBoundsException e){
			System.out.println("Error: Row lengths unequal");
			System.out.println("First occurence detected at line number " + (i + 1));
			System.exit(0);
		}

	}

	// Helper method that counts the number of lines in a text file
	public static int getLineNumbers(String filename){
		// Creates a reader
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(filename));
		}catch(FileNotFoundException e){
			System.out.println("Error: file not found");
			System.exit(0);
		}

		// By default the line count is one
		int lineCount = 1;
		int c  = -1;

		// Counts the number of lines
		try{
			do{
				c = reader.read();
				if(c == '\n') lineCount++;	
			}while(c != -1);
			reader.close();
		}catch(IOException e){
			System.out.println("Error: IO error occured during line count parse");
			System.exit(0);
		}
		
		return lineCount;
	}

	// Function for the purposes of testing. Prints out the game array
	public static void printGame(){
		for(int i = 0; i < game.length; i++){
			for(int j = 0; j < game[i].length; j++){
				if(game[i][j] == false) System.out.print(0);
				else System.out.print(1);
			}
			System.out.println();
		}
		System.out.println();
	}

	// Sets all remaining values to zero
	public static void setGame(){
		for(int i = 0; i < game.length; i++){
			for(int j = 0; j < game[i].length; j++){
				if(i >= lineCount || j >= colCount) game[i][j] = false;
			}
		}
	}
}