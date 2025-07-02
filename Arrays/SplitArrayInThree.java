/*
 * Problem Statement:
 * Given an integer array, find two indices that split the array into three contiguous parts
 * such that the sum of each part is equal. If such a split is not possible, return [-1, -1].
 *
 * Approach:
 * 1. Calculate the total sum of the array. If it's not divisible by 3, return [-1, -1].
 * 2. Traverse the array while maintaining a prefix sum.
 * 3. Whenever the prefix sum equals one-third of the total sum, record the index.
 * 4. As soon as two such split points are found, return them.
 *    These points effectively divide the array into three parts with equal sums.
 */

class Solution {

    public List<Integer> findSplit(int[] arr) {
        List<Integer> ans = new ArrayList<>();
        
        int sum = 0;
        for(int num : arr) {
            sum += num;
        }
        
        if(sum % 3 != 0) return Arrays.asList(-1, -1);
        
        int prefixSum = 0;
        for(int i=0; i<arr.length; i++) {
            prefixSum += arr[i];
            
            if(prefixSum == sum / 3) {
                ans.add(i);
                prefixSum = 0;
                
                if(ans.size() == 2) {
                    return ans;
                } 
            }
        }
        
        return Arrays.asList(-1, -1);
    }
}
