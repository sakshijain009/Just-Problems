/**
 * 🧠 Problem: Good or Bad String
 * 
 * You are given a string `S` consisting of lowercase English letters and the character `?`.
 * 
 * A string is considered **"Bad"** if:
 *   - It contains **more than 5 consecutive vowels**, OR
 *   - It contains **more than 3 consecutive consonants**
 * 
 * The character `?` can represent either a vowel or a consonant. You must assume **all possible substitutions**.
 * 
 * Determine if there exists **at least one valid substitution** of `?` characters
 * such that the string is **"Good"** (i.e., does NOT violate the rules).
 * 
 * Return:
 *   - `1` if the string is "Good" under all possible interpretations.
 *   - `0` if the string is definitely "Bad" in at least one interpretation.
 * 
 * Examples:
 * Input: "aeiouu"         → Output: 0   (6 vowels → Bad)
 * Input: "a?iou?"         → Output: 1   (Can be Good depending on ?)
 * Input: "bcdf?"          → Output: 0   (If ? is consonant → 4 consonants → Bad)
 * Input: "a??e"           → Output: 1   (No interpretation violates the rule)
 */

class Solution {
    static int isGoodorBad(String S) {
        return dfs(S.toCharArray(), 0, 0, 0) ? 1 : 0;
    }

    static boolean dfs(char[] s, int idx, int v, int c) {
        if (v > 5 || c > 3) return false;
        if (idx == s.length) return true;

        char ch = s[idx];

        if (ch == '?') {
            // Try both: as vowel and as consonant
            return dfs(s, idx + 1, v + 1, 0) || dfs(s, idx + 1, 0, c + 1);
        } else if (isVowel(ch)) {
            return dfs(s, idx + 1, v + 1, 0);
        } else {
            return dfs(s, idx + 1, 0, c + 1);
        }
    }

    static boolean isVowel(char ch) {
        return "aeiou".indexOf(ch) != -1;
    }
}
