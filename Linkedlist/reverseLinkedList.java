import java.io.IOException;

// Problems addressed in this file are :------->
// reversing using recursion
// reversing using iteration
// Checking palindrome for linked list

class Linked {

    //Globally
    private static Node helper = null;
    private static int flag = 0;

    public static void main(String[] args) throws IOException {
        LinkedList list = new LinkedList();
        for (int i = 1; i <= 7; i++) {
            list.addLast(i);
        }
        list.display();
        System.out.print("Printing in reverse : ");
        printReverseLL(list.head);

        System.out.println("\n\nReversing linked list using recursion by pointer");
        reversePR(list);
        list.display();

        System.out.println("\nReversing linked list using recursion by data");
        reverseDR(list);
        list.display();

        System.out.println("\nReversing linked list using iteration by pointer");
        list.reversePointer();
        list.display();

        System.out.println("\nReversing linked list using iteration by data");
        list.reverseData();
        list.display();

        System.out.println("\nChecking if list is a palindrome");
        System.out.println(isPalindrome(list));

        LinkedList r = new LinkedList();
        r.addLast(1);
        r.addLast(2);
        r.addLast(2);
        r.addLast(1);
        r.display();
        System.out.println(isPalindrome(r));
    }

    //Print reverse of a linked list-------------------------
    public static void printReverseLL(Node root){
        if (root==null) return;
        printReverseLL(root.next);
        System.out.print(root.data + " --> ");
    }

    //Reverse linked list using recursion---------------------
    public static void reverseHelperll(Node root, Node tail){
        if (root==null){
            return;
        }
        reverseHelperll(root.next,tail);
        if (root != tail){
            root.next.next=root;
        }
    }

    public static void reversePR(LinkedList li){
        reverseHelperll(li.head, li.tail);
        li.head.next=null;
        Node temp = li.head;
        li.head=li.tail;
        li.tail=temp;
    }

    //Reverse linked list using data by recursion------------------------
    public static void reverseDR(LinkedList li){
        helper = li.head;
        reverseHelperllData(li.head);
    }

    public static void  reverseHelperllData(Node root){
        if (root == null) return;
        reverseHelperllData(root.next);

        if (helper==root || helper.next==root){
            flag=1;
        }
        if (flag!=1){
            int temp = root.data;
            root.data=helper.data;
            helper.data=temp;
        }

        helper=helper.next;
    }

    //Reverse linked list using data by recursion------------------------
    public static boolean isPalindrome(LinkedList li){
        helper = li.head;
        return palindromeCheck(li.head);
    }

    public static boolean  palindromeCheck(Node root){
        if (root == null) return true;
        boolean ans = palindromeCheck(root.next);
        if (!ans) return false;
        boolean b = helper.data==root.data?true:false;
        helper=helper.next;
        return b;
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

        //Reverse Linked List through data--------------------------
        private Node getNodeAt(int index){
            Node temp = head;
            for (int i = 0; i < index; i++) {
                temp=temp.next;
            }
            return temp;
        }

        public void reverseData(){
            int l=0;
            int r=size-1;
            while (l<r){
                Node left = getNodeAt(l);
                Node right = getNodeAt(r);

                int temp=left.data;
                left.data=right.data;
                right.data=temp;

                l++;
                r--;
            }
        }

        //Reverse Linked List through pointer--------------------------
        public void reversePointer(){
            if (size==0 || size==1) return;
            Node p = head;
            Node q = head.next;
            Node temp = new Node();
            while (q!=null){
                temp=q.next;
                q.next=p;
                p=q;
                q=temp;
            }
            temp=tail;
            tail=head;
            head=temp;
            tail.next=null;
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

        //Remove the first element of the linked list
        public void removeFirst(){
            if (size==0){
                System.out.println("List is empty");
            }else if (size==1){
                head=tail=null;
                size=0;
            }else{
                head=head.next;
                size--;
            }
        }

        //Return first element of the linked list
        public int getFirst(){
            if (size==0){
                System.out.println("List is empty");
                return -1;
            }else {
                return head.data;
            }
        }

        //Add element at first position
        public void addFirst(int val){
            Node temp = new Node();
            temp.data=val;
            temp.next=head;
            head = temp;
            if (size==0){
                tail=temp;
            }
            size++;
        }

    }
}