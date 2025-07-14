/*
💡 Question: Find the First Node of the Loop in a Linked List

You are given the head of a singly linked list. If there is a **cycle (loop)** in the list,
return the **node where the cycle begins**. If there is no cycle, return `null`.

📌 Example:
Input: 1 → 2 → 3 → 4 → 5 → 6 → 3 (cycle starts again at node 3)
Output: Node with value 3

✅ Logic (Floyd's Cycle Detection Algorithm):

1. Use two pointers: `slow` and `fast`.
   - `slow` moves one step at a time.
   - `fast` moves two steps at a time.

2. If there's a cycle, `slow` and `fast` will eventually meet inside the loop.

3. Once they meet, reset `slow` to head.
   Move both `slow` and `fast` one step at a time.
   They will meet again at the **start of the loop**.

🔍 Why does this work?
Let:
- L = distance from head to start of cycle
- k = distance from cycle start to meeting point
- C = length of the cycle

From the equations:
  2(L + k) = L + k + mC → L = mC - k

Which means the same number of steps from:
- head to cycle start, and
- meeting point to cycle start (in loop)
So, both pointers meet at the loop start.
*/

class Solution {
    public static Node findFirstNode(Node head) {
        Node slow = head, fast = head;

        // Step 1: Detect cycle using slow and fast pointers
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (slow == fast) {
                break;
            }
        }

        // No cycle
        if (slow != fast) return null;

        // Step 2: Find start of the cycle
        slow = head;

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow; // Start of the loop
    }
}
