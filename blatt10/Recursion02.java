public class Recursion02 {

    public static void main(String[] args) {

        System.out.println("Test Palindrome");
        System.out.println("OTTO: " + isPalindrome("OTTO"));
        System.out.println("REGINEWETTEWENIGER: " + isPalindrome("REGINEWETTEWENIGER"));
        System.out.println("EIDI1: " + isPalindrome("EIDI1"));


        System.out.println("\nTest Heron");
        double[] ds = new double[]{-2, -0.1, 0.5, 1, 1.25, 1.5, 1.75, 2, 3, 5, 25};
        for (double d : ds) {
            heronTr(2, d);
            System.out.println();
        }
    }

    private static boolean isPalindrome(String s) {
        //TODO: a)

        if (s.length() == 0 || s.length() == 1) {
            return true;
        }else{
            if (s.charAt(0) == s.charAt(s.length() - 1)) {
                return isPalindrome(s.substring(1, s.length() - 1));
            }
        }
        return false;
    }

    private static void heronTr(double a, double x0) {
        //TODO: b)

        System.out.println(heronTrHelp(a, x0));
    }

    private static double heronTrHelp(double a, double xn){

        // xn1 := x_{n+1}
        
        double xn1 = 0.5*(xn + a/xn);
        if (Math.abs(xn1 - xn) < Math.pow(10, -8)) {
            return xn1;
        }else{
            return heronTrHelp(a, xn1);
        }
    }
}