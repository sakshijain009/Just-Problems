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

    static boolean isIdentical(Node r1, Node r2) {
        if(r1==null && r2==null) return true;

        if(r1==null || r2==null || (r1.data != r2.data)) return false;

        boolean b1 = isIdentical(r1.left, r2.left);
        boolean b2 = isIdentical(r1.right, r2.right);

        return b1&&b2;
    }

    static boolean isMirrorImage(Node r1, Node r2) {
        if(r1==null && r2==null) return true;

        if(r1==null || r2==null || (r1.data != r2.data)) return false;

        boolean b1 = isIdentical(r1.left, r2.right);
        boolean b2 = isIdentical(r1.right, r2.left);

        return b1&&b2;
    }


    public static void main(String[] args){
        Integer[] arr = {50,25,12,null,null,37,30,null,null,null,7990,62,null,70,null,null,87,null,null};

        Node root = createTree(arr);

        display(root);

        System.out.println();
        System.out.println("Is it identical to itself: " + isIdentical(root, root));
        System.out.println("Is its own mirror image: " + isMirrorImage(root, root));
    }

}
