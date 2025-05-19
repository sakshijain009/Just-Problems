import java.util.*;

/**
 * Q: Given an array of strings where each string represents a vote for a candidate (name),
 * find the candidate with the highest number of votes. If there is a tie (multiple candidates with the same
 * number of votes), return the candidate whose name is lexicographically smaller (i.e., comes first alphabetically).
 *
 * Return the result as a String array of size 2:
 *   - result[0] = name of the winner
 *   - result[1] = number of votes they received (as a string)
 *
 * Example:
 * Input: ["john", "johnny", "jackie", "johnny", "john", "jackie", "jamie", 
 *         "jamie", "john", "johnny", "jamie", "johnny", "john"]
 * Output: ["john", "4"]
 */

public class Main {

    public static String[] winner(String[] arr, int n) {
      
        HashMap<String, Integer> map = new HashMap<>();
        for (String name : arr) {
            map.put(name, map.getOrDefault(name, 0) + 1);
        }

        String winner = "";
        int max = 0;

        for (String name : map.keySet()) {
            int votes = map.get(name);
            if (votes > max) {
                max = votes;
                winner = name;
            } else if (votes == max && name.compareTo(winner) < 0) {
                // Tie-breaker: choose lexicographically smaller name
                winner = name;
            }
        }

        return new String[] { winner, Integer.toString(max) };
    }

    public static void main(String[] args) {
        String[] votes = {
            "john", "johnny", "jackie", "johnny", "john", "jackie",
            "jamie", "jamie", "john", "johnny", "jamie", "johnny", "john"
        };

        String[] result = winner(votes, votes.length);
        System.out.println("Winner: " + result[0]);
        System.out.println("Votes: " + result[1]);
    }
}
