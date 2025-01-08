import java.util.*;

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

        levelOrderDisplay(root);
        System.out.println("\n");

        calculateDiameterOfTree(root);
        System.out.println("Diameter : " + diameter);
    }

    static int diameter = Integer.MIN_VALUE;

    // Diameter of a Generic Tree
    public static int calculateDiameterOfTree(Node root) {
        int deepestChild = -1;
        int secondDeepestChild = -1;

        for (Node child : root.children) {
            int height = calculateDiameterOfTree(child); // Fixed recursive call

            if (height > deepestChild) {
                secondDeepestChild = deepestChild;
                deepestChild = height;
            } else if (height > secondDeepestChild) {
                secondDeepestChild = height;
            }
        }

        // Update the global diameter
        diameter = Math.max(diameter, deepestChild + secondDeepestChild + 2);

        // Return the height of the current node
        return deepestChild + 1;
    }


    // Create Generic Tree
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

                if (!stack.isEmpty()){
                    stack.peek().children.add(temp);
                }else{
                    root = temp;
                }
                stack.push(temp);
            }
        }
        return root;
    }

       /*


       Tree Structure:

            10
        /   |   \
      20    30    40
     /  \   /|\     \
    50  60 70 80 90  100
             /  \
           110  120

     */

    // LevelOrder Linewise to display the tree
    public static void levelOrderDisplay(Node root) {
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
