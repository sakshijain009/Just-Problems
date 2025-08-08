/**
 * Question:
 * =========
 * You are given a function f() that returns true with probability 50% 
 * and false with probability 50%. 
 *
 * Task: 
 * Create a function g() that returns true with probability 75% 
 * and false with probability 25%.
 *
 * ---------------------------------------------------------------------
 * Explanation:
 * ============
 * Let's analyze:
 *  - f() → P(true) = 0.5, P(false) = 0.5
 *  - g() → P(true) = 0.75, P(false) = 0.25
 *
 * Idea:
 * -----
 * If we call f() twice:
 *  Case 1: true, true   → probability = 0.5 × 0.5 = 0.25
 *  Case 2: true, false  → probability = 0.5 × 0.5 = 0.25
 *  Case 3: false, true  → probability = 0.5 × 0.5 = 0.25
 *  Case 4: false, false → probability = 0.5 × 0.5 = 0.25
 *
 * If we make g() return TRUE in the first 3 cases and FALSE only in 
 * the last case, then:
 *    P(true)  = 0.25 + 0.25 + 0.25 = 0.75
 *    P(false) = 0.25
 *
 * Implementation:
 * ---------------
 * The condition for FALSE is when both calls to f() are false. 
 * So g() can simply be implemented as:
 *
 *    return f() || f();
 *
 * Why this works:
 * ===============
 * - f() || f() returns false only when BOTH calls are false.
 * - Probability of both false = 0.5 × 0.5 = 0.25 → 25% chance of false.
 * - Therefore, 75% chance of true.
 */
public class ProbabilityExample {

    // Given: Function with 50% true / 50% false
    boolean f() {
        return Math.random() < 0.5; // Simulates a fair coin toss
    }

    // Required: Function with 75% true / 25% false
    boolean g() {
        return f() || f();
    }

    // Test the g() method to validate probabilities
    public static void main(String[] args) {
        ProbabilityExample obj = new ProbabilityExample();
        int trials = 1_000_000;
        int trueCount = 0;

        for (int i = 0; i < trials; i++) {
            if (obj.g()) {
                trueCount++;
            }
        }

        double trueProbability = (trueCount * 100.0) / trials;
        System.out.println("Estimated probability of true: " + trueProbability + "%");
        System.out.println("Estimated probability of false: " + (100 - trueProbability) + "%");
    }
}
