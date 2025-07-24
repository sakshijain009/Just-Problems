/*
Question:
Given two arrays representing the inorder and preorder traversals of a binary tree, 
construct the tree and return the root node of the constructed tree.

Then print the tree in postorder traversal.

Examples:

Input: inorder[] = [1, 6, 8, 7], preorder[] = [1, 6, 7, 8]
Output: 8 7 6 1

Input: inorder[] = [3, 1, 4, 0, 2, 5], preorder[] = [0, 1, 3, 4, 2, 5]
Output: 3 4 1 5 2 0

Input: inorder[] = [2, 5, 4, 1, 3], preorder[] = [1, 4, 5, 2, 3]
Output: 2 5 4 3 1
*/

import java.util.*;

class TreeBuilder {

    static int preIndex = 0;

    // Tree node class
    static class Node {
        int data;
        Node left, right;

        Node(int value) {
            this.data = value;
            this.left = this.right = null;
        }
    }

    // Main function to build the tree
    public static Node buildTree(int[] inorder, int[] preorder) {
        preIndex = 0;
        HashMap<Integer, Integer> inorderMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return build(inorderMap, preorder, 0, inorder.length - 1);
    }

    // Helper function to recursively build the tree
    private static Node build(HashMap<Integer, Integer> inorderMap, int[] preorder, int start, int end) {
        if (start > end) return null;

        int currVal = preorder[preIndex++];
        Node root = new Node(currVal);

        int inIndex = inorderMap.get(currVal);

        root.left = build(inorderMap, preorder, start, inIndex - 1);
        root.right = build(inorderMap, preorder, inIndex + 1, end);

        return root;
    }

    // Postorder traversal
    public static void postorder(Node root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    // Example usage
    public static void main(String[] args) {
        int[] inorder = {3, 1, 4, 0, 2, 5};
        int[] preorder = {0, 1, 3, 4, 2, 5};

        Node root = buildTree(inorder, preorder);
        postorder(root); // Output: 3 4 1 5 2 0
    }
}
