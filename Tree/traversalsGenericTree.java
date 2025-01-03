
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/*
    Generic Tree Operations
 */

class GenericTree {

    static class Node {
        int data;
        ArrayList<Node> children;

        Node(int data){
            this.data = data;
            children = new ArrayList<>();
        }
    }

    // Display a generic tree
    public static void display(Node node){
        StringBuilder str = new StringBuilder(node.data + " -> ");

        for (Node child: node.children){
            str.append(child.data).append(", ");
        }
        if (node.children.isEmpty()){
            str.append("Leaf Node");
        }
        System.out.println(str);

        for (Node child: node.children){
            display(child);
        }
    }

    // PreOrder Traversal
    public static void preorderTraversal(Node root) {
        System.out.print(root.data + " ");

        for (Node child : root.children) {
            preorderTraversal(child);
        }
    }

    // PostOrder Traversal
    public static void postTraversal(Node root) {
        for (Node child : root.children) {
            postTraversal(child);
        }

        System.out.print(root.data + " ");
    }

    // All Traversals
    public static void Traversals(Node root) {
        System.out.println("Node Pre " + root.data);

        for (Node child : root.children) {
            System.out.println("Edge Pre " + root.data + " --> " + child.data);
            Traversals(child);
            System.out.println("Edge Post " + root.data + " --> " + child.data);
        }

        System.out.println("Node Post " + root.data);
    }

    // Main function
    public static void main(String[] args) throws IOException {
        int[] array = {10,20,50,-1,60,-1,-1,30,70,-1,80,110,-1,120,-1,-1,90,-1,-1,40,100,-1,-1,-1};
        Node root = null;
        Stack<Node> stack = new Stack<>();

        // Create tree using stack
        for (int i = 0; i < array.length; i++) {
            if (array[i]==-1){
                stack.pop();
            } else {
                Node temp = new Node(array[i]);

                if (!stack.isEmpty()){
                    stack.peek().children.add(temp);
                }else{
                    root = temp;
                }
                stack.push(temp);
            }
        }

        // Display tree
        display(root);

        System.out.println("\n");
        
        // PreOrder Traversal
        preorderTraversal(root);

        System.out.println("\n");

        // PostOrder Traversal
        postTraversal(root);

        System.out.println("\n");

        // All Traversal
        Traversals(root);
    }
}
