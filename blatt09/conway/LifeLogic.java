public class LifeLogic {

    public static void compute(boolean[][] field) {
        //TODO: Merry Christmas !
        //Hinweis: Erstellen Sie sich Hilfsmethoden zur Berechnung der Anzahl der umgebenden lebenden Zellen.

		boolean[][] fieldCopy = new boolean[field.length][field[0].length];

    	for (int row = 0; row < field.length; row++) {
    		for (int col = 0; col < field[0].length; col++) {
    			
    			int livingNeighbors = calculateLivingNeighbors(field, row, col);

    			// Die Zelle lebt
    			if (field[row][col]) {

    				fieldCopy[row][col] = livingNeighbors == 2 || livingNeighbors == 3;

    			// Die Zelle ist tot
    			}else{

    				fieldCopy[row][col] = livingNeighbors == 3;
    			}
    		}
    	}

    	// Uebertragen der Werte
    	for (int i = 0; i < field.length ; i++){
    		for (int j = 0; j < field[0].length; j++)
    			field[i][j] = fieldCopy[i][j];
		}
    }


    public static int calculateLivingNeighbors(boolean[][] field, int row, int col){

    	// ACHTUNG: sich selbst nicht mitzaehlen! (Fall row = col)
    	int livingNeighbors = 0;

    	boolean[][] neighborArray = getNeighborArray(field, row, col);

    	for (int i = 0; i < neighborArray.length; i++) {
    		for (int j = 0; j < neighborArray[0].length ; j++) {
    			
    			if (i == 1 && j == 1) {
    				continue;
    			}

    			if (neighborArray[i][j]) {
    				livingNeighbors++;
    			}
    		}
    	}


    	return livingNeighbors;
    }




    public static boolean[][] getNeighborArray(boolean[][] field, int row, int col){

    	boolean[][] neighborArray = new boolean[3][3];



    	for (int i = row - 1, k = 0; k < neighborArray.length; i++, k++) {
    		for (int j = col - 1, l = 0; l < neighborArray[0].length ; j++, l++) {
    			
    			if (i < 0){
    				i += field.length;
    			}else if (i >= field.length) {
    				i -= field.length;
    			}
    			
    			if (j < 0) {
    				j += field[0].length;
    			}else if (j >= field[0].length) {
    				j -= field[0].length;	
    			}


    			neighborArray[k][l] = field[i][j];
    		}
    	}

    return neighborArray;
    }

}
