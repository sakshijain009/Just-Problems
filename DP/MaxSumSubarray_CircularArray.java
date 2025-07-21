/**
 * ✅ Problem: Maximum Subarray Sum in Circular Array
 * 
 * Given a circular array of integers `arr[]`, return the maximum possible sum
 * of a non-empty subarray. The subarray can wrap around the end to the beginning.
 * 
 * There are two possible cases:
 * 1. Maximum sum subarray is **non-wrapping** → use Kadane's algorithm.
 * 2. Maximum sum subarray is **wrapping** → total sum - minimum subarray sum.
 * 
 * Final answer = max(normal Kadane sum, total - min subarray sum)
 * 
 * 🔴 Edge Case:
 * - If all numbers are negative, the wrapping case will incorrectly give 0.
 *   So we only return maxKadane in that case.
 * 
 * --------------------------------------------
 * 🔄 Dry Run Example:
 * Input: [5, -3, 5]
 * 
 * Kadane’s Max Subarray Sum (non-wrap): 
 *     5 → 5
 *     5 + (-3) = 2 → keep 5
 *     2 + 5 = 7 → update to 7
 *     => maxKadane = 7
 * 
 * Total sum = 5 + (-3) + 5 = 7
 * 
 * Kadane’s Min Subarray Sum:
 *     -3 is the smallest subarray sum
 *     => minKadane = -3
 * 
 * Wrapping sum = total - min = 7 - (-3) = 10
 * 
 * Final Answer = max(7, 10) = 10
 */

class Solution {
    public int maxSubarraySumCircular(int[] arr) {
        int maxKadane = Integer.MIN_VALUE;
        int minKadane = Integer.MAX_VALUE;
        int currMax = 0, currMin = 0, total = 0;

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            total += num;

            // Kadane’s for max subarray
            currMax = Math.max(num, currMax + num);
            maxKadane = Math.max(maxKadane, currMax);

            // Kadane’s for min subarray
            currMin = Math.min(num, currMin + num);
            minKadane = Math.min(minKadane, currMin);
        }

        // If all elements are negative, return maxKadane only
        if (maxKadane < 0) return maxKadane;

        // Otherwise, return the maximum of wrap and non-wrap cases
        return Math.max(maxKadane, total - minKadane);
    }
}
