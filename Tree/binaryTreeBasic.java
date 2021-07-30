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

    //Total nodes in a tree
    public static int size(Node node){
        if (node==null) return 0;

        int ls = size(node.left);
        int rs = size(node.right);
        int ts = ls + rs + 1; //Adding 1 extra for the node itself
        return ts;
    }

    //Sum of all nodes
    public static int sum(Node node){
        if (node==null) return 0;

        int lsum = size(node.left);
        int rsum = size(node.right);
        int total = lsum + rsum + node.data; //Adding data for the node itself
        return total;
    }

    //Max of all nodes
    public static int max(Node node){
        if (node==null) return Integer.MIN_VALUE;

        int lmax = max(node.left);
        int rmax = max(node.right);
        int maxg = Math.max(rmax,lmax);
        return Math.max(maxg,node.data);
    }

    //Height of the tree
    public static int height(Node node){
        if (node==null) return -1;

        int l = height(node.left);
        int r = height(node.right);
        int maxh = Math.max(l,r);
        return maxh+1;
    }

    //Display the nodes in a tree
    public static void display(Node node){
        if (node == null) return;

        String left = node.left==null?"null":Integer.toString(node.left.data);
        String right = node.right==null?"null":Integer.toString(node.right.data);
        System.out.println(left + " <- " + node.data + " -> " + right);

        display(node.left);
        display(node.right);
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

    //Main driver function
    public static void main(String[] args) throws IOException {
        Integer[] arr = {50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
        Node root = createTree(arr);
        display(root);
        System.out.println("Size: "+size(root));
        System.out.println("Sum: "+sum(root));
        System.out.println("Max: "+max(root));
        System.out.println("Height: "+height(root));
    }
}

