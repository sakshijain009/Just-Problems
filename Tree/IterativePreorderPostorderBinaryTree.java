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

    // Iterative traversal to find Inorder, Preorder and Postorder traversal
    public static void iterativeTraversal(Node root) {
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, 1));

        StringBuilder pre = new StringBuilder();
        StringBuilder in = new StringBuilder();
        StringBuilder post = new StringBuilder();

        while (!stack.isEmpty()) {
            Pair top = stack.peek();

            if (top.state == 1) { // Adding first child to stack
                pre.append(top.node.data).append(" ");
                top.state++;
                if (top.node.left!=null) stack.push(new Pair(top.node.left, 1));
            } else if (top.state == 2) { // Adding second child to stack
                in.append(top.node.data).append(" ");
                top.state++;
                if (top.node.right!=null) stack.push(new Pair(top.node.right, 1));
            } else { // Removing the node since both children covered
                post.append(top.node.data).append(" ");
                stack.pop();
            }
        }

        System.out.println("Preorder : " + pre);
        System.out.println("Inorder : " + in);
        System.out.println("Postorder : " + post);
    }

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

        // Calling the iterative traversal function for the binary tree
        iterativeTraversal(root);
    }

}
