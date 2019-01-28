import java.util.Random;
import java.util.Scanner;

public class GuessNumber {

    private static Random randomGen = new Random();

    public static void main(String[] args) {

        int n = 1;
        int m = 100;

        int random = randomGen.nextInt(m + 1 - n) + n;

        //guessNumber(n, m, random);

        System.out.println("Gesuchte Zahl: " + random);
        linearSearch(n, m, random);
        binarySearch(n, m, random);
        randomizedSearch(n, m, random);
    }

    private static void guessNumber(int n, int m, int random) {

        Scanner scanner = new Scanner(System.in);

        int guess;
        int counter = 1;
        do {
            System.out.printf("Bitte raten Sie eine Zahl im Intervall [%d, %d]: ", n, m);
            guess = scanner.nextInt();
            System.out.println();
            counter++;

            if (guess < random) {
                System.out.println("Gesuchte Zahl ist größer!");
            } else if (guess > random) {
                System.out.println("Gesuchte Zahl ist kleiner!");
            }

        } while (guess != random);

        System.out.printf("Sie haben %d Versuche benötigt%n", counter);
    }

    private static void linearSearch(int n, int m, int random) {
        //TODO: c)
    	int count = 1;
        for (int i = n; i <= m; i++) {
        	if (i == random) {
        		System.out.println("[linearSearch] Gefunden! Versuche: " + count);
        		break;
        	}
   			count++;
        }
    }

    private static void binarySearch(int l, int r, int random) {
        //TODO: d)

    	int x = (l + r) / 2;
    	int count = 1;


    	while (true){
    		if (x == random) {
    			break;
    		}

    		if (x < random) {
    			l = x + 1;
    		}else{
    			r = x - 1;
    		}
    		x = (l + r) / 2;
    		count++;
    	}

        System.out.println("[binarySearch] Gefunden! Versuche: " + count);

    }

    private static void randomizedSearch(int l, int r, int random) {
        //TODO: e)

    	int x = randomGen.nextInt(r + 1 - l);;
    	int count = 0;
    	while (true){
    		if (x == random) {
    			break;
    		}

    		if (x < random) {
    			l = x;
    		}else{
    			r = x;
    		}
    		x = randomGen.nextInt(r + 1 - l);
    		count++;
    	}

    	System.out.println("[RandomizedSearch] Gefunden! Versuche: " + count);
    	
    }
}