public class Simulation{
	static boolean[][] game;
	static int totalCells;
	static int aliveCells;
	static int deadCells;
	static int generation = 0;

	// Runs the game for one step
	public static void step(){
		boolean[][] tempGame = new boolean[MainSetup.rowMax][MainSetup.colMax];
		aliveCells = 0;
		deadCells = 0;
		for(int i = 0; i < MainSetup.rowMax; i++){
			for(int j = 0; j < MainSetup.colMax; j++){
				int neighbors = countNeighbors(i, j);
				if(game[i][j] == false){
					if(neighbors == 3){
						aliveCells++;
						tempGame[i][j] = true;
					}else{
						deadCells++;
						tempGame[i][j] = false;
					}
				}else{
					if(neighbors < 2 || neighbors > 3){
						deadCells++;
						tempGame[i][j] = false;
					}else{
						aliveCells++;
						tempGame[i][j] = true;
					}
				}
			}
		}

		generation++;
		updateGame(tempGame);

	}


	// Counts the number of neighbors of a cell at index i and index j
	public static int countNeighbors(int i, int j){
		int count = 0;
		int x, y;
		
		// Loops through all neighbors, determines count
		for(int k = -1; k <= 1; k++){
			for(int l = -1; l <= 1; l++){
				x = i + k;
				y = j + l;
				if(x < 0 || y < 0 || x >= MainSetup.rowMax || y >= MainSetup.colMax || (x == i && y == j)){
					continue;
				}else{
					if(game[x][y] == true) count++;
				}
			}
		}
		return count;
	}

	// Obtains the game field from main setup
	public static void setField(){
		game = MainSetup.game;
	}

	// Copies the contents of the buffer array into the main game array
	public static void updateGame(boolean[][] temp){
		for(int i = 0; i < MainSetup.rowMax; i++){
			for(int j = 0; j < MainSetup.colMax; j++){
				game[i][j] = temp[i][j];
			}
		}
	}

}
