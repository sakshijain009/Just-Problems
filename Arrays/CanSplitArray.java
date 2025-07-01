class Solution {
    // Check if the array can be split into two non-empty parts with equal sum.
    // Dry Run Example: Input = [1, 2, 3, 8, 2, 1, 3]
    // Total sum = 20 → target = 10
    // Prefix sums: 1, 3, 6, 14, 16, 17 → none equal 10 before last index
    // Hence, return false
  
    public boolean canSplit(int[] arr) {
        int total = 0;
        for (int num : arr) {
            total += num;
        }

        if (total % 2 != 0) return false; // can't split odd sum equally

        int prefixSum = 0;
        for (int i = 0; i < arr.length - 1; i++) { // exclude last index
            prefixSum += arr[i];
            if (prefixSum == total / 2) return true;
        }

        return false;
    }

    public boolean canSplit_M2(int arr[]) {
        if (arr == null || arr.length < 2) return false;

        int left = arr[0];
        int right = arr[arr.length - 1];
        int i = 0, j = arr.length - 1;

        while (i < j - 1) { // Ensure there's at least one element between i and j
            if (left < right) {
                left += arr[++i];
            } else if (left > right) {
                right += arr[--j];
            } else {
                // Equal sums but not adjacent
                left += arr[++i];
                right += arr[--j];
            }
        }

        return left == right && j == i + 1;
    }

    /*
        Find the first index in the array such that the sum of elements before it is equal 
        to the sum of elements after it. Return -1 if no such point exists.
    */
    int equalSum(int[] arr) {
        int sum = 0;
        for(int num : arr) {
            sum += num;
        }
        
        int prefixSum = 0;
        for(int i = 0; i<arr.length; i++) {
            prefixSum += arr[i];
            if(prefixSum - arr[i] == sum - prefixSum) {
                return i;
            }
        }
        
        return -1;
    }
}
