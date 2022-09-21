package leetcode_test;

public class RotateList {
	
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
		int k = 3;
		ListNode output = null;
		try {
			output = rotateRight(head, k);
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
	
    public static ListNode rotateRight(ListNode head, int k) {
    	// in this case, there is no need to rotate
    	if (head == null || k == 0) {
    		return head;
    	}
    	
    	// length is the length of list
    	int length = 1;
    	// tmp to store the last item of list
    	ListNode tmp = head;
    	while (tmp.next != null) {
    		tmp = tmp.next;
    		length++;
    	}

		// System.out.println("length: " + length);
    	if (k % length == 0) {
    		return head;
    	}
    	// realRemove is the index of new head
    	// to create new list easier, newHead is the previous item of new head
    	int realRemove = length - (k % length);
		// System.out.println("realRemove: " + realRemove);
    	ListNode newHead = head;
		while (realRemove > 1) {
			newHead = newHead.next;
			realRemove--;
		}
    	
    	tmp.next = head;
    	ListNode result = newHead.next;
    	newHead.next = null;
    	return result;
    }
}
