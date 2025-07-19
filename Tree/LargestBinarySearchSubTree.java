// ✅ Problem:
// Given a binary tree, find the size (number of nodes) of the **largest subtree** which is a Binary Search Tree (BST).
// A subtree is a BST if:
// - left and right subtrees are BSTs
// - root's value > max of left subtree AND root's value < min of right subtree

// ✅ Approach:
// 1. Traverse the tree in postorder.
// 2. At each node, get info from left and right subtrees: min, max, size, and whether they are BSTs.
// 3. If both subtrees are BSTs and current node satisfies BST conditions, it's a BST.
// 4. Track the size of the largest BST subtree found so far.

class Solution {

    static int largestBst(Node root) {
        return helper(root).count;
    }

    // Helper method to return info about the subtree rooted at `root`
    public static BSTPair helper(Node root) {
        if (root == null) {
            BSTPair pair = new BSTPair();
            pair.min = Integer.MAX_VALUE;
            pair.max = Integer.MIN_VALUE;
            pair.count = 0;
            pair.isBST = true;  // null subtree is a valid BST
            return pair;
        }

        BSTPair left = helper(root.left);
        BSTPair right = helper(root.right);

        BSTPair curr = new BSTPair();

        // Check if current subtree is BST
        if (left.isBST && right.isBST && root.data > left.max && root.data < right.min) {
            curr.isBST = true;
            curr.count = left.count + right.count + 1;
            curr.min = Math.min(root.data, left.min);  // update min
            curr.max = Math.max(root.data, right.max); // update max
        } else {
            curr.isBST = false;
            curr.count = Math.max(left.count, right.count); // pick max of left/right
        }

        return curr;
    }

    // Helper class to hold info about subtree
    static class BSTPair {
        int max;      // max value in the subtree
        int min;      // min value in the subtree
        int count;    // size of the largest BST in this subtree
        boolean isBST; // whether this subtree is a BST
    }
}
