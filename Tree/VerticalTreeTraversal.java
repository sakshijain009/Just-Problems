/*
💡 Problem: Vertical Order Traversal of a Binary Tree

Given a binary tree, return its vertical order traversal — nodes grouped by horizontal distance from the root, top to bottom, and left to right.

🧭 Horizontal Distance (hd):
- Root has hd = 0
- Left child → hd - 1
- Right child → hd + 1

📌 Example Tree:
        1
      /   \
     2     3
    / \   / \
   4   5 6   7

✅ Expected Vertical Order Output:
[
 [4],
 [2],
 [1, 5, 6],
 [3],
 [7]
]

---

🔁 Dry Run (BFS):
Step-by-step processing by level with horizontal distance (hd):

Queue:
[ (1, 0) ]  
→ map: { 0: [1] }

Add children: (2, -1), (3, 1)  
→ map: { -1: [2], 0: [1], 1: [3] }

Add children: (4, -2), (5, 0), (6, 0), (7, 2)  
→ map: { -2: [4], -1: [2], 0: [1, 5, 6], 1: [3], 2: [7] }

---

⚖️ Why BFS is Better Than DFS Here:

✅ BFS guarantees:
- Nodes are processed **top to bottom**
- Maintains **left-to-right** order across levels

❌ DFS doesn't guarantee top-down order, so values at the same horizontal distance might be added out of vertical order.

*/

import java.util.*;

class Solution {
    
    // ---------------- BFS-based Vertical Order Traversal ----------------
    public static ArrayList<ArrayList<Integer>> verticalOrderBFS(Node root) {
        Map<Integer, ArrayList<Integer>> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null) return result;

        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            Node node = current.node;
            int hd = current.hd;

            map.computeIfAbsent(hd, k -> new ArrayList<>()).add(node.data);

            if (node.left != null) queue.offer(new Pair(node.left, hd - 1));
            if (node.right != null) queue.offer(new Pair(node.right, hd + 1));
        }

        for (ArrayList<Integer> list : map.values()) {
            result.add(list);
        }

        return result;
    }

    // ---------------- DFS-based Vertical Grouping ----------------
    public static ArrayList<ArrayList<Integer>> verticalOrderDFS(Node root) {
        Map<Integer, ArrayList<Integer>> map = new TreeMap<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        dfsHelper(root, 0, map);

        for (ArrayList<Integer> list : map.values()) {
            result.add(list);
        }

        return result;
    }

    private static void dfsHelper(Node root, int hd, Map<Integer, ArrayList<Integer>> map) {
        if (root == null) return;

        map.computeIfAbsent(hd, k -> new ArrayList<>()).add(root.data);

        dfsHelper(root.left, hd - 1, map);
        dfsHelper(root.right, hd + 1, map);
    }

    // ---------------- Supporting Classes ----------------
    static class Pair {
        Node node;
        int hd;

        Pair(Node node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            data = val;
            left = right = null;
        }
    }

    // ---------------- Example Main ----------------
    public static void main(String[] args) {
        /*
                1
              /   \
             2     3
            / \   / \
           4   5 6   7
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        System.out.println("🔁 BFS Vertical Order:");
        System.out.println(verticalOrderBFS(root));

        System.out.println("🌿 DFS Vertical Grouping:");
        System.out.println(verticalOrderDFS(root));
    }
}
