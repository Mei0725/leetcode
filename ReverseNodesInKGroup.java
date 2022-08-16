package leetcode_test;

public class ReverseNodesInKGroup {
	
	public static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	public static void main(String[] args) {
		ListNode node5 = new ListNode(5);
		ListNode node4 = new ListNode(4, node5);
		ListNode node3 = new ListNode(3, node4);
		ListNode node2 = new ListNode(2, node3);
		ListNode head = new ListNode(1, node2);
//		int k = 3;
		int k = 2;
		ListNode output = null;
		try {
			output = reverseKGroup(head, k);
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
	
    public static ListNode reverseKGroup(ListNode head, int k) {
    	// result is the new head of new list
    	// behind is used to store the node behind every k-length sublist
    	ListNode result = null, behind = null;
    	while (head != null) {
    		ListNode start = head;
    		int index = 0;
    		// check that if there are enough elements to deal with
    		while (index < k && head != null) {
    			index++;
    			head = head.next;
    		}
    		// in the end of list, the elements are less than k, so we can remain them
    		if (index < k) {
    			break;
    		}
    		
    		// newEnd is the end of new k-length sublist, store it so we can change its next in the end
    		// start is the first node, and newStart is the second node for every step before change
    		// nextNode is the first node behind these nodes we change in this step, we need to store it otherwise it may get lost
    		ListNode newEnd = start, newStart = start.next;
    		ListNode nextNode;
    		for (int i = 0; i < k - 1; i++) {
    			nextNode = newStart.next;
    			newStart.next = start;
    			start = newStart;
    			newStart = nextNode;
    		}
    		newEnd.next = newStart;
    		
    		if (result == null) {
    			result = start;
    		} else {
    			behind.next = start;
    		}
    		behind = newEnd;
    	}
    	return result;
    }

}
