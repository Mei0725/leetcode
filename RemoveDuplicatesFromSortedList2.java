package leetcode_test;

public class RemoveDuplicatesFromSortedList2 {
	
	public static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	public static void main(String[] args) {
		ListNode node5 = new ListNode(4);
		ListNode node4 = new ListNode(3, node5);
		ListNode node3 = new ListNode(3, node4);
		ListNode node2 = new ListNode(2, node3);
		ListNode head = new ListNode(1, node2);
		ListNode output = null;
		try {
			output = deleteDuplicates(head);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(output.val + " ");
		while (output.next != null) {
			output = output.next;
			System.out.print(output.val + " ");
		}
	}
	
    public static ListNode deleteDuplicates(ListNode head) {
    	ListNode node = head;
    	while (node != null) {
        	ListNode next = node.next;
        	while (next != null && next.val == node.val) {
        		next = next.next;
        	}
        	node.next = next;
        	node = next;
    	}
    	return head;
    }

}
