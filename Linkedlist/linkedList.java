import java.io.IOException;

class Linked {
    public static void main(String[] args) throws IOException {
        LinkedList l = new LinkedList();
        //SHOWING A FEW OPERATIONS
        l.addLast(40);
        l.addLast(20);
        l.addFirst(2);
        l.addAt(23,2);
        l.display();
        l.reverseData();
        l.display();
        l.reversePointer();
        l.display();
        l.removeFirst();
        l.display();
        l.removeAt(4);
    }

    //NODE CLASS TO CREATE NODES
    public static class Node{
        int data;
        Node next;
    }

    //LINKED LIST CLASS HAVING LINKED LIST OPERATIONS
    public static class LinkedList{
        Node head;
        Node tail;
        int size;

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

        //Return size of the linked list
        public int size(){
            return size;
        }

        //Display the linked list
        public void display(){
            Node temp=head;
            while (temp!=null){
                System.out.print(temp.data + " --> ");
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

        //Remove the element at given index of the linked list
        public void removeAt(int index){
            if (index<0 || index>=size){
                System.out.println("Invalid Operation");
            }else if (index==0){
                removeFirst();
            }else if (index==size-1){
                removeLast();
            }else{
                Node temp = head;
                for (int i = 0; i < index-1; i++) {
                    temp=temp.next;
                }
                Node t = temp.next;
                temp.next=temp.next.next;
                t.next=null;
                size--;
            }
        }

        //Remove the last element of the linked list
        public void removeLast(){
            if (size==0){
                System.out.println("List is empty");
            }else if (size==1){
                head=tail=null;
                size=0;
            }else{
                Node temp = head;
                for (int i = 0; i < size-2; i++) {
                    temp=temp.next;
                }
                tail=temp;
                temp.next=null;
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

        //Return last element of the linked list
        public int getLast(){
            if (size==0){
                System.out.println("List is empty");
                return -1;
            }else {
                return tail.data;
            }
        }

        //Return element at particular index of the linked list
        public int getAt(int index){
            if (size==0){
                System.out.println("List is empty");
                return -1;
            }else if (index<0 || index>=size){
                System.out.println("Invalid Arguments");
                return -1;
            }else {
                Node temp=head;
                for (int i = 0; i < index; i++) {
                    temp=temp.next;
                }
                return temp.data;
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

        //Add element at particular position
        public void addAt(int val, int index){
            if (index<0 || index>size){
                System.out.println("Invalid Arguments");
            }else if (index==0){
                addFirst(val);
            }else if (index==size){
                addLast(val);
            }else{
                Node node = new Node();
                node.data=val;
                Node temp=head;
                for (int i = 0; i < index-1; i++) {
                    temp=temp.next;
                }
                node.next=temp.next;
                temp.next=node;
                size++;
            }
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
    }
}