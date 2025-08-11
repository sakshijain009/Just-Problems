/*
Q: Given a string s, remove all its adjacent duplicate characters recursively, until there are no adjacent duplicate characters left.
   If the resultant string becomes empty, return an empty string.

Example:
---------
Input:  "geeksforgeek"
Output: "gksforgk"
Explanation:
    g(ee)ksforg(ee)k -> gksforgk

Input:  "abccbccba"
Output: ""
Explanation:
    ab(cc)b(cc)ba -> abbba -> a(bbb)a -> aa -> "" (empty string)

Input:  "aeebcccd"
Output: "abd"

Dry Run for "aeebcccd":
-----------------------
Pass 1:
    aeebcccd
    -> remove "ee"  → "abcccd"
    -> remove "ccc" → "abd"

Pass 2:
    "abd" → No adjacent duplicates → Stop.

Final Output: "abd"
*/

class Solution {
    public String removeUtil(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int n = s.length();

        while (i < n) {
            int j = i;
            // find the block of same characters starting at i
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            // keep only if block length is 1 (no duplicates)
            if (j - i == 1) {
                sb.append(s.charAt(i));
            }
            // move i to the next block
            i = j;
        }
        // if string changed, repeat recursively
        if (sb.length() != s.length()) {
            return removeUtil(sb.toString());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.removeUtil("aeebcccd")); // Expected: abd
    }
}
