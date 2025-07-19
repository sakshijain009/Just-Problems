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
        int v = 0, c = 0; // v: current streak of vowels, c: current streak of consonants

        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);

            if (isVowel(ch)) {
                v++;    // increase vowel count
                c = 0;  // reset consonant count
                if (v > 5) return 0; // Bad string
            } else if (ch == '?') {
                v++;    // consider '?' as vowel
                c++;    // also consider '?' as consonant
                if (v > 5 || c > 3) return 0; // Bad string
            } else {
                c++;    // increase consonant count
                v = 0;  // reset vowel count
                if (c > 3) return 0; // Bad string
            }
        }

        return 1; // Good string
    }

    // Utility function to check if a character is a vowel
    public static boolean isVowel(char ch) {
        return "aeiou".indexOf(ch) != -1;
    }
}
