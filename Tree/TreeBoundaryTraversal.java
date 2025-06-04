/*
class Node
{
    int data;
    Node left, right;

    public Node(int d)
    {
        data = d;
        left = right = null;
    }
}
*/

class Solution {
    ArrayList<Integer> boundaryTraversal(Node node) {
        ArrayList<Integer> ans = new ArrayList<>();
        
        if(node==null) return ans;
        
        if(!(node.left==null && node.right==null)) ans.add(node.data);
         
        // Left Boundary
        addLeftBoundary(node.left, ans);
        
        // Leaf Nodes
        addLeafNodes(node, ans);
        
        // Right Boundary
        addRightBoundary(node.right, ans);
        
        return ans;
    }
    
    void addLeftBoundary(Node root, ArrayList<Integer> ans) {
        if(root==null) return;
        
        // Not a leaf
        if(!(root.left==null && root.right==null)) {
            ans.add(root.data);
        }
        
        if(root.left != null) {
            addLeftBoundary(root.left, ans);
        } else {
            addLeftBoundary(root.right, ans);
        }
    }
    
    void addLeafNodes(Node root, ArrayList<Integer> ans) {
        if(root==null) return;
        
        if(root.left==null && root.right==null) {
            ans.add(root.data);
            return;
        }
        
        addLeafNodes(root.left, ans);
        addLeafNodes(root.right, ans);
    }
    
    void addRightBoundary(Node root, ArrayList<Integer> ans) {
        if(root==null) return;
        
        if(root.right != null) {
            addRightBoundary(root.right, ans);
        } else {
            addRightBoundary(root.left, ans);
        }
        
        // Not a leaf
        if(!(root.left==null && root.right==null)) {
            ans.add(root.data);
        }
        
    }
}
