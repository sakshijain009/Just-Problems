/*
Question:
Given two strings s1 and s2, find the length of the smallest string which has both s1 and s2 
as its subsequences.

Examples:
Input: s1 = "geek", s2 = "eke"
Output: 5
Explanation: String "geeke" has both string "geek" and "eke" as subsequences.

Input: s1 = "AGGTAB", s2 = "GXTXAYB"
Output: 9
Explanation: String "AGXGTXAYB" has both string "AGGTAB" and "GXTXAYB" as subsequences.

Input: s1 = "geek", s2 = "ek"
Output: 4
Explanation: String "geek" has both string "geek" and "ek" as subsequences.
*/

class ShortestCommonSupersequence {
    
    // Function to calculate length of shortest common supersequence
    public int shortestCommonSupersequence(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // DP array for LCS
        int[][] dp = new int[m + 1][n + 1];

        // Fill dp for LCS
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int lcs = dp[m][n]; // length of longest common subsequence

        // Formula
        return m + n - lcs;
    }

    public static void main(String[] args) {
        ShortestCommonSupersequence sol = new ShortestCommonSupersequence();

        // Dry Run Example: s1 = "geek", s2 = "eke"
        // m = 4, n = 3
        // LCS("geek", "eke") = "ek" → length = 2
        // SCS length = 4 + 3 - 2 = 5
        System.out.println(sol.shortestCommonSupersequence("geek", "eke")); // 5

        // Example 2: s1 = "AGGTAB", s2 = "GXTXAYB"
        // LCS = "GTAB" → length = 4
        // SCS length = 6 + 7 - 4 = 9
        System.out.println(sol.shortestCommonSupersequence("AGGTAB", "GXTXAYB")); // 9

        // Example 3: s1 = "geek", s2 = "ek"
        // LCS = "ek" → length = 2
        // SCS length = 4 + 2 - 2 = 4
        System.out.println(sol.shortestCommonSupersequence("geek", "ek")); // 4
    }
}
