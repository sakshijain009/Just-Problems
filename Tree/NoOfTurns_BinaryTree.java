import java.util.*;

class Tree {
    // Used: Node to root path in Binary Tree
    // Q: No of turns between 2 nodes in a tree
    
    class Pair {
        Node node;
        int dir;

        Pair(Node node, int dir) {
            this.node = node;
            this.dir = dir;
        }
    }

    class Node{
        int data;
        Node left,right;
        Node(int d){
            data=d;
            left=right=null;
        }
    }

    static int NumberOfTurns(Node root, int first, int second)
    {
        ArrayList<Pair> pFirst = nodeToRootPath(root, first);
        ArrayList<Pair> pSecond = nodeToRootPath(root, second);

        int i = pFirst.size() - 1;
        int j = pSecond.size() - 1;
        while(i>=0 && j>=0) {
            if(pFirst.get(i).node != pSecond.get(j).node) break;
            i--;
            j--;
        }

        int turn = 0;

        while(i>0) {
            if(pFirst.get(i).dir != pFirst.get(i+1).dir) turn++;
            i--;
        }

        while(j>0) {
            if(pSecond.get(j).dir != pSecond.get(j+1).dir) turn++;
            j--;
        }

        if((i<0 && turn>0) || (j<0 && turn>0)) return turn;
        else if((i<0 && turn==0) || (j<0 && turn==0)) return -1;

        return turn+1;

    }

    ArrayList<Pair> nodeToRootPath(Node root, int val) {
        if(root==null){
            return new ArrayList<Pair>();
        }

        if(root.data == val) {
            ArrayList<Pair> temp = new ArrayList<Pair>();
            temp.add(new Pair(root, 2)); // no children
            return temp;
        }

        ArrayList<Pair> temp1 = nodeToRootPath(root.left, val);
        if(temp1.size() > 0) {
            temp1.add(new Pair(root, 0)); //Left turn
            return temp1;
        }

        ArrayList<Pair> temp2 = nodeToRootPath(root.right, val);
        if(temp2.size() > 0) {
            temp2.add(new Pair(root, 1)); //Right turn
            return temp2;
        }

        return new ArrayList<Pair>();

    }
}
