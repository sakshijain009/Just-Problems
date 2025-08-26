/*
Q: Given two people's availability schedules (as lists of time intervals [start, end]),
find the earliest common time slot of length `d` where both are available.
If no such slot exists, return an empty array.

This is the "Meeting Scheduler" problem (LeetCode 1229).

Approach:
1. Sort both interval lists by start time.
2. Use two pointers (i, j) to traverse both lists.
3. For each pair of intervals a[i], b[j]:
   - Find the overlap: start = max(a[i][0], b[j][0]), end = min(a[i][1], b[j][1]).
   - If (end - start >= d), return {start, start + d}.
   - Otherwise, move the pointer that ends earlier (because that interval can’t help further).
4. If no overlap of length d is found, return empty array.

Data structure/Algo: Two Pointers + Interval Intersection
Complexity: O((n + m) log(n + m)) due to sorting, O(n + m) for scanning.
*/

import java.util.*;

class Solution {
    public int[] commonSlot(List<int[]> a, List<int[]> b, int d) {
        a.sort(Comparator.comparingInt(arr -> arr[0]));
        b.sort(Comparator.comparingInt(arr -> arr[0]));

        int i = 0, j = 0;

        while (i < a.size() && j < b.size()) {
            int start = Math.max(a.get(i)[0], b.get(j)[0]);
            int end = Math.min(a.get(i)[1], b.get(j)[1]);

            if (end - start >= d) {
                return new int[]{start, start + d};
            }

            // Move the pointer that ends earlier
            if (a.get(i)[1] < b.get(j)[1]) {
                i++;
            } else {
                j++;
            }
        }

        return new int[0];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        List<int[]> a = new ArrayList<>();
        a.add(new int[]{10, 50});
        a.add(new int[]{60, 120});
        a.add(new int[]{140, 210});

        List<int[]> b = new ArrayList<>();
        b.add(new int[]{0, 15});
        b.add(new int[]{60, 70});

        int d = 8;

        int[] ans = sol.commonSlot(a, b, d);
        System.out.println(Arrays.toString(ans)); // Expected [60, 68]
    }
}

/*
🔹 Dry Run for Example:
a = [[10,50], [60,120], [140,210]]
b = [[0,15], [60,70]]
d = 8

Step 1: Compare [10,50] vs [0,15]
   overlap = [max(10,0), min(50,15)] = [10,15]
   length = 5 < 8 → not enough
   advance b (ends earlier at 15)

Step 2: Compare [10,50] vs [60,70]
   overlap = [max(10,60), min(50,70)] = [60,50] → invalid overlap
   advance a (ends earlier at 50)

Step 3: Compare [60,120] vs [60,70]
   overlap = [max(60,60), min(120,70)] = [60,70]
   length = 10 ≥ 8 → ✅ return [60, 68]

Output: [60, 68]
*/
