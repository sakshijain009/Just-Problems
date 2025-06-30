class Solution {

    /*
    This solution finds the maximum sum rectangle in a 2D matrix using a combination of prefix sum and Kadane's algorithm.

    🔍 Logic:
    - We fix pairs of columns (left to right) and compress the 2D matrix into a 1D array where each element represents the sum of elements in that row between the chosen columns.
    - For each column pair, we apply Kadane’s algorithm on this 1D array to find the maximum sum subarray, which corresponds to a subrectangle in the matrix.
    - We repeat this for all column pairs to find the rectangle with the maximum sum.

    🧪 Dry Run Example:
    Given matrix:
    [1,  2, -1]
    [-3, 4,  5]
    [2, -1,  2]

    Step 1: Fix leftCol = 0
        rightCol = 0 ➝ sum[] = [1, -3, 2] ➝ kadane = 2
        rightCol = 1 ➝ sum[] = [3, 1, 1] ➝ kadane = 5
        rightCol = 2 ➝ sum[] = [2, 6, 3] ➝ kadane = 11

    Step 2: Fix leftCol = 1
        rightCol = 1 ➝ sum[] = [2, 4, -1] ➝ kadane = 6
        rightCol = 2 ➝ sum[] = [1, 9, 1] ➝ kadane = 11

    Step 3: Fix leftCol = 2
        rightCol = 2 ➝ sum[] = [-1, 5, 2] ➝ kadane = 6

    ✅ Final Answer = 11 (subrectangle from rows 1-2, cols 1-2)

    Time Complexity: O(cols² * rows)
    */

    int maximumSumRectangle(int mat[][]) {
        int c = mat[0].length; // number of columns
        int r = mat.length;    // number of rows
        int Omax = Integer.MIN_VALUE; // stores the overall maximum sum found
        
        // Loop over all possible pairs of starting and ending columns
        for(int j = 0; j < c; j++) {
            int[] sum = new int[r]; // compressed row sums
            
            for(int i = j; i < c; i++) {
                // Build row-wise sums between column j and i
                for(int row = 0; row < r; row++) {
                    sum[row] += mat[row][i];
                }

                // Apply Kadane’s algorithm on compressed row array
                int max = Kadane(sum);
                Omax = Math.max(max, Omax);
            }
        }
        
        return Omax;
    }

    // Kadane’s algorithm to find max subarray sum in 1D array
    public int Kadane(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        int max = arr[0];
        
        for(int i = 1; i < arr.length; i++) {
            dp[i] = Math.max(arr[i], arr[i] + dp[i-1]);
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }
}
