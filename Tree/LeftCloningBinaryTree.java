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

    public static void leftCloning_BinaryTree(Node root) {
        if (root == null) return;

        leftCloning_BinaryTree(root.left);
        leftCloning_BinaryTree(root.right);

        Node newLeftNode = new Node(root.data);
        newLeftNode.left = root.left;
        root.left = newLeftNode;
    }

      /*

    TREE AFTER LEFT CLONING:

                 10
               /  \
             10    40
            /      /
          20      40
         /  \
        20   30
            /
           30

  */

    public static Node transformBackFromLeftClonedTree(Node root) {
        if (root==null) return null;

        Node left = transformBackFromLeftClonedTree(root.left.left);
        Node right = transformBackFromLeftClonedTree(root.right);

        root.left = left;
        root.right = right;
        return root;
    }

    public static void transformBackFromLeftClonedTree1(Node root) {
        if (root==null) return;

        root.left = root.left.left;

        transformBackFromLeftClonedTree(root.left);
        transformBackFromLeftClonedTree(root.right);
    }

    public static void main(String[] args){
        Integer[] arr = {10,20,30,null,null,null,40,null,null,null,};

        Node root = createTree(arr);
        display(root);

        System.out.println();
        leftCloning_BinaryTree(root);
        display(root);

        System.out.println();
        transformBackFromLeftClonedTree(root);
        display(root);

        System.out.println();
        leftCloning_BinaryTree(root);
        transformBackFromLeftClonedTree1(root);
        display(root);

    }

    /*

    TREE:
          10
         /  \
        20   40
       /
      30

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
