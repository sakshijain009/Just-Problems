import java.util.*;

class Testclass {
    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = s.nextInt();
        }
        int tar = s.nextInt();

        // TOTAL COMBINATIONS TO PAY THE AMOUNT
        int[] countC = new int[tar+1];
        countC[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j < countC.length; j++) {
                countC[j] += countC[j-coins[i]];
            }
        }
        System.out.println("Combinations "+countC[tar]);

        // TOTAL PERMUTATIONS TO PAY THE AMOUNT
        int[] countP = new int[tar+1];
        countP[0] = 1;

        for (int i = 1; i < countP.length; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j]<=i){
                    countP[i]+=countP[i-coins[j]];
                }
            }
        }
        System.out.println("Permutations "+countP[tar]);
    }
}
