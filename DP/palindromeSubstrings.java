//Palindromic substrings in a string
import java.util.*;

class Testclass {
    public static void main(String args[]){
        Scanner scn = new Scanner(System.in);
        String s = scn.nextLine(); //string

        //Creating dp array for memoization
        int[][] dp = new int[s.length()][s.length()];
        int count = 0;

        // g is GAP
        for(int g=0;g<s.length();g++){
            for(int i=0,j=g;j<s.length();i++,j++){
                if(g==0){
                    dp[i][j] = 1;
                    count++;
                }
                else if(g==1){
                    if(s.charAt(i)==s.charAt(j)){
                        dp[i][j] = 1;
                        count++;
                    }
                }else{
                    if(s.charAt(i)==s.charAt(j)){
                        dp[i][j] = dp[i+1][j-1];
                        count = dp[i][j]==1?count+1:count;
                    }
                }
            }
        }

        System.out.println(count);
    }
}
