package leetcode_test;

public class RemoveNthNode {
	
	public static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	public static void main(String[] args) {
//		ListNode node5 = new ListNode(5);
//		ListNode node4 = new ListNode(4, node5);
//		ListNode node3 = new ListNode(3, node4);
//		ListNode node2 = new ListNode(2, node3);
//		ListNode head = new ListNode(1, node2);
//		int n = 2;
		ListNode head = new ListNode(1);
		int n = 1;
		ListNode output = null;
		try {
			output = removeNthFromEnd(head, n);
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
	
    public static ListNode removeNthFromEnd(ListNode head, int n) {
    	int length = 1;
    	ListNode leftNode = head;
    	while (leftNode.next != null) {
    		length++;
    		leftNode = leftNode.next;
    	}
    	if (length == n) {
    		return head.next;
    	}
    	
    	leftNode = head;
    	for (int i = 0; i < length - n - 1; i++) {
    		leftNode = leftNode.next;
    	}
    	ListNode rightNode = null;
    	if (leftNode.next != null) {
    		rightNode = leftNode.next.next;
    	}
    	leftNode.next = rightNode;
        return head;
    }
}
