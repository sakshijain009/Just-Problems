import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        int[] arr = {1, 0, 0, 1, 0};
        int n = arr.length;

        int result = countSubarrWithEqualZeroAndOne(arr, n);
        System.out.println("Count of subarrays with equal number of 0s and 1s: " + result);
    }

    static int countSubarrWithEqualZeroAndOne(int arr[], int n) {

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int ans = 0;
        int sum = 0; // Prefix sum

        // Step 1: Replace all 0s with -1 to simplify the problem
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                arr[i] = -1;
            }
        }

        // Step 2: Traverse the modified array and count prefix sums
        for (int i = 0; i < n; i++) {
            sum += arr[i];

            // If this prefix sum was seen before, then subarrays ending at i with the same sum exist
            if (map.containsKey(sum)) {
                ans += map.get(sum); // Add the frequency of this prefix sum to the answer
            }

            // Update the frequency of this prefix sum
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return ans;

        /*
         * ============================
         * ✅ DRY RUN:
         * Original Input: arr = {1, 0, 0, 1, 0}
         * After replacing 0s with -1: arr = {1, -1, -1, 1, -1}
         *
         * Iterate and track prefix sum and map:
         *
         * i | arr[i] | sum  | map                          | ans
         * --------------------------------------------------------
         * 0 |   1    |  1   | {0=1, 1=1}                   | 0
         * 1 |  -1    |  0   | {0=2, 1=1}                   | 1
         * 2 |  -1    | -1   | {0=2, 1=1, -1=1}             | 1
         * 3 |   1    |  0   | {0=3, 1=1, -1=1}             | 3
         * 4 |  -1    | -1   | {0=3, 1=1, -1=2}             | 4
         *
         * Final Output: 4 subarrays with equal number of 0s and 1s
         * Subarrays are:
         *   (0,1), (1,2,3), (0,1,2,3), (3,4)
         * ============================
         */

    }
}
