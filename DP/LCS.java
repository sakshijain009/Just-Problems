//Longest Common Subsequence
import java.util.*;

class Testclass {
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        String s1 = s.nextLine();
        String s2 = s.nextLine();

        //Creating dp array for memoization
        int[][] dp = new int[s2.length()+1][s1.length()+1];

        for (int i = s2.length()-1; i >= 0; i--) {
            for (int j = s1.length() -1; j >= 0; j--) {
                if (s1.charAt(j)==s2.charAt(i)){
                    dp[i][j] = dp[i+1][j+1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j+1]);
                }
            }
        }

        System.out.println(dp[0][0]);
    }
}
