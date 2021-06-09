import java.util.*;

class Fib {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int fibn = fib(n);
        System.out.println("Recursion: "+fibn);
        fibn = fibDP(n,new int[n+1]);
        System.out.println("DP: "+fibn);
    }

    // Recursion
    public static int fib(int n){
        if (n==0 || n==1) return n;

        return fib(n-1)+fib(n-2);
    }

    // DP
    public static int fibDP(int n,int[] store){
        if (n==0 || n==1) return n;

        if (store[n] != 0){
            return store[n];
        }
        int fibans = fibDP(n-1,store)+fibDP(n-2,store);
        store[n] = fibans;
        return fibans;
    }
}




