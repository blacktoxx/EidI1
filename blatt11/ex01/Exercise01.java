public class Exercise01 {

    final static int DRAWING_PAUSE = 10; //Wait 100ms between drawing figures
    final static String IMAGE_DIR = "img/";

    private static BoardViewer bv;

    private static int figuresToPlace = 0;

    public static void main(String[] args) {

        bv = new BoardViewer();
        bv.show();

        int[][] board = new int[8][8];
        
        bv.setBoard(board);
        

        bv.touch();

        //System.out.println(countQueens(board, bv));

        //System.out.println(countKnights(board, bv));
        System.out.println(countQueensAndKnights(board, bv));
    
    }



    public static int countQueens(int[][] board, BoardViewer bv){


        int maxQueens = 0;
        for(;countQueens(board, maxQueens, bv); maxQueens++){
            
            bv.touch();

            // Zuruecksetzen des Boards
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    board[i][j] = 0;
                }
            }
        }
        // Es muss noch 1 abgezogen werden, da erst bei maxQueens = 9 die Bedigung der Schleife false ist und die Schleife abgebrochen wird
        return maxQueens - 1;
    }

    public static boolean countQueens(int[][] board, int n, BoardViewer bv){

        if (n == 0) {
            return true;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                
                if (checkValidationQueen(board, i, j, true) && board[i][j] == 0) {
                    board[i][j] = 2;
                    //bv.touch();
                    if (countQueens(board, n-1, bv)) {
                        return true;
                    }
                    board[i][j] = 0;
                    //bv.touch();
                }
            }
        }
        return false;
    }

    public static boolean checkValidationQueen(int[][] board, int row, int col, boolean isQueen){

        // Ceck Rows & Cols
        for (int i = 0; i < board.length; i++) {
            if (i == col) {
                continue;
            }
            // Check Cols
            // mit isQueen false wird geschaut, ob Figur von anderer Dame bedroht wird,
            // mit isQueen true wird geschaut, ob Dame andere Figuren bedroht
            if (board[row][i] >= (isQueen ? 1 : 2)) {
                return false;
            }
        }
        for (int i = 0; i < board.length; i++) {
            if (i == row) {
                continue;
            }

            // Check Rows
            if (board[i][col] != 0) {
                return false;
            }

        }

        // Check diagonals ( in jede der 4 Diagonalrichtungen wird geprueft)
        for (int i = row + 1, j = col + 1; i < board.length && j < board[0].length; i++, j++) {
            if (board[i][j] >= (isQueen ? 1 : 2)) {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] >= (isQueen ? 1 : 2)) {
                return false;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < board[0].length ; i--, j++ ) {
            if (board[i][j] >= (isQueen ? 1 : 2)) {
                return false;
            }
        }
        for (int i = row + 1, j = col - 1; i < board.length && j >= 0 ; i++, j--) {
            if (board[i][j] >= (isQueen ? 1 : 2)) {
                return false;
            }    
        }
        /*
            // Alternative
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (i + j == row + col || i - j == row - col) {
                        if (board[k][l] == 1) {
                            return true;
                        }
                    }
                }
            }
        */
        return true;
    }

    public static int countKnights(int[][] board, BoardViewer bv){

        int maxKnights = 0;
        for (; countKnights(board, maxKnights, bv); maxKnights++) {
            bv.touch();

            // Zuruecksetzen des Boards
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    board[i][j] = 0;
                }
            }
        }
        return maxKnights - 1;
    }

    public static boolean countKnights(int[][] board, int knights, BoardViewer bv){

        if (knights == 0) {
            return true;
        }

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                
                if (checkValidationKnight(board, row, col, true) && board[row][col] == 0) {
                    board[row][col] = 1;
                    if (countKnights(board, knights-1, bv)) {
                        return true;
                    }
                    board[row][col] = 0;  

                    board[row][col] = 1;
                    if (countKnights(board, knights-1, bv)) {
                        return true;
                    }
                    board[row][col] = 0;
                    
                } 
            }
        }
        return false;

    }

    public static boolean checkValidationKnight(int[][] board, int row, int col, boolean isKnight){

        if (row - 2 >= 0) {
            if (col - 1 >= 0) {
                if ((isKnight ? board[row - 2][col - 1] >= 1: board[row - 2][col - 1] == 1)) {
                    return false;
                }
            }
            if (col + 1 < board[row].length) {
                if ((isKnight ? board[row - 2][col + 1] >= 1: board[row - 2][col + 1] == 1)) {
                    return false;
                }
            }
        }

        if (row + 2 < board.length ) {
            if (col - 1 >= 0) {
                if ((isKnight ? board[row + 2][col - 1] >= 1: board[row + 2][col - 1] == 1)) {
                    return false;
                }
            }
            if (col + 1 < board[row].length) {
                if ((isKnight ? board[row + 2][col + 1] >= 1: board[row + 2][col + 1] == 1)) {
                    return false;
                }
            }
        }   

        if (row + 1 < board.length ) {
            if (col - 2 >= 0) {
                if ((isKnight ? board[row + 1][col - 2] >= 1: board[row + 1][col - 2] == 1)) {
                    return false;
                }
            }
           if (col + 2 < board[row].length) {
                if ((isKnight ? board[row + 1][col + 2] >= 1: board[row + 1][col + 2] == 1)) {
                    return false;
                }
            }
        }

        if (row - 1 >= 0) {
            if (col - 2 >= 0) {
                if ((isKnight ? board[row - 1][col - 2] >= 1: board[row - 1][col - 2] == 1)) {
                    return false;
                }
            }
            if (col + 2 < board[row].length) {
                if (isKnight ? board[row - 1][col + 2] >= 1 : board[row - 1][col + 2] == 1) {
                    return false;
                }
            }
        }

        return true;
    
    }

    // Zur Beschleunigung des Prozesses wird ueberprueft, ob bis zur Haelfte des Feldes mintdestens
    // auch die Haelfte der Figuren gesetzt wurde
    public static boolean checkHalf(int[][] board, int figuresNeeded){
        int count = 0;
        for (int i = 0; i < board.length/2; i++) {
            for (int j = 0; j < board[i].length; j++) {
                count += board[i][j] != 0 ? 1 : 0;
            }
        }
        return (count >= figuresNeeded/2);
    }


    public static int countQueensAndKnights(int[][] board, BoardViewer bv){

        figuresToPlace = 0;
        for (; countQueensAndKnights(board, figuresToPlace, bv); figuresToPlace++) {
            bv.touch();

            // Zuruecksetzen des Boards
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    board[i][j] = 0;
                }
            }
        }
        return figuresToPlace - 1;
    }


    public static boolean countQueensAndKnights(int[][] board, int figures, BoardViewer bv){
        if (figures == 0) {
            return true;
        }

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                
                if (board[row][col] == 0) {
                    // Wenn Gesamtzahl gerade -> Springer bei mod 0 setzen
                    if (figuresToPlace % 2 != 0) {
                        if (figures % 2 == 0) {
                            if (checkValidationKnight(board, row, col, true) && checkValidationQueen(board, row, col, false)) {
                                board[row][col] = 1;
                                if (countQueensAndKnights(board, figures-1, bv)) {
                                    return true;
                                }
                            }
                        }else{
                            if (checkValidationKnight(board, row, col, false) && checkValidationQueen(board, row, col, true)) {
                                board[row][col] = 2;
                                if (countQueensAndKnights(board, figures-1, bv)) {
                                    return true;
                                }
                            }
                        }
                    }else{
                        // Sonst Springer bei mod 1 setzen
                        if (figures % 2 == 1) {
                            if (checkValidationKnight(board, row, col, true) && checkValidationQueen(board, row, col, false)) {
                                board[row][col] = 1;
                                if (countQueensAndKnights(board, figures-1, bv)) {
                                    return true;
                                }
                            }
                        }else{
                            if (checkValidationKnight(board, row, col, false) && checkValidationQueen(board, row, col, true)) {
                                board[row][col] = 2;
                                if (countQueensAndKnights(board, figures-1, bv)) {
                                    return true;
                                }
                            }
                        }
                    }
                board[row][col] = 0;
                }
            }
        }
        return false;
    }


} // END Exercise01
