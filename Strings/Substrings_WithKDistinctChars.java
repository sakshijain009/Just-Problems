import java.util.*;

class Solution {
    public int countSubstr(String s, int k) {
        return atMostK(s, k) - atMostK(s, k - 1);
    }

    private int atMostK(String s, int k) {
        if (k == 0) return 0;

        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int count = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);

            while (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }

            count += (right - left + 1); // number of substrings ending at 'right'
        }
        return count;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "abcba";
        int k = 2;
        System.out.println(sol.countSubstr(s, k)); // Expected output: substrings with exactly 2 distinct chars
    }
}
