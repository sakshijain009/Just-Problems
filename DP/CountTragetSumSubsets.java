/*
Perfect Sum Problem
Difficulty: Medium  Accuracy: 20.58%  Submissions: 549K+  Points: 4

Given an array arr of non-negative integers (which may include zeros)
and an integer target, count all subsets of the array whose sum equals the target.

Example with a zero element:
  nums  = [0, 1]
  target = 1

  All subsets:
    {}     → sum=0
    {0}    → sum=0
    {1}    → sum=1
    {0,1}  → sum=1

  There are 2 subsets that sum to 1: {1} and {0,1}.
*/

public class PerfectSum {

    /**
     * Returns the number of subsets of nums[] that sum to target.
     */
    public static int perfectSum(int[] nums, int target) {
        int n = nums.length;
        // dp[i][j] = number of ways to pick from first i elements to get sum j
        int[][] dp = new int[n + 1][target + 1];

        // Base case: one way to make sum=0 with any number of items (pick none)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        // Fill the table
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                // exclude nums[i-1]
                dp[i][j] = dp[i - 1][j];
                // include nums[i-1] if it does not exceed current sum j
                if (j >= nums[i - 1]) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[n][target];
    }

    /**
     * Main method to demonstrate the solution and dry run the example.
     */
    public static void main(String[] args) {
        int[] nums = {0, 1};
        int target = 1;
        int count = perfectSum(nums, target);
        System.out.println("Number of subsets summing to " + target + ": " + count);
        
        /*
        Dry run DP table for nums = [0,1], target = 1:

            j →    0    1
        i=0       1    0    // no items: one way to make 0, zero ways to make 1
        i=1 (0)   2    0    // include/exclude 0 doubles ways for sum=0
                      dp[1][0] = dp[0][0] + dp[0][0] = 1 + 1 = 2
                      dp[1][1] = dp[0][1] + dp[0][1] = 0 + 0 = 0
        i=2 (1)   2    2    // include/exclude 1 adds dp[1][0] to dp[1][1]
                      dp[2][0] = dp[1][0] = 2
                      dp[2][1] = dp[1][1] + dp[1][0] = 0 + 2 = 2

        Final answer dp[2][1] = 2
        */
    }
}
