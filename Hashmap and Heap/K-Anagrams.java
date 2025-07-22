public class KAnagramChecker {

    /**
     * Checks whether two strings are k-anagrams of each other.
     */
    public static boolean areKAnagrams(String s1, String s2, int k) {
        // Condition 1: Lengths must be equal
        if (s1.length() != s2.length()) return false;

        int[] count1 = new int[26];
        int[] count2 = new int[26];

        // Count character frequencies for both strings
        for (char c : s1.toCharArray()) {
            count1[c - 'a']++;
        }

        for (char c : s2.toCharArray()) {
            count2[c - 'a']++;
        }

        // Count how many characters in s1 need to be changed to match s2
        int changesRequired = 0;
        for (int i = 0; i < 26; i++) {
            if (count1[i] > count2[i]) {
                changesRequired += count1[i] - count2[i];
            }
        }

        // If changes required are within k, it's a k-anagram
        return changesRequired <= k;
    }

    public static void main(String[] args) {
        // Test Case 1:
        String s1 = "fodr";
        String s2 = "gork";
        int k = 2;

        // Dry Run:
        // s1: f o d r → count1: [0, ..., d=1, f=1, o=1, r=1]
        // s2: g o r k → count2: [0, ..., g=1, k=1, o=1, r=1]
        //
        // Comparing counts:
        // - f (1 in s1, 0 in s2) → +1 change
        // - d (1 in s1, 0 in s2) → +1 change
        // Total changes = 2
        // ✅ 2 <= k → true

        System.out.println(areKAnagrams(s1, s2, k)); // Output: true

        // Test Case 2:
        s1 = "geeks";
        s2 = "eggkf";
        k = 1;
        // Explanation:
        // s1: g=1, e=2, k=1, s=1
        // s2: e=1, g=2, k=1, f=1
        // Excess in s1: e(1), s(1) → 2 changes needed
        // ❌ 2 > 1 → false
        System.out.println(areKAnagrams(s1, s2, k)); // Output: false

        // Test Case 3:
        s1 = "adb";
        s2 = "fdab";
        k = 2;
        // ❌ Different lengths → false
        System.out.println(areKAnagrams(s1, s2, k)); // Output: false
    }
}
