package leetcode_test;

public class SwapNodesInPairs {
	
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
		ListNode output = null;
		try {
			output = swapPairs(head);
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
	
    public static ListNode swapPairs(ListNode head) {
    	if (head == null || head.next == null) {
    		return head;
    	}
    	
    	ListNode node = head;
    	head = head.next;
    	node.next = head.next;
    	head.next = node;
    	while (node.next != null && node.next.next != null) {
    		ListNode node1 = node.next;
    		ListNode node2 = node1.next;
    		node1.next = node2.next;
    		node.next = node2;
    		node2.next = node1;
    		node = node1;
    	}
    	return head;
    }

}
