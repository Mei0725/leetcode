package leetcode_test;

public class PartitionList {
	
	public static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	public static void main(String[] args) {
		ListNode node5 = new ListNode(3);
		ListNode node4 = new ListNode(2, node5);
		ListNode node3 = new ListNode(3, node4);
		ListNode node2 = new ListNode(4, node3);
		ListNode head = new ListNode(1, node2);
		int x = 4;
		ListNode output = null;
		try {
			output = partition(head, x);
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

    public static ListNode partition(ListNode head, int x) {
    	ListNode leftHead = null, rightHead = null, left = null, right = null;
    	while (head != null) {
    		if (head.val < x) {
    			if (left == null) {
    				left = head;
    				leftHead = head;
    			} else {
    				left.next = head;
    				left = left.next;
    			}
    		} else {
    			if (right == null) {
    				right = head;
    				rightHead = head;
    			} else {
    				right.next = head;
    				right = right.next;
    			}
    		}
    		head = head.next;
    	}
    	if (leftHead == null) {
    		return rightHead;
    	}
    	left.next = rightHead;
    	if (right != null) {
        	right.next = null;
    	}
        return leftHead;
    }
}
