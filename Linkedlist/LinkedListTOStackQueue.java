import java.io.IOException;
import java.util.LinkedList;

class Linked {
    public static void main(String[] args) throws IOException {

        //Linked list as Stack Adapter
        System.out.println("Linked list as Stack Adapter");
        LinkedListStack Lst = new LinkedListStack();
        Lst.push(12);
        Lst.push(3);
        Lst.push(41);
        Lst.displayS();
        System.out.println("Peek : " + Lst.top());
        Lst.pop();
        Lst.displayS();
        System.out.println("Peek : " + Lst.top());
        Lst.push(100);
        Lst.displayS();
        System.out.println("Peek : " + Lst.top());

        //Linked list as Queue Adapter
        System.out.println("\nLinked list as Queue Adapter");
        LinkedListQueue Lq = new LinkedListQueue();
        Lq.add(3);
        Lq.add(45);
        Lq.add(12);
        Lq.displayQ();
        System.out.println("Peek : " + Lq.peek());
        Lq.remove();
        Lq.displayQ();
        System.out.println("Peek : " + Lq.peek());
        Lq.add(100);
        Lq.displayQ();
        System.out.println("Peek : " + Lq.peek());
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
            System.out.print("From top to last: ");
            while (temp!=null){
                System.out.print(temp.data + "  ");
                temp=temp.next;
            }
            System.out.println();
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

    //LINKED LIST TO STACK
    public static class LinkedListStack{
        LinkedList list;

        public LinkedListStack(){
            list = new LinkedList();
        }

        int size(){
            return list.size();
        }

        void push(int val){
            list.addFirst(val);
        }

        void pop(){
            list.removeFirst();
        }

        int top(){
            return list.getFirst();
        }

        void displayS(){
            list.display();
        }

    }

    //LINKED LIST TO QUEUE
    public static class LinkedListQueue{
        LinkedList list;

        public LinkedListQueue(){
            list = new LinkedList();
        }

        int size(){
            return list.size();
        }

        void add(int val){
            list.addLast(val);
        }

        void remove(){
            list.removeFirst();
        }

        int peek(){
            return list.getFirst();
        }

        void displayQ(){
            list.display();
        }
    }
}