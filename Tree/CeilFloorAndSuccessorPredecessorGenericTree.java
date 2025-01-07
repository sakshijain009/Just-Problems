import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

class Tree {

    static class Node {
        int data;
        ArrayList<Node> children;

        Node(int data){
            this.data = data;
            children = new ArrayList<>();
        }
    }

    public static void main(String[] args){
        int[] array = {10,20,50,-1,60,-1,-1,30,70,-1,80,110,-1,120,-1,-1,90,-1,-1,40,100,-1,-1,-1};
        Node root = createTree(array);

        levelOrderLinewise(root);
        System.out.println("\n");

        getPredecessorAndSuccessor(root, 100);

        if (predecessor == null) predecessor = new Node(-1);
        if (successor == null) successor = new Node(-1);
        System.out.println("Predecessor : " + predecessor.data + " Successor : " + successor.data);

        getCeilAndFloor(root, 11);
        System.out.println("Ceil : " + ceil + " Floor : " + floor);
    }

    static Node predecessor = null;
    static Node successor = null;

    static int state = 0;

    static int ceil = Integer.MAX_VALUE;
    static int floor = Integer.MIN_VALUE;

    public static void getCeilAndFloor(Node root, int val) {
        if (root.data > val && root.data < ceil) ceil = root.data;
        if (root.data <= val && root.data > floor) floor = root.data;

        for (Node child: root.children){
            getCeilAndFloor(child, val);
        }
    }

    public static void getPredecessorAndSuccessor(Node root, int val) {
        if (state == 1) {
            successor = root;
            state = 2;
            return;
        }
        if (root.data == val) {
            state = 1;
        }
        if (state == 0) {
            predecessor = root;
        }
        for (Node child: root.children) {
            getPredecessorAndSuccessor(child, val);
        }
    }

    // Create Tree
    public static Node createTree(int[] array){
        Node root = null;
        Stack<Node> stack = new Stack<>();

        // Create tree using stack
        for (int i = 0; i < array.length; i++) {
            if (array[i]==-1){
                stack.pop();
            }else{
                Node temp = new Node(array[i]);
                temp.data = array[i];

                if (stack.size() > 0){
                    stack.peek().children.add(temp);
                }else{
                    root = temp;
                }
                stack.push(temp);
            }
        }
        return root;
    }

    // LevelOrder Linewise to display the tree
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

}
