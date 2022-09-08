import java.util.ArrayList;
import java.util.Stack;

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
        String str = node.data + " -> ";

        for (Node child: node.children){
            str += child.data + ", ";
        }
        if (node.children.size() == 0){
            str += " Leaf Node";
        }
        System.out.println(str);

        for (Node child: node.children){
            display(child);
        }
    }

    // Linearize tree
    public static Node Linearize(Node node){
        if (node.children.size() == 0) return node;

        Node lastNodeTail = Linearize(node.children.get(node.children.size() - 1));
        while (node.children.size() > 1){
            Node last = node.children.remove(node.children.size() - 1);
            Node secondlast = node.children.get(node.children.size() - 1);
            Node secondLastTail = Linearize(secondlast);
            secondLastTail.children.add(last);
        }
        return lastNodeTail;
    }

    // Main Function
    public static void main(String[] args) {
        int[] array = {10,20,50,-1,60,-1,-1,30,70,-1,80,110,-1,120,-1,-1,90,-1,-1,40,100,-1,-1,-1};
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
        display(root);

        // Linear the tree
        Linearize(root);
        display(root);
    }
}
