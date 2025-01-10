import java.util.*;

class Tree {

    //Declare node of a tree
    public static class Node{
        int data;
        Node left;
        Node right;

        Node(int data,Node left,Node right){
            this.data = data;
            this.left = left;
            this.right = right;
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

    //Create the binary tree
    public static Node createTree(Integer[] arr){
        Node root = new Node(arr[0],null,null);
        Stack<Pair> st = new Stack<>();
        Pair p = new Pair(root,1);
        st.push(p);

        int idx = 0;
        while (!st.isEmpty()){
            Pair top = st.peek();
            if (top.state == 1){
                idx++;
                if (arr[idx]!=null){
                    Node lp = new Node(arr[idx],null,null);
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
                    Node rp = new Node(arr[idx],null,null);
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

    // Helper for K distance down
    static ArrayList<Integer> KdistanceDown(Node root, int k) {
        ArrayList<Integer> ans = new ArrayList<>();
        printKLevelDown(root, k, ans);
        return ans;
    }

    // K level down children for a node
    public static void printKLevelDown(Node root, int k, ArrayList<Integer> ans) {
        if (root == null) return;

        if(k==0) {
            ans.add(root.data);
            return;
        }

        printKLevelDown(root.left, k-1, ans);
        printKLevelDown(root.right, k-1, ans);
    }

    // Helper for Nodes at K edge distance
    static ArrayList<Integer> KdistanceDownWithBlocker(Node root, int k, Node blocker) {
        ArrayList<Integer> ans = new ArrayList<>();
        printKLevelDownWithBlocker(root, k, ans, blocker);
        return ans;
    }

    public static void printKLevelDownWithBlocker(Node root, int k, ArrayList<Integer> ans, Node blocker) {
        if (root == null || root == blocker) return;

        if(k==0) {
            ans.add(root.data);
            return;
        }

        printKLevelDownWithBlocker(root.left, k-1, ans, blocker);
        printKLevelDownWithBlocker(root.right, k-1, ans, blocker);
    }

    // Neighbours at a distance of K edges
    public static ArrayList<Integer> printKDistantEdgeNeighbours(Node root, int k, int val) {
        ArrayList<Node> path = nodeToRootPath(root, val);

        int i = 0;
        ArrayList<Integer> finalAns = new ArrayList<>();

        while (i<path.size()) {
            ArrayList<Integer> ans = KdistanceDownWithBlocker(path.get(i), k, (i==0 ? null : path.get(i-1)));
            finalAns.addAll(ans);
            k--;
            i++;
        }

        return finalAns;
    }

    // Path from a given node to the root node
    public static ArrayList<Node> nodeToRootPath(Node root, int val) {
        if (root == null) {
            return new ArrayList<>();
        }

        if (root.data == val) {
            ArrayList<Node> path = new ArrayList<>();
            path.add(root);
            return path;
        }

        ArrayList<Node> path1 = nodeToRootPath(root.left, val);
        if (!path1.isEmpty()) {
            path1.add(root);
            return path1;
        }

        ArrayList<Node> path2 = nodeToRootPath(root.right, val);
        if (!path2.isEmpty()) {
            path2.add(root);
            return path2;
        }

        return new ArrayList<>();
    }


    public static void printNodeToRootPath(Node root, int val) {
        ArrayList<Node> path = nodeToRootPath(root, val);

        System.out.print("Node to root path : ");
        for(Node node : path) {
            System.out.print(node.data + " ");
        }
        System.out.println();
    }

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

    public static void main(String[] args){
        Integer[] arr = {50,25,12,null,null,37,30,null,null,null,7,62,null,70,null,null,87,null,null};

        Node root = createTree(arr);
        display(root);

        System.out.println("Elements at K distance down : " + KdistanceDown(root, 2));
        printNodeToRootPath(root, 70);
        System.out.println("Elements at K distance (including both parent and child) : " + printKDistantEdgeNeighbours(root, 2, 37));
    }

}
