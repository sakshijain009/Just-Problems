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

    // Similar Shape Trees
    public static boolean SimilarShape(Node node1,Node node2){
        if (node1.children.size() != node2.children.size()){
            return false;
        }

        for (int i = 0; i < node1.children.size()-1; i++) {
            Node child1 = node1.children.get(i);
            Node child2 = node2.children.get(i);
            if (!SimilarShape(child1,child2)) return false;
        }

        return true;
    }

    // Mirror image
    public static boolean isMirror(Node node1,Node node2){
        if (node1.children.size() != node2.children.size()){
            return false;
        }

        for (int i = 0; i < node1.children.size()-1; i++) {
            int j = node1.children.size() - i - 1;
            Node child1 = node1.children.get(i);
            Node child2 = node2.children.get(j);
            if (!isMirror(child1,child2)) return false;
        }

        return true;
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


    // Main Function
    public static void main(String[] args) {
        int[] array = {10,20,50,-1,60,-1,-1,30,70,-1,80,110,-1,120,-1,-1,90,-1,-1,40,100,-1,-1,-1};
        Node root1 = createTree(array);
        Node root2 = createTree(array);

        // Are trees of Similar Shape
        System.out.println("Are trees similar: " + SimilarShape(root1,root2));

        // Are trees Mirror Images
        System.out.println("Are trees mirror images: " + isMirror(root1,root2));

        // Symmetric Tree
        System.out.println("Is tree symmmetric: " + isMirror(root1,root1));
    }
}
