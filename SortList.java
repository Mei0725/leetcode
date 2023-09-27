package leetcode_test;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class SortList {

	/**
	 * solve by Divide and Conquer.
	 * 
	 * start by single node, and conquer every 2 neighboring lists until it because a list.
	 * 
	 * @param head
	 * @return
	 */
    public ListNode sortListByDivideAndConquer(ListNode head) {
        if (head == null) {
            return head;
        }

        // start from 2 nodes instead of single can help to save time(15ms->11ms)
        // to mark the end of list, the end of list must be set as null
        Queue<ListNode> nodes = new LinkedList<>();
        while (head != null && head.next != null) {
            ListNode n1 = head, n2 = head.next;
            head = head.next.next;
            if (n1.val <= n2.val) {
                nodes.add(n1);
                n2.next = null;
            } else {
                nodes.add(n2);
                n2.next = n1;
                n1.next = null;
            }
        }
        // get the final node
        if (head != null) {
            nodes.add(head);
        }

        // if there are more than 2 list, conquer neighboring 2.
        while (nodes.size() > 1) {
        	// to store the new lists
            Queue<ListNode> tq = new LinkedList<>();
            // if there are more than 2 list, conquer the first 2
            while (nodes.size() > 1) {
                ListNode n1 = nodes.poll();
                ListNode n2 = nodes.poll();
                ListNode tmp;
                // get the start node, and put it into tq
                if (n1.val < n2.val) {
                    tmp = n1;
                    n1 = n1.next;
                } else {
                    tmp = n2;
                    n2 = n2.next;
                }
                tq.offer(tmp);
                // move the the end
                while (n1 != null && n2 != null) {
                    if (n1.val < n2.val) {
                        tmp.next = n1;
                        tmp = n1;
                        n1 = n1.next;
                    } else {
                        tmp.next = n2;
                        tmp = n2;
                        n2 = n2.next;
                    }
                }
                // handle the rest list
                if (n1 != null) {
                    tmp.next = n1;
                }
                if (n2 != null) {
                    tmp.next = n2;
                }
            }
            // handle the last list
            if (!nodes.isEmpty()) {
                tq.add(nodes.poll());
            }
            nodes = tq;
        }
        return nodes.poll();
    }
    

    /**
     * solve by PriorityQueue.
     * 
     * store all nodes into PriorityQueue and poll them by val.
     * this solution is easier to understand and spend less space but more time(37ms)
     * 
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return head;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>((n1, n2) -> (n1.val - n2.val));
        ListNode node = head;
        while (node != null) {
            queue.offer(node);
            node = node.next;
        }

        ListNode res = queue.poll();
        node = res;
        while (!queue.isEmpty()) {
            ListNode tmp = queue.poll();
            node.next = tmp;
            node = tmp;
        }
        // avoid create cycle
        node.next = null;
        return res;
    }
}
