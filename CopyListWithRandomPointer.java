package leetcode_test;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {
	class Node {
	    int val;
	    Node next;
	    Node random;

	    public Node(int val) {
	        this.val = val;
	        this.next = null;
	        this.random = null;
	    }
	}
	
	/**
	 * store all nodes and their random in a map, key is the old node and value is the new node.
	 * check nodes from head to end, 
     * for every node, get it from map firstly, if it don't exist, create a new one.
	 * 
	 * @param head
	 * @return
	 */
    public Node copyRandomList(Node head) {
        Node newHead = null, tmp = null;
        Map<Node, Node> store = new HashMap<>();
        while (head != null) {
            // get node itself
            Node node = store.getOrDefault(head, new Node(head.val));
            // node should be added into map immediately because random maybe itself
            store.put(head, node);
            
            // get node's random if it is not null
            Node random = null;
            if (head.random != null) {
                random = store.getOrDefault(head.random, new Node(head.random.val));
                store.put(head.random, random);
            }
            node.random = random;

            // handle the first node
            if (newHead == null) {
                newHead = node;
            } else {
                tmp.next = node;
            }
            //go to the next node
            tmp = node;
            head = head.next;
        }
        // System.out.println("result: " + newHead.val);
        return newHead;
    }
}
