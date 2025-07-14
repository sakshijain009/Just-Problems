/*
💡 Question: Reorder a Linked List in Zig-Zag Fashion

Given a singly linked list, reorder it **in-place** such that the nodes follow this pattern:

L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …

📌 Example:
Input: 1 → 2 → 3 → 4 → 5
Output: 1 → 5 → 2 → 4 → 3

The list is reordered by interleaving the first half and the reversed second half.

Constraints:
- Do it in-place with O(1) extra space.
- Don’t modify node values, only pointers.
*/

class Solution {

    public Node inPlace(Node root) {
        if (root == null || root.next == null) return root;

        // Step 1: Find the middle of the list
        Node slow = root, fast = root;
        Node prev = new Node(-1); // dummy node to help split list
        prev.next = root;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            prev = prev.next;
        }

        // Step 2: Split the list into two halves
        prev.next = null;

        // Step 3: Reverse the second half
        Node revH = reverseLL(slow);

        // Step 4: Merge both halves alternately
        Node curr = root;
        while (curr != null && curr.next != null) {
            Node temp = curr.next;
            curr.next = revH;
            revH = revH.next;
            curr.next.next = temp;
            curr = temp;
        }

        // Step 5: Append any remaining nodes from second half
        while (revH != null) {
            curr.next = revH;
            curr = curr.next;
            revH = revH.next;
        }

        curr.next = null;
        return root;
    }

    // Helper method to reverse a linked list
    Node reverseLL(Node head) {
        Node prev = null;
        Node curr = head;

        while (curr != null) {
            Node temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        return prev;
    }
}
