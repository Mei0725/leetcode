package leetcode_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReverseLinkedList2 {
	
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
//		int left = 2;
//		int right = 4;
		int left = 1;
		int right = 5;
		ListNode output = null;
		try {
			output = reverseBetween(head, left, right);
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

    public static ListNode reverseBetween(ListNode head, int left, int right) {
    	// when left==right, there is no need to reverse
    	if (left == right) {
    		return head;
    	}
    	
    	// preNode is the node before left
    	// node1 is the node in left
    	// node is the local node, and node2 is node next to node
    	// node3 is the node next to right
    	ListNode preNode = null, node1 = null, node2 = null, node3 = null, node = head;
    	int index = 1;
    	// find out the node before start
    	while (index < left - 1) {
    		node = node.next;
    		index++;
    	}
    	if (left != 1) {
    		preNode = node;
    	}
    	// put node in the left
    	if (index == left - 1) {
    		index++;
    		node = node.next;
    	}
    	// put node1 in the left
    	node1 = node;
    	node2 = node.next;
    	index++;
    	// reverse the list between left and right
    	while (index < right) {
    		node3 = node2.next;
    		node2.next = node;
    		node = node2;
    		node2 = node3;
    		index++;
    	}
    	// handle the end
    	node3 = node2.next;
    	node2.next = node;
    	node1.next = node3;
    	// handle the case that left=1
    	if (preNode == null) {
    		return node2;
    	}
    	preNode.next = node2;
    	return head;
    }

}
