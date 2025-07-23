/**
 * QUESTION:
 * A thief wants to rob houses represented as nodes in a binary tree.
 * Each node contains some amount of money.
 * The thief cannot rob two directly-connected houses (i.e., a parent and its child).
 * 
 * Write a method to return the maximum amount of money the thief can rob without alerting the police.
 * 
 * SAMPLE RUN:
 * Input Tree:
 *         3
 *        / \
 *       2   3
 *        \    \
 *         3    1
 * 
 * Output:
 * Maximum profit the thief can rob: 7
 * 
 * Explanation:
 * Rob the root (3), left.right (3), and right.right (1) => 3 + 3 + 1 = 7
 */

public class MaxProfitThief {

    // Binary Tree Node class
    public static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    // Method to get max profit from robbing the tree
    public static int getMaxProfit(Node root) {
        int[] result = helper(root);
        return Math.max(result[0], result[1]);
    }

    // Helper method returns {include, exclude}
    private static int[] helper(Node node) {
        if (node == null) return new int[]{0, 0};

        int[] left = helper(node.left);
        int[] right = helper(node.right);

        int include = node.data + left[1] + right[1];
        int exclude = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[]{include, exclude};
    }

    // Main method with sample input
    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.right = new Node(3);
        root.right.right = new Node(1);

        int maxProfit = getMaxProfit(root);
        System.out.println("Maximum profit the thief can rob: " + maxProfit); // Output: 7
    }
}
