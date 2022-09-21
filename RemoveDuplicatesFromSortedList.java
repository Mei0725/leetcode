package leetcode_test;

public class RemoveDuplicatesFromSortedList {
	
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
    	ListNode result = null, end = null;
    	int count;
    	while (head != null) {
    		// count is the number of nodes which have the same value as head
    		count = 1;
        	ListNode next = head.next;
        	while (next != null && next.val == head.val) {
        		count++;
        		next = next.next;
        	}
        	if (count == 1) {
        		if (result == null) {
        			result = head;
        		} else {
        			end.next = head;
        		}
        		end = head;
        	}
        	head = next;
    	}
    	// to head the case that end is not the end of the result list
    	// since end and result may be null, it must make sure that end is not null to avoid error
    	if (end != null) {
        	end.next = null;
    	}
    	return result;
    }

}
