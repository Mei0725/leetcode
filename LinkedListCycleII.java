package leetcode_test;

public class LinkedListCycleII {

	/**
	 * solve by two pointers.
	 * 
	 * it is similar to FindTheDuplicateNumber.
	 * 
	 * @param head
	 * @return
	 */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        do {
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast && fast != null && fast.next != null);
        if (fast == null || fast.next == null) {
            return null;
        }
        
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
