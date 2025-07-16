/*
💡 Problem: Maximum Width of a Binary Tree

📌 Statement:
Given a binary tree, find its **maximum width**.  
The **width of a level** is the number of nodes present at that level.

✅ Your task is to return the maximum width among all the levels of the tree.

---

🧠 Concept:
We perform a **level-order traversal (BFS)** using a queue:
- For each level, count the number of nodes in the queue (`queue.size()`).
- Update the maximum width seen so far.

---

🕒 Time Complexity: O(n) → each node is visited once  
🧠 Space Complexity: O(w) → max number of nodes at any level (width)

---

📦 Example:

        1
      /   \
     2     3
    / \     \
   4   5     8
            /
           6

Level-wise:
- Level 0 → [1]            → Width = 1
- Level 1 → [2, 3]         → Width = 2
- Level 2 → [4, 5, 8]      → Width = 3 ✅
- Level 3 → [6]            → Width = 1

🔚 Maximum Width = 3

*/

import java.util.*;

class Solution {
    
    // Node class for the binary tree
    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            data = val;
            left = right = null;
        }
    }

    // Function to get the maximum width of a binary tree.
    public int getMaxWidth(Node root) {
        if (root == null) return 0;

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);

        int max = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();         // nodes at current level
            max = Math.max(max, size);       // update max width

            for (int i = 0; i < size; i++) {
                Node temp = queue.poll();

                if (temp.left != null) queue.add(temp.left);
                if (temp.right != null) queue.add(temp.right);
            }
        }

        return max;
    }

    // Sample test
    public static void main(String[] args) {
        /*
                1
              /   \
             2     3
            / \     \
           4   5     8
                  /
                 6
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(8);
        root.right.right.left = new Node(6);

        Solution sol = new Solution();
        System.out.println("🔍 Maximum Width: " + sol.getMaxWidth(root));  // Output: 3
    }
}
