/*
  Given two strings s1 and s2. Return a minimum number of times s1 has to be repeated such 
  that s2 is a substring of it. If s2 can never be a substring then return -1.
*/
class Solution {
    static int minRepeats(String A, String B) {
        StringBuilder sb = new StringBuilder(A);
        int count = 1;

        // Repeat A until sb.length() >= B.length()
        while (sb.length() < B.length()) {
            sb.append(A);
            count++;
        }

        // First check after minimum required repeats
        if (kmpSearch(sb.toString(), B)) return count;

        // One more repeat to handle overlap cases
        sb.append(A);
        count++;

        if (kmpSearch(sb.toString(), B)) return count;

        return -1;
    }
    
    // KMP pattern search
    private static boolean kmpSearch(String text, String pattern) {
        int[] lps = computeLPS(pattern);
        int i = 0, j = 0;

        while (i < text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == pattern.length()) return true;
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return false;
    }

    // Preprocess pattern to build LPS (Longest Prefix Suffix) array
    private static int[] computeLPS(String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0, i = 1;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                lps[i++] = ++len;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i++] = 0;
                }
            }
        }
        return lps;
    }
}
