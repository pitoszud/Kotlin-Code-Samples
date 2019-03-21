package Algorithms;

public class FromJtoK {
    public static void main(String[] args) {
        method("Patryk Jakubik");
    }

    public static boolean method(String str) {
        if (str.length() > 128) return false;

        boolean[] charSet = new boolean[128];
        for (int i = 0; i <str.length() ; i++) {
            int val = str.charAt(i);
            System.out.println("check " + val);
            if(charSet[val]){
                System.out.println("already exists - return false");
                return false;
            }
            System.out.println("update " + charSet[val] + " to true");
            charSet[val] = true;
        }
        return true;
    }
}
