import java.io.IOException;
import java.util.*;

class Testclass
{
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

    //Pair class used in insertion in a tree
    public static class pair{
        Node node;
        int state;

        pair(Node node,int state){
            this.node = node;
            this.state = state;
        }
    }

    //Display the nodes in a tree in inorder form
    public static void inorder(Node node){
        if (node == null) return;

        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    //Display the nodes in a tree in preorder form
    public static void preorder(Node node){
        if (node == null) return;

        System.out.print(node.data + " ");
        preorder(node.left);
        preorder(node.right);
    }

    //Display the nodes in a tree in postorder form
    public static void postorder(Node node){
        if (node == null) return;

        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data + " ");
    }

    //Create the tree
    public static Node createTree(Integer[] arr){
        Node root = new Node(arr[0],null,null);
        Stack<pair> st = new Stack<>();
        pair p = new pair(root,1);
        st.push(p);

        int idx = 0;
        while (st.size()>0){
            pair top = st.peek();
            if (top.state == 1){
                idx++;
                if (arr[idx]!=null){
                    Node lp = new Node(arr[idx],null,null);
                    top.node.left = lp;

                    pair temp = new pair(lp,1);
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

                    pair temp = new pair(rp,1);
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

    //Level Order traversal
    public static void levelOrder(Node node){
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);

        while (q.size() > 0){
            int count = q.size();
            for (int i = 0; i < count; i++) {
                Node temp = q.remove();
                System.out.print(temp.data+" ");

                if (temp.left != null) q.add(temp.left);
                if (temp.right != null) q.add(temp.right);
            }

            System.out.println();
        }
    }

    //Main driver function
    public static void main(String[] args) throws IOException {
        Integer[] arr = {50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
        Node root = createTree(arr);

        System.out.println("\nLEVELORDER:");
        levelOrder(root);

        System.out.println("\nPREORDER:");
        preorder(root);

        System.out.println("\nINORDER:");
        inorder(root);

        System.out.println("\nPOSTORDER:");
        postorder(root);
    }
}

