/*
💡 Question: Rearranging a Linked List by Even and Odd Values

Given a singly linked list, group all nodes with even values before nodes with odd values,
while preserving the original relative order of even and odd nodes.

📌 Example:
Input: 1 → 2 → 3 → 4 → 5
Output: 2 → 4 → 1 → 3 → 5

Nodes with even data come first, followed by nodes with odd data.
*/

class Main {

    // Node definition
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Method to divide the list into even first, then odd (preserving order)
    static Node divide(Node head) {
        Node dummyEven = new Node(-1);
        Node dummyOdd = new Node(-1);

        Node even = dummyEven;
        Node odd = dummyOdd;
        Node curr = head;

        while (curr != null) {
            if (curr.data % 2 == 0) {
                even.next = curr;
                even = even.next;
            } else {
                odd.next = curr;
                odd = odd.next;
            }
            curr = curr.next;
        }

        // Terminate both lists properly
        odd.next = null;
        even.next = dummyOdd.next;

        return dummyEven.next;
    }

    // Helper method to print the list
    static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " → ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    // Main method for testing
    public static void main(String[] args) {
        // Create test list: 1 → 2 → 3 → 4 → 5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        System.out.println("Original List:");
        printList(head);

        Node result = divide(head);

        System.out.println("\nRearranged List (Even before Odd):");
        printList(result);
    }
}
