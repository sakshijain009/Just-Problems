import java.io.IOException;

// Problems addressed in this file are :------->
// Fold a linked list

class Linked {

    //Globally
    private static Node helper = null;
    private static int flag = 0;

    public static void main(String[] args) throws IOException {
        LinkedList list = new LinkedList();
        for (int i = 1; i < 10; i++) {
            list.addLast(i);
        }
        list.display();

        System.out.println("\nFolding a Linked List with odd elements");
        foldLL(list);
        list.display();
        System.out.println();

        LinkedList l = new LinkedList();
        for (int i = 1; i < 7; i++) {
            l.addLast(i);
        }
        l.display();

        System.out.println("\nFolding a Linked List with even elements");
        foldLL(l);
        l.display();
    }

    //Fold a linked list
    public static void foldLL(LinkedList list){
        helper = list.head;
        flag=0;
        foldHelper(list.head);
    }

    public static void foldHelper(Node root){
        if (root==null) return;
        foldHelper(root.next);

        if (flag==1) return;

        Node temp = null;
        if (helper!=root && helper.next!=root){
            temp = helper.next;
            helper.next=root;
            root.next=temp;
        }else if (helper==root){
            helper.next=null;
            flag=1;
        }else if (helper.next==root){
            root.next = null;
            flag=1;
        }

        helper = temp;

    }

    //NODE CLASS TO CREATE NODES
    public static class Node{
        int data;
        Node next;
    }

    //LINKED LIST CLASS HAVING LINKED LIST OPERATIONS
    public static class LinkedList {
        Node head;
        Node tail;
        int size;

        //Return size of the linked list
        public int size(){
            return size;
        }


        //Display the linked list
        public void display(){
            Node temp=head;
            System.out.print("Linked list : ");
            while (temp.next!=null){
                System.out.print(temp.data + " -> ");
                temp=temp.next;
            }
            System.out.print(temp.data);
            System.out.println();
        }

        //Add a node at the end
        public void addLast(int val){
            Node temp = new Node();
            temp.data=val;
            temp.next=null;
            if (size==0){
                head=tail=temp;
            }else {
                tail.next=temp;
                tail=temp;
            }
            size++;
        }

    }
}