import java.util.*;

class Solution {

    /**
     * Q: Given a string consisting of only '(' and ')',
     *    find the length of the longest valid (well-formed) parentheses substring.
     *
     * Example:
     *   Input: "(())()"
     *   Output: 6
     *
     * Explanation:
     *   The whole string "(())()" is a valid substring of length 6.
     *
     * Approach (Stack of indices):
     *  - Use a stack to store indices of characters.
     *  - Push -1 initially as a sentinel (base index).
     *  - If current char is '(' → push its index.
     *  - If current char is ')' → pop:
     *       - If stack is empty after pop → push current index as new base.
     *       - Else → calculate valid length = i - stack.peek(), update max.
     *
     * Why -1?
     *   - It acts as the "last invalid boundary" before string start.
     *   - Without it, we cannot correctly calculate lengths for substrings starting at index 0.
     *
     * Dry Run for s = "(())()":
     *   stack = [-1], maxLen = 0
     *
     *   i=0 '(' → push 0        → stack = [-1, 0]
     *   i=1 '(' → push 1        → stack = [-1, 0, 1]
     *   i=2 ')' → pop(1)        → stack = [-1, 0], len = 2-0=2 → maxLen=2
     *   i=3 ')' → pop(0)        → stack = [-1], len = 3-(-1)=4 → maxLen=4
     *   i=4 '(' → push 4        → stack = [-1, 4]
     *   i=5 ')' → pop(4)        → stack = [-1], len = 5-(-1)=6 → maxLen=6
     *
     * Result: 6
     */
    static int maxLen(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // base index
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i); // new base index
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }

    // quick test
    public static void main(String[] args) {
        String s = "(())()";
        System.out.println("Longest valid substring length = " + maxLen(s)); // 6
    }
}
