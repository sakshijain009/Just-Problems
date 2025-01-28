class Palindrome {
    public static void main(String[] args) {
        String s = "abcdba";
        System.out.println(longestPalinSubseq(s));
    }

    public static int longestPalinSubseq(String S)
    {
        int l = S.length();
        int[][] dp = new int[l][l];

        for(int g=0;g<l;g++){
            for(int i=0,j=g; j<l; j++,i++){
                if(g==0) dp[i][j] = 1;
                else{
                    if(S.charAt(i) == S.charAt(j)){
                        dp[i][j] = dp[i+1][j-1] + 2;
                    }else{
                        dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                    }
                }
            }
        }
        return dp[0][l-1];
    }
}
