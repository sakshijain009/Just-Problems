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

    // Size of tree
    public static int size(Node node){
        int count = 0;

        for (Node child: node.children){
            count += size(child);
        }
        count += 1; // Node itself

        return count;
    }

    // Level order traversal
    public static void levelOrder(Node node){
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);

        while (queue.size()>0){
            Node temp = queue.remove();
            System.out.println(temp.data);

            for (Node child: temp.children){
                queue.add(child);
            }
        }
    }

    // Max of a tree
    public static int max(Node node){
        int max = Integer.MIN_VALUE;

        for (Node child: node.children){
            max = Math.max(max(child),max);
        }
        max = Math.max(node.data,max);

        return max;
    }

    // Height of a tree
    public static int FindHeight(Node node){
        int height = 0;

        for (Node child: node.children){
            height = Math.max(height,FindHeight(child));
        }
        height += 1;

        return height;
    }

    // Remove leaves
    public static void removeLeaves(Node node){
        for (int i=node.children.size()-1;i>=0;i--){
            Node child = node.children.get(i);
            if (child.children.size()==0){
                node.children.remove(child);
            }
        }

        for (Node child: node.children){
            removeLeaves(child);
        }
    }

    // Linearize tree
    public static void linearize(Node node){
        for (Node child: node.children){
            linearize(child);
        }

        while (node.children.size() > 1){
            Node lastChild = node.children.remove(node.children.size() - 1);
            Node secondlastChild = node.children.get(node.children.size() - 1);
            Node secondLastChildTail = getTail(secondlastChild);
            secondLastChildTail.children.add(lastChild);
        }
    }

    public static Node getTail(Node node){
        while (node.children.size() == 1){
            node = node.children.get(0);
        }
        return node;
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

        // Display tree
        display(root);

        // Size of tree (Total Nodes)
        System.out.println("Size : " + size(root));

        // Max value
        System.out.println("Max : " + max(root));

        // Height of tree
        System.out.println("Height : " + FindHeight(root));

        // level order
        levelOrder(root);

        // Remove leaves
        removeLeaves(root);
        display(root);

        // Linearize
        System.out.println("Linearize");
        linearize(root);
        display(root);
    }
}
