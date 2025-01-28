class Palindrome {
    public static void main(String[] args) {
        String s = "ababbbabbababa";
        System.out.println(palindromicPartition(s));
    }

    static int palindromicPartition(String s) {

        int n = s.length();
        if(s.length() == 1) return 0;

        boolean[][] palindrome = new boolean[n][n];

        for(int i=n-1; i>=0; i--) {
            for(int j=i; j<n; j++) {
                if(i == j) {
                    palindrome[i][j] = true;
                } else if (j == i+1) {
                    if(s.charAt(i) == s.charAt(j)) {
                        palindrome[i][j] = true;
                    } else {
                        palindrome[i][j] = false;
                    }
                } else {
                    if(s.charAt(i) == s.charAt(j)) {
                        palindrome[i][j] = palindrome[i+1][j-1];
                    } else {
                        palindrome[i][j] = false;
                    }
                }
            }
        }

        int[] dp = new int[n];
        dp[0] = 0;

        for(int j=1; j<n; j++) {
            if(palindrome[0][j]) {
                dp[j] = 0;
            } else {
                int min = Integer.MAX_VALUE;

                for(int i=j; i>=1; i--) {
                    if(palindrome[i][j]) {
                        if(dp[i-1] < min) {
                            min = dp[i-1];
                        }
                    }
                }
                dp[j] = min + 1;
            }
        }

        return dp[n-1];
    }
}
