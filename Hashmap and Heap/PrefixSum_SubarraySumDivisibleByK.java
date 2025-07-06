/*
  Given an integer array arr and an integer k, 
  find the number of contiguous subarrays such that the sum of the subarray is divisible by k.

  rem = prefixSum % k → the remainder when the running total is divided by k.
  The extra +k % k handles negative numbers, so the remainder is always in [0, k-1].

*/

class Solution {
    public int subCount(int[] arr, int k) {
        HashMap<Integer, Integer> remainderCount = new HashMap<>();
        remainderCount.put(0, 1); 
      
        int count = 0;
        int currentSum = 0;

        for (int num : arr) {
            currentSum += num;
            int remainder = ((currentSum % k) + k) % k; 
            count += remainderCount.getOrDefault(remainder, 0);
            remainderCount.put(remainder, remainderCount.getOrDefault(remainder, 0) + 1);
        }

        return count;
    }
}
