package leetcode_test;

public class MergeTwoSortedLists {
	
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
		ListNode head2 = new ListNode(1, node2);
		ListNode head1 = new ListNode(1);
		ListNode output = null;
		try {
			output = mergeTwoLists(head1, head2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output.val);
		while (output.next != null) {
			output = output.next;
			System.out.println("result: " + output.val);
		}
	}
	
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    	if (list1 == null) {
    		return list2;
    	} else if (list2 == null) {
    		return list1;
    	}
    	
    	ListNode head;
    	if (list1.val < list2.val) {
    		head = list1;
    		list1 = list1.next;
    	} else {
    		head = list2;
    		list2 = list2.next;
    	}
    	
    	ListNode localNode = head;
    	while(list1 != null && list2 != null) {
        	if (list1.val < list2.val) {
        		localNode.next = list1;
        		list1 = list1.next;
        	} else {
        		localNode.next = list2;
        		list2 = list2.next;
        	}
        	localNode = localNode.next;
    	}
    	if (list1 != null) {
    		localNode.next = list1;
    	} else {
    		localNode.next = list2;
    	}
    	return head;
    }

}
