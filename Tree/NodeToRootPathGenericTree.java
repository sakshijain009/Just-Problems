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

    // Node to Root Path
    public static ArrayList<Integer> nodeToRootPath(Node node, int data){
        if (node.data==data){
            ArrayList<Integer> ans = new ArrayList<>();
            ans.add(data);
            return ans;
        }

        for(Node child: node.children){
            ArrayList<Integer> result = nodeToRootPath(child,data);
            if(result.size() > 0){
                result.add(node.data);
                return result;
            }
        }

        return new ArrayList<Integer>();
    }

    // Common Ancestor
    public static int commonAncestor(Node root, int data1, int data2){
        ArrayList<Integer> res1 = nodeToRootPath(root,data1);
        ArrayList<Integer> res2 = nodeToRootPath(root,data2);

        int i = res1.size() - 1;
        int j = res2.size() - 1;

        while (i>=0 && j>=0 && res1.get(i)==res2.get(j)){
            i--;
            j--;
        }
        return res1.get(++i);
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

        // Node to root path
        System.out.println(nodeToRootPath(root,110));
        System.out.println(nodeToRootPath(root,50));

        // Common Ancestor
        System.out.println(commonAncestor(root,50,110));
    }
}
