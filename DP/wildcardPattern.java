//Wildcard Pattern Matching
// Regex can have: char,*,?
// ? - matches any single character
// * - matches any group of character
import java.util.*;

class Testclass {
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        String s1 = s.nextLine(); //string
        String s2 = s.nextLine(); //regex

        //Creating dp array for memoization
        boolean[][] dp = new boolean[s2.length()+1][s1.length()+1];

        for (int i = s2.length(); i >= 0; i--) {
            for (int j = s1.length(); j >= 0; j--) {
                if(i == s2.length() && j == s1.length()){
                    dp[i][j] = true;
                }else if(i == s2.length()){
                    dp[i][j] = false;
                }else if(j == s1.length()){
                    if(s2.charAt(i) == '*'){
                        dp[i][j] = dp[i+1][j];
                    }else{
                        dp[i][j] = false;
                    }
                }else{
                    if (s1.charAt(j) == s2.charAt(i)){
                        dp[i][j] = dp[i+1][j+1];
                    }else if(s2.charAt(i) == '?'){
                        dp[i][j] = dp[i+1][j+1];
                    }else if(s2.charAt(i) == '*'){
                        dp[i][j] = dp[i+1][j] | dp[i][j+1];
                    }else{
                        dp[i][j] = false;
                    }
                }
            }
        }

        System.out.println(dp[0][0]);
    }
}
