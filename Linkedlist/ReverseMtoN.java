import java.io.IOException;
import java.util.*;

//Reverse linked list from position M to N
class LinkedList {
    public static void main(String[] args) throws IOException{
        ListNode l = new ListNode(4);
        ListNode dummy = l,d=l;
        for (int i = 7; i < 26; i=i+3) {
            ListNode t = new ListNode(i);
            l.next = t;
            l=l.next;
        }
        l.next=null;
        while (dummy!=null) {
            System.out.print(dummy.val + " --> ");
            dummy=dummy.next;
        }

        ListNode ans = reverseBetween(d,2,5);
        System.out.println("\nReversed from m to n:");
        while (ans!=null) {
            System.out.print(ans.val + " --> ");
            ans=ans.next;
        }
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head, pre = dummy, leftTail=null, temp = null;
        for (int i=1; i<=right && cur!=null; i++) {
            temp = cur.next; 
            if (i>=left) {
                cur.next = pre;
                if (i==left)
                    leftTail = pre; 
            }
            pre = cur;
            cur = temp;
        }
        temp = leftTail.next; 
        leftTail.next = pre; 
        temp.next = cur; 
        return dummy.next;

    }
}
