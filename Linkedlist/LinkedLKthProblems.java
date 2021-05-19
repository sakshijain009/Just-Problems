import java.io.IOException;

// Problems addressed in this file are :------->
//K Reverse of Linked List
//Merge two linked list
//Kth element from end of a linked list
//Removing Duplicates from a linked list


class Linked {
    public static void main(String[] args) throws IOException {
        LinkedList list = new LinkedList();
        LinkedList list2 = new LinkedList();
        LinkedList list1 = new LinkedList();
        for (int i = 1; i <= 7; i++) {
            list.addLast(i);
        }
        list.display();
        System.out.println("Kth element where k is 4 : "+ list.kthEleFromLast(4));
        System.out.println("Mid element is : "+list.mid());

        //Merging two lists=======================================
        System.out.println("\nMerging two sorted lists");
        for (int i = 1; i <12; i=i+3) {
            list1.addLast(i);
        }
        for (int i = 2; i <9; i=i+2) {
            list2.addLast(i);
        }
        System.out.print("The First ");
        list1.display();
        System.out.print("The Second ");
        list2.display();
        LinkedList ans = new LinkedList();
        ans = mergeTwoSortedList(list1,list2);
        ans.display();

        //Remove duplicates
        System.out.println("\nRemove duplicates");
        removeDuplicateFromSortedList(ans).display();

        //K - reverse
        for (int i = 1; i < 11; i++) {
            ans.addLast(i);
        }
        System.out.println("\nK reverse:");
        ans.display();
        kreverse(3,ans).display();
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

        //K th element from last
        public int kthEleFromLast(int k){
            if (k>=size) return -1;
            Node p = head;
            Node q = head;

            for (int i = 0; i < k; i++) {
                q=q.next;
            }

            while (q!=tail){
                q=q.next;
                p=p.next;
            }
            return p.data;
        }

        public int mid(){
            Node fast= head;
            Node slow = head;
            while (fast.next!=null && fast.next.next!=null){
                fast=fast.next.next;
                slow=slow.next;
            }
            return slow.data;
        }
    }

    public static LinkedList mergeTwoSortedList(LinkedList l1, LinkedList l2){
        LinkedList newList = new LinkedList();
        Node p1 = l1.head;
        Node p2 = l2.head;

        while (p1!= null && p2!=null){
            if (p1.data<p2.data){
                newList.addLast(p1.data);
                p1=p1.next;
            }else {
                newList.addLast(p2.data);
                p2=p2.next;
            }
        }

        if (p1==null){
            while (p2!=null){
                newList.addLast(p2.data);
            }
        }else{
            newList.addLast(p1.data);
        }

        return newList;
    }

    public static LinkedList removeDuplicateFromSortedList(LinkedList l){
        LinkedList newList = new LinkedList();
        while (l.size()>0){
            int val = l.getFirst();
            l.removeFirst();

            if (newList.size()==0 || newList.tail.data!=val) newList.addLast(val);
        }
        return newList;
    }

    public static LinkedList kreverse(int k,LinkedList l){
        LinkedList prev = null;

        while (l.size()>0){
            LinkedList curr = new LinkedList();

            if (l.size()>=k){
                for (int i =0; i < k ; i++){
                    int val = l.getFirst();
                    l.removeFirst();
                    curr.addFirst(val);
                }
            }else {
                int si = l.size();
                for (int i = 0; i < si; i++) {
                    int val = l.getFirst();
                    l.removeFirst();
                    curr.addFirst(val);
                }
            }

            if (prev == null){
                prev = curr;
            }else{
                prev.tail.next=curr.head;
                prev.tail=curr.tail;
                prev.size=curr.size;
            }
        }
        return prev;
    }

}
