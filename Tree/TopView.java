/*
💡 Problem: Top View of a Binary Tree using DFS

Given a binary tree, return the top view of it.
The **top view** of a binary tree is the set of nodes visible when the tree is **viewed from the top**.
From each horizontal distance (hd), only the **topmost node** should be visible.

📌 Example:
For the following tree:

         1
       /   \
      2     3
     / \   / \
    4   5 6   7

🔼 Top view: 4 2 1 3 7

✅ Core Concepts:
- Horizontal Distance (hd): root = 0; left = hd - 1; right = hd + 1
- Level (depth): used to determine if a node is higher (i.e., closer to root)

✅ How the Code Works:
- A recursive DFS is used to traverse the tree, tracking:
  - `hd` = horizontal distance from root
  - `level` = vertical level (depth)
- A `TreeMap<Integer, Pair>` is used to store the topmost node at each horizontal level:
    - key = hd
    - value = Pair(node value, level)

👨‍💻 At each node:
- If this is the **first time** we are seeing this `hd`, add it to the map.
- If this `hd` is already in the map, we only update it if the current node is **higher** (i.e., has smaller level).

✅ Why DFS Works Here:
- Normally, BFS is used for top view.
- But in this approach, DFS works by maintaining level and only updating a horizontal position if the current node is **closer to the top**.
- The final result is built by iterating over the `TreeMap`, which automatically sorts the keys from left to right.

✅ Output for example:
Input: 1 → 2 → 3 → 4 → 5 → 6 → 7
Output: 4 2 1 3 7

*/

class Solution {
    static ArrayList<Integer> topView(Node root) {

        ArrayList<Integer> ans = new ArrayList<>();
        
        if(root == null) return ans;
        
        Map<Integer, Pair> map = new TreeMap<>();
        helper(root, 0, 0, map);
        
        for(int key: map.keySet()) {
            ans.add(map.get(key).val);
        }
        
        return ans;
    }
    
    static void helper(Node root, int horizontal, 
        int level, Map<Integer, Pair> map) {
        if(root == null) return;
        
        if(map.containsKey(horizontal)) {
            Pair pair = map.get(horizontal);
            if(level < pair.lvl) {
                map.put(horizontal, new Pair(root.data, level));
            }
        } else {
            map.put(horizontal, new Pair(root.data, level));
        }
        
        helper(root.left, horizontal-1, level+1, map);
        helper(root.right, horizontal+1, level+1, map);
    }
    
    static class Pair {
        int val;
        int lvl;
        
        public Pair(int val, int lvl) {
            this.val = val;
            this.lvl = lvl;
        }
    }
}
