import java.math.BigInteger;


public class Recursion01 {

    public static void main(String[] args) {
        System.out.println("Test factorial: 0! = 1");
        System.out.println("iterativ: " + facIt(0));
        System.out.println("head: " + facHr(0));
        System.out.println("tail: " + facTr(0));

        System.out.println("\nTest factorial: 5! = 120");
        System.out.println("iterativ: " + facIt(5));
        System.out.println("head: " + facHr(5));
        System.out.println("tail: " + facTr(5));

        System.out.println("\nTest factorial: 10! = 3628800");
        System.out.println("iterativ: " + facIt(10));
        System.out.println("head: " + facHr(10));
        System.out.println("tail: " + facTr(10));

        System.out.println("\nTest binom(4,2) = 6");
        System.out.println("iterativ: " + binomIt(4, 2));
        System.out.println("head: " + binomHr(4, 2));
        System.out.println("tail: " + binomTr(4, 2));

        System.out.println("\nTest binom(11,5) = 462");
        System.out.println("iterativ: " + binomIt(11, 5));
        System.out.println("head: " + binomHr(11, 5));
        System.out.println("tail: " + binomTr(11, 5));

        System.out.println("\nTest binom(49,6) = 13983816");
        System.out.println("head: " + binomHr(49, 6));
        System.out.println("tail: " + binomTr(49, 6));

        System.out.println("\nTest binom(49,43) = 13983816");
        System.out.println("head: " + binomHr(49, 43));
        System.out.println("tail: " + binomTr(49, 43));

        System.out.println("\nTest bigFacTr(42) = ");
        System.out.println("Ergebnis: " + bigFacTr(42).toString());

        System.out.println("\nTest bigFacTr(50) = ");
        System.out.println("Ergebnis: " + bigFacTr(50).toString());

    }

    private static int facIt(int n) {
        int fac = 1;

        for (int i = 1; i <= n; i++) {
            fac *= i;
        }
        return fac;
    }

    private static int facHr(int n) {
        //TODO: a)
        return n <= 1 ? 1 : n * facHr(n - 1);
    }


    private static int facTr(int n) {
        //TODO: b)
        return facTrHelp(n, 1);
    }


    private static int facTrHelp(int n, int total){
    	if (n <= 1) {
    		return total;
    	}else{
    		return facTrHelp(n - 1, total * n);
    	}
    }

    /*
    *	c)
    *	Bei Headrekursion muss jeder rekursive Ausruf zuerst ausgefuehrt werden, bevor der Java-Interpreter den kompletten Term evaluieren kann, 
    * 	wohingegen bei Tailrekursion der Ausdruck zuerst evaluiert wird und mit diesem Ergebnis die Methode rekursiv aufgerufen wird. 
    */

    private static int binomIt(int n, int k) {
        if (n >= k && k >= 0) {
            return facIt(n) / (facIt(k) * facIt(n - k));
        }
        return -1;
    }

    private static int binomHr(int n, int k) {
        if (n < k || k < 0) {
        	return -1;
        }

        if (k == 0 || n == k) {
        	return 1;
        }else{
        	return binomHr(n - 1, k - 1) + binomHr(n - 1, k);
        }
    }

    private static int binomTr(int n, int k) {
        //TODO: e)
    	if (n < k || k < 0) {
        	return -1;
        }
        return binomTrHelp(n, k, 1);
    }


    private static int binomTrHelp(int n, int k, double total){

        if (k == 1) {
            return (int) Math.round(total*n);
        }
        return binomTrHelp(n-1, k-1, total*n/k);

    }

    public static BigInteger bigFacTr(int n){

        // BigInteger number = new BigInteger(String.valueOf(n));
        return bigFacTrHelp(BigInteger.valueOf(n), new BigInteger("1"));
    }

    public static BigInteger bigFacTrHelp(BigInteger n, BigInteger total){

        if (n.compareTo(BigInteger.valueOf(1)) <= 0) {
            return total;
        }else{
            return bigFacTrHelp(n.subtract(BigInteger.valueOf(1)), total.multiply(n));
        }
    }
}