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

        int[] count = new int[tar+1];
        count[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j < count.length; j++) {
                count[j] += count[j-coins[i]];
            }
        }
        System.out.println(count[count.length-1]);
    }
}
