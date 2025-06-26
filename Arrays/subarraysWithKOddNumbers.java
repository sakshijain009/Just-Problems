/*
 Given an integer array nums and an integer k, return the number of contiguous subarrays that have exactly k odd numbers.
*/
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int oddCount = k;
        int count = 0;
        int tempCount = 0;

        while (right < nums.length) {
            // If current number is odd, decrease allowed odd count and reset temp count
            if (nums[right] % 2 != 0) {
                oddCount--;
                tempCount = 0;
            }

            // When we have exactly k odd numbers, move left pointer to count valid subarrays
            while (oddCount == 0) {
                tempCount++;
                if (nums[left] % 2 != 0) {
                    oddCount++;
                }
                left++;
            }

            // Add the number of valid subarrays ending at 'right'
            count += tempCount;
            right++;
        }

        return count;
    }

    public static int countSubarray2(int n, int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0, oddTillNow = 0;
        map.put(0, 1);
        
        for(int num : nums) {
            if(num%2!=0) {
                oddTillNow++;
            }
            
            if(map.containsKey(oddTillNow - k)) {
                count += map.get(oddTillNow - k);
            }
            
            map.put(oddTillNow, map.getOrDefault(oddTillNow, 0) + 1);
        }
        
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 1, 1};
        int k = 3;
        System.out.println(numberOfSubarrays(nums, k));  // Output: 2
    }
}
