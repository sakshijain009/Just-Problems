
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
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

    // LevelOrder Linewise
    public static void levelOrderLinewise(Node root) {
        Queue<Node> mainQueue = new ArrayDeque<>();
        Queue<Node> childQueue = new ArrayDeque<>();
        mainQueue.add(root);

        while (!mainQueue.isEmpty()) {
            Node temp = mainQueue.remove();
            System.out.print(temp.data + " ");

            for (Node child: temp.children) {
                childQueue.add(child);
            }

            if (mainQueue.isEmpty()) {
                mainQueue = childQueue;
                childQueue = new ArrayDeque<>();
                System.out.print("\n");
            }
        }
    }

    // LevelOrder Linewise Approach 2
    public static void levelOrderLinewiseApproach2(Node root) {
        Queue<Node> mainQueue = new ArrayDeque<>();
        mainQueue.add(root);
        mainQueue.add(new Node(-1));

        while (!mainQueue.isEmpty()) {
            Node temp = mainQueue.remove();

            if (temp.data == -1) {
                if (!mainQueue.isEmpty()) {
                    mainQueue.add(new Node(-1));
                    System.out.println();
                }
            } else {
                System.out.print(temp.data + " ");
                for (Node child: temp.children) {
                    mainQueue.add(child);
                }
            }
        }
    }

    // LevelOrder Zigzag
    public static void levelOrderZigzag(Node root) {                //          1
        Stack<Node> s1 = new Stack<>();                             //       2     3
        Stack<Node> s2 = new Stack<>();                             //     4  5   6  7
        s1.push(root);                                              // Printed as : 1
                                                                    //              3 2
        int lvl = 1;                                                //              4 5 6 7
        while (!s1.isEmpty()) {
            Node temp = s1.pop();
            System.out.print(temp.data + " ");

            if (lvl%2!=0) {
                for (Node child : temp.children) {
                    s2.push(child);
                }
            } else {
                for (int i = temp.children.size()-1; i >= 0 ; i--) {
                    s2.push(temp.children.get(i));
                }
            }

            if (s1.isEmpty()) {
                s1 = s2;
                s2 = new Stack<>();
                System.out.println();
                lvl++;
            }
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

        // levelOrder Traversal Linewise
        levelOrderLinewise(root);

        System.out.println("\n");

        // levelOrder Traversal Linewise Without Child queue
        levelOrderLinewiseApproach2(root);

        System.out.println("\n");

        // levelOrder Traversal Zigzag
        levelOrderZigzag(root);

        System.out.println("\n");

        // All Traversal
        Traversals(root);
    }
}
