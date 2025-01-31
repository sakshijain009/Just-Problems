class Palindrome {
    public static void main(String[] args) {
        String s = "abcdba";
        System.out.println(countPS(s));
    }

    static int countPS(String s) {

        int n = s.length();
        if(n==1) return 1;

        int[][] dp = new int[n][n];

        for(int g=0; g<n; g++) {
            for(int i=0,j=g; j<n; i++,j++) {
                if(g==0) {
                    dp[i][j] = 1;
                } else if (g==1) {
                    if(s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = 3;
                    } else {
                        dp[i][j] = 2;
                    }
                } else {
                    if(s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i][j-1] + dp[i+1][j] + 1;
                    } else {
                        dp[i][j] = dp[i][j-1] + dp[i+1][j] - dp[i+1][j-1];
                    }
                }
            }
        }

        return dp[0][n-1];
    }
}
