import java.util.*;

class Tree {

    //Declare node of a tree
    public static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class Pair {
        int state;
        Node node;

        Pair( Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    static class BSTPair {
        boolean isBST;
        int min;
        int max;
        Node largestBSTRoot;
        int size;
        Node root;
        int largestBSTSize;
    }

    public static boolean isItABinaryTree(Node root) {
        return isBSTHelper(root).isBST;
    }

    public static BSTPair isBSTHelper(Node root) {

        if (root == null) {
            BSTPair pair = new BSTPair();
            pair.max = Integer.MIN_VALUE;
            pair.min = Integer.MAX_VALUE;
            pair.isBST = true;
            return pair;
        }

        BSTPair lp = isBSTHelper(root.left);
        BSTPair rp = isBSTHelper(root.right);

        BSTPair pair = new BSTPair();
        pair.isBST = lp.isBST && rp.isBST && (root.data>=lp.max && root.data<= rp.min);
        pair.max = Math.max(root.data, Math.max(lp.max, rp.max));
        pair.min = Math.min(root.data, Math.min(lp.min, rp.min));

        return pair;
    }

    public static boolean isItABinaryTree(Node root) {
        return isBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    public static boolean isBST(Node node, long min, long max) {
        if (node == null) return true;
    
        if (node.data <= min || node.data >= max) return false;
    
        return isBST(node.left, min, node.data) && isBST(node.right, node.data, max);
    }

    public static void largestBinarySearchSubTree(Node root) {
        BSTPair bp = largestBinarySearchSubTreeHelper(root);
        System.out.println(bp.root.data + " with size of " + bp.size);
    }

    public static BSTPair largestBinarySearchSubTreeHelper(Node root) {

        if (root == null) {
            BSTPair pair = new BSTPair();
            pair.max = Integer.MIN_VALUE;
            pair.min = Integer.MAX_VALUE;
            pair.isBST = true;
            pair.size = 0;
            pair.root = null;
            return pair;
        }

        BSTPair lp = largestBinarySearchSubTreeHelper(root.left);
        BSTPair rp = largestBinarySearchSubTreeHelper(root.right);

        BSTPair pair = new BSTPair();
        pair.isBST = lp.isBST && rp.isBST && (root.data>=lp.max && root.data<= rp.min);
        pair.max = Math.max(root.data, Math.max(lp.max, rp.max));
        pair.min = Math.min(root.data, Math.min(lp.min, rp.min));

        if (pair.isBST) {
            pair.size = lp.size + rp.size + 1;
            pair.root = root;
        } else if (lp.size > rp.size){
            pair.size = lp.size;
            pair.root = lp.root;
        } else {
            pair.size = rp.size;
            pair.root = rp.root;
        }

        return pair;
    }

    public static void main(String[] args){
        Integer[] arr1 = {50,25,12,null,null,37,30,null,null,40,null,null,75,
                62,60,null,null,76,null,null,87,null,null};
        Integer[] arr2 = {50,25,12,null,null,37,30,null,null,40,null,null,75,
                62,60,null,null,64,null,null,87,null,null};

        Node root1 = createTree(arr1);
        display(root1);

        System.out.println();
        Node root2 = createTree(arr2);
        display(root2);

        System.out.println(isItABinaryTree(root1));
        System.out.println(isItABinaryTree(root2));

        largestBinarySearchSubTree(root1);

    }

    /*

    TREE 1:
               50
           /        \
         25         75
        /  \       /  \
       12  37     62  87
          /  \   /  \
         30  40 60  76


    TREE 2:
               50
           /        \
         25         75
        /  \       /  \
       12  37     62  87
          /  \   /  \
         30  40 60  64

  */

    // Display the Binary Tree
    public static void display(Node root) {
        if (root == null) return;

        String rootAndItsNeighbours = "";

        rootAndItsNeighbours = root.left == null
                ? rootAndItsNeighbours + "null"
                : rootAndItsNeighbours + root.left.data;
        rootAndItsNeighbours = rootAndItsNeighbours + " <-- " + root.data + " --> ";
        rootAndItsNeighbours = root.right == null
                ? rootAndItsNeighbours + "null"
                : rootAndItsNeighbours + root.right.data;

        System.out.println(rootAndItsNeighbours);
        display(root.left);
        display(root.right);
    }

    //Create the binary tree
    public static Node createTree(Integer[] arr){
        Node root = new Node(arr[0]);
        Stack<Pair> st = new Stack<>();
        Pair p = new Pair(root,1);
        st.push(p);

        int idx = 0;
        while (!st.isEmpty()){
            Pair top = st.peek();
            if (top.state == 1){
                idx++;
                if (arr[idx]!=null){
                    Node lp = new Node(arr[idx]);
                    top.node.left = lp;

                    Pair temp = new Pair(lp,1);
                    st.push(temp);
                }else{
                    top.node.left = null;
                }
                top.state++;
            }else if (top.state == 2){
                idx++;
                if (arr[idx]!=null){
                    Node rp = new Node(arr[idx]);
                    top.node.right = rp;

                    Pair temp = new Pair(rp,1);
                    st.push(temp);
                }else{
                    top.node.right = null;
                }
                top.state++;
            }else{
                st.pop();
            }
        }

        return root;
    }
}
