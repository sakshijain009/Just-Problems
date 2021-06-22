import java.util.*;

class Testclass {
    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] weights = new int[n]; //number of elements
        int[] count = new int[n];

        //Weight of ith number elements
        for (int i = 0; i < n; i++) {
            weights[i] = s.nextInt();
        }

        //Number of items in ith element
        for (int i = 0; i < n; i++) {
            count[i] = s.nextInt();
        }
        int tar = s.nextInt();
        //Create dp array
        int[][] dp = new int[n+1][tar+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= tar; j++) {
                if (count[i-1]<= j){
                    dp[i][j] = Math.max(dp[i-1][j],weights[i-1]+dp[i-1][j-count[i-1]]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        //output
        System.out.println(dp[n][tar]);

    }
}
