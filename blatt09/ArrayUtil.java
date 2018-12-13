import java.util.Arrays;

public class ArrayUtil {

    public static void main(String[] args) {

        double[] arr1 = {1, 2, 3, 4, 5};
        double[] arr2 = {5, 4, 3, 2, 1};
        double[] arr3 = randomArray(1000);

        System.out.printf("arr1: %s%n", array2String(arr1));
        System.out.printf("arr2: %s%n", array2String(arr2));
        System.out.printf("arr3: %s%n", array2String(arr3));
        System.out.println();

        //a) sum
        System.out.printf("Sum: %.2f\t (should: %2.2f)%n", sum(arr1), Arrays.stream(arr1).sum());
        System.out.printf("Sum: %.2f\t (should: %2.2f)%n", sum(arr2), Arrays.stream(arr2).sum());
        System.out.printf("Sum: %.2f\t (should: %2.2f)%n", sum(arr3), Arrays.stream(arr3).sum());
        System.out.println();

        //b) avg
        System.out.printf("Average: %.2f\t (should: %2.2f)%n", avg(arr1), Arrays.stream(arr1).average().getAsDouble());
        System.out.printf("Average: %.2f\t (should: %2.2f)%n", avg(arr2), Arrays.stream(arr2).average().getAsDouble());
        System.out.printf("Average: %.2f\t (should: %2.2f)%n", avg(arr3), Arrays.stream(arr3).average().getAsDouble());
        System.out.println();

        //c) max
        System.out.printf("Max: %.2f\t (should: %2.2f)%n", max(arr1), Arrays.stream(arr1).max().getAsDouble());
        System.out.printf("Max: %.2f\t (should: %2.2f)%n", max(arr2), Arrays.stream(arr2).max().getAsDouble());
        System.out.printf("Max: %.2f\t (should: %2.2f)%n", max(arr3), Arrays.stream(arr3).max().getAsDouble());
        System.out.println();

        //d) min
        System.out.printf("Min: %.2f\t (should: %2.2f)%n", min(arr1), Arrays.stream(arr1).min().getAsDouble());
        System.out.printf("Min: %.2f\t (should: %2.2f)%n", min(arr2), Arrays.stream(arr2).min().getAsDouble());
        System.out.printf("Min: %.2f\t (should: %2.2f)%n", min(arr3), Arrays.stream(arr3).min().getAsDouble());
        System.out.println();

        //e) sort
        sort(arr1);
        sort(arr2);
        sort(arr3);
        System.out.printf("Sorted: %s%n", array2String(arr1));
        System.out.printf("Sorted: %s%n", array2String(arr2));
        System.out.printf("Sorted: %s%n", array2String(arr3));
        System.out.println();

        //g) isSorted
        double[] arrUnsorted = randomArray(1000);
        System.out.printf("isSorted arr1: %s%n", isSortedDesc(arr1));
        System.out.printf("isSorted arr2: %s%n", isSortedDesc(arr2));
        System.out.printf("isSorted arr3: %s%n", isSortedDesc(arr3));
        System.out.printf("isSorted arrUnsorted: %s%n", isSortedDesc(arrUnsorted));
    }




    //a)
    private static double sum(double[] arr) {

        double sum = 0.0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        return sum;
    }



    //b)
    private static double avg(double[] arr) {

        if (arr == null || arr.length == 0) {
            return Double.NaN;
        }

        double sum = sum(arr);

        return sum / arr.length ; 
    }



    //c)
    private static double max(double[] arr) {

        if (arr == null || arr.length == 0) {
            return Double.NaN;
        }

        double max = Double.NEGATIVE_INFINITY;
        // oder: double max = arr[0];

        
        for (int i = 0; i < arr.length; i++) {
            max = arr[i] > max ? arr[i] : max;
        }

        return max; 
    }



    //d)
    private static double min(double[] arr) {

        if (arr == null || arr.length == 0) {
            return Double.NaN;
        }

        double min = Double.POSITIVE_INFINITY;
        // oder: double min = arr[0];


        for (int i = 0; i < arr.length ; i++) {
            min = arr[i] < min ? arr[i] : min;
        }

        return min; 
    }




    //e)
    private static void sort(double[] arr) {
        //TODO

        for (int i = 0; i <= arr.length - 2 ; i++) {
            for (int j = i + 1; j <= arr.length - 1; j++) {

                if (arr[i] < arr[j]) {
                    
                    double buffer = arr[j];

                    arr[j] = arr[i];
                    arr[i] = buffer;
                }
            }
            
        }


    }


    /*
    *   f) Die Methode funktioniert, da eigentlich keine Rueckgabe erfolgt. Es werden lediglich die Werte umsortiert.
    *      Dabei ist 'arr' eine Referenz auf das zu Beginn erstelle Objekt des Feldes, das bedeutet, wenn die Aenderung 
    *      von Daten in diesem Array ueber die Referenz erfolgt, werden diese Aenderungen am eigentlichen Objekt vorgenommen.
    *      Diese sind somit fuer alle anderen Referenzen 'sichtbar'.
    */



    //g)
    private static boolean isSortedDesc(double[] arr) {
        
        // bis arr.length - 2, da arr[arr.length - 1] der letzte zu vergleichende Eintrag
        for (int i = 0; i < arr.length - 1 ; i++) {
            if (arr[i + 1] > arr[i]) {
                return false;
            }
        }

        return true; 
    }


    //h)
    private static double median(double[] arr) {

        if (arr == null || arr.length == 0 || !isSortedDesc(arr)) {
            return Double.NaN;
        }

        int mitte = arr.length / 2;

        return arr[mitte]; 
    }




    //vvvvv Blackbox vvvvv
    private static double[] randomArray(int n) {

        double[] arr = new double[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.random() - 0.5;
        }

        return arr;
    }

    private static String array2String(double[] arr) {
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(String.format("%.4f", arr[i]));
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");

        return new String(sb);
    }
}