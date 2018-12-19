public class PuzzleUtil {
    public static void main(String[] args) {
        int[] arr1 = new int[]{25, 42, 69, 17};

        System.out.println("arr1: ");
        view(arr1);

        System.out.println("\narr1 shiftRight: ");
        shiftRight(arr1);
        view(arr1);

        System.out.println("\narr1 shiftLeft: ");
        shiftLeft(arr1);
        view(arr1);

        System.out.println("\narr1 shiftLeft: ");
        shiftLeft(arr1);
        view(arr1);

        System.out.println("\narr1 shiftRight: ");
        shiftRight(arr1);
        view(arr1);

        int[][] arr2 = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("\narr2t: ");
        view(arr2);

        System.out.println("\narr2 shiftRowsRight(1,3): ");
        shiftRows(arr2, 1, 3, false);
        view(arr2);

        System.out.println("\narr2 shiftRowsLeft(1,3): ");
        shiftRows(arr2, 1, 3, true);
        view(arr2);



        System.out.println("\narr2 shiftColsDown(1,3): ");
        shiftCols(arr2, 1, 3, true);
        view(arr2);

        System.out.println("\narr2 shiftColsUp(1,3): ");
        shiftCols(arr2, 1, 3, false);
        view(arr2);



        System.out.println("\narr2 extractArray(2,4,2,4): ");
        view(extractArray(arr2, 2, 4, 2, 4));

        System.out.println("\narr2 extractArray(0,2,0,2): ");
        view(extractArray(arr2, 0, 2, 0, 2));

        System.out.println("\narr2 extractArray(0,2,0,3): ");
        view(extractArray(arr2, 0, 2, 0, 3));



        System.out.println("\narr2 rotateClockwise: ");
        rotateClockwise(arr2);
        view(arr2);


        // Rotate Clockwise bigger Array

        int[][] arr3 = new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {8, 7, 6, 5}, {4, 3, 2, 1}};
        System.out.println("\narr3 rotateClockwise: ");
        view(arr3);


        System.out.println("\narr3 rotateClockwise: ");
        rotateClockwise(arr3);
        view(arr3);


        /*

        System.out.println("\narr2 replaceInArray with zeros: ");
        replaceInArray(arr2, new int[][]{{0, 0}, {0, 0}}, 1, 1);
        view(arr2);

        System.out.println("\narr2 replaceInArray with zeros: ");
        replaceInArray(arr2, new int[][]{{0, 0}, {0, 0}}, 0, 0);
        view(arr2);

        */

        System.out.println("\narr2 partialRotateClockwise(0,2,0,2): ");
        partialRotateClockwise(arr2, 0, 2, 0, 2);
        view(arr2);
    }





    //a)
    public static void shiftRight(int[] array) {
        //TODO

        int buffer = array[array.length - 1];

        for (int i = array.length - 1; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = buffer;

    }


    //b)
    public static void shiftLeft(int[] array) {
        
        int buffer = array[0];

        for (int i = 0; i < array.length - 1 ; i++ ) {
            array[i] = array[i + 1];
        }
        array[array.length - 1] = buffer;
    }



    //c)
    public static void shiftRows(int[][] array, int rowStart, int rowEnd, boolean shiftLeft) {
        
        for (int i = rowStart ; i < rowEnd; i++) {
            if (shiftLeft) {
                shiftLeft(array[i]);
            }else{
                shiftRight(array[i]);
            }
        }

    }



    //d)
    public static void shiftCols(int[][] array, int colStart, int colEnd, boolean shiftDown) {
        
        for (int i = colStart ; i < colEnd ; i++ ) {
            
            // Fehler in der Angabe, deshalb negiert
            if (!shiftDown) {

                int buffer = array[array.length - 1][i];
                for (int j = array.length - 1 ; j > 0 ; j-- ) {
                        array[j][i] = array[j - 1][i];
                    }    

                array[0][i] = buffer;

            }else{

                int buffer = array[0][i];
                for (int j = 0; j < array.length - 1; j++ ) {
                    array[j][i] = array[j + 1][i];
                }

                array[array.length - 1][i] = buffer;

            }
        }

    }


    //e)
    public static int[][] extractArray(int[][] array, int rowStart, int rowEnd, int colStart, int colEnd) {
        
        // Wohldefiniertheit der Eingabeparameter
        rowStart = rowStart < 0 ? 0 : rowStart;
        rowEnd = rowEnd > array[0].length ? array[0].length : rowEnd;

        colStart = colStart < 0 ? 0 : colStart;
        colEnd = colEnd > array.length ? array.length : colEnd;


        // Neues Array wird erzeugt
        int[][] extracted = new int[rowEnd - rowStart][colEnd - colStart];


        for (int rowOld = rowStart, rowNew = 0 ; rowOld < rowEnd ; rowOld++, rowNew++) {
            for (int colOld = colStart, colNew = 0 ; colOld < colEnd ; colOld++, colNew++) {
                
                extracted[rowNew][colNew] = array[rowOld][colOld];
            }
        }

        return extracted;
    }



    //f)
    public static void replaceInArray(int[][] array, int[][] delta, int rowIndex, int colIndex) {
       
       for (int i = rowIndex, k = 0 ; k < delta[0].length && i < array[0].length ; i++, k++) {
            for (int j = colIndex, l = 0 ; l < delta.length && l < array.length ; j++, l++ ) {
                
                array[i][j] = delta[k][l];
            }
        } 
    }



    //g)
    public static void rotateClockwise(int[][] array) {
        
        int[][] original = new int[array.length][array.length];

        // Werte von 'array' in 'original' uebertragen
        for (int i = 0; i < array.length; i++) {
            for (int j = 0 ; j < array.length; j++) {
                original[i][j] = array[i][j];
            }
        }

        for (int i = 0 ; i < original.length ; i++ ) {
            for (int j = 0; j < original.length ; j++ ) {
                
                array[i][j] = original[array.length - 1 - j][i];

            }
        }  
    }



    //h)
    public static void partialRotateClockwise(int[][] array, int rowStart, int rowEnd, int colStart, int colEnd) {
        
        int[][] partArray = extractArray(array, rowStart, rowEnd, colStart, colEnd);
        rotateClockwise(partArray);

        replaceInArray(array, partArray, rowStart, colStart);


    }







    //vvvvv Blackbox vvvvv
    public static void view(int[] array) {

        System.out.print("[");

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i != array.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }

    private static void view(int[][] array) {

        System.out.println("2d-Array:");

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
