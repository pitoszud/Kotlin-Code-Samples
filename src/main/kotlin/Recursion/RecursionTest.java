package Recursion;

public class RecursionTest {
    public static void main(String[] args) {

    }

    public double power1(double a, int n){
        if (n == 1){
            return 1.0;
        }else if (n > 0){
            return a * power1(a, n-1);
        }else{
            return power1(a, n-1);
        }
    }
}
