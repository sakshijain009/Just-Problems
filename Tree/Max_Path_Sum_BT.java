/*
    🧠 Problem: Maximum Path Sum in a Binary Tree

    📌 Statement:
    Given a binary tree, find the path with the **maximum sum**.
    A path is defined as any sequence of nodes from some starting node to any node
    in the tree along the parent-child connections (the path **does not need to go through the root**).
    The path must contain at least one node and does not need to go through a leaf.

    ✅ Approach:
    - We use a recursive post-order traversal (left-right-root).
    - At each node, calculate the **maximum path sum** passing through that node:
        currentMax = node.data + max(0, leftSubtree) + max(0, rightSubtree)
    - Use a global variable `oMax` to keep track of the overall max path sum.
    - When returning to the parent, we can only include **one child path** (left or right),
      because a valid path cannot fork at a parent (i.e., can't return both left and right).

    🔁 Time Complexity: O(N)
    📦 Space Complexity: O(H) — recursion stack, where H is the height of the tree
*/

class Main {

    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
            this.left = this.right = null;
        }
    }

    static class Solution {
        int oMax;

        int findMaxSum(Node node) {
            oMax = Integer.MIN_VALUE;
            helper(node);
            return oMax;
        }

        int helper(Node root) {
            if (root == null) return 0;

            int left = Math.max(0, helper(root.left));   // Ignore negative paths
            int right = Math.max(0, helper(root.right));

            int currentMaxPath = root.data + left + right;

            oMax = Math.max(oMax, currentMaxPath);       // Update global max

            return root.data + Math.max(left, right);    // Return max single path
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(2);
        root.right = new Node(10);
        root.left.left = new Node(20);
        root.left.right = new Node(1);
        root.right.right = new Node(-25);
        root.right.right.left = new Node(3);
        root.right.right.right = new Node(4);

        Solution sol = new Solution();
        int maxSum = sol.findMaxSum(root);

        System.out.println("Maximum Path Sum: " + maxSum);
    }
}
