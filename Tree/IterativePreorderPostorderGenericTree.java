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

    static class Pair {
        int state;
        Node node;

        Pair(int state, Node node) {
            this.node = node;
            this.state = state;
        }
    }

    public static void main(String[] args){
        int[] array = {10,20,50,-1,60,-1,-1,30,70,-1,80,110,-1,120,-1,-1,90,-1,-1,40,100,-1,-1,-1};
        Node root = createTree(array);

        interativePreorder_Postorder(root);
    }

    public static void interativePreorder_Postorder(Node root) {
        Stack<Pair> stack = new Stack<>();

        // Push the root node onto the stack with an initial state of -1
        stack.push(new Pair(-1, root));

        StringBuilder pre = new StringBuilder();
        StringBuilder post = new StringBuilder();

        while (!stack.isEmpty()) {
            Pair top = stack.peek();

            if (top.state == -1) {  // If the state is -1, this node is being visited for the first time (preorder)
                pre.append(top.node.data).append(" ");
                top.state++;
            } else if (top.node.children.size() > top.state) {   // Push the next child onto the stack with an initial state of -1
                stack.push(new Pair(-1, top.node.children.get(top.state)));
                top.state++;
            } else {   // If all children of this node have been processed, add it to postorder
                post.append(top.node.data).append(" ");
                stack.pop();
            }
        }

        System.out.println("PreOrder " + pre);
        System.out.println("PostOrder " + post);
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
}
