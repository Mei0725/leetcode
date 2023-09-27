package leetcode_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph {
	public static class Node {
	    public int val;
	    public List<Node> neighbors;
	    public Node() {
	        val = 0;
	        neighbors = new ArrayList<Node>();
	    }
	    public Node(int _val) {
	        val = _val;
	        neighbors = new ArrayList<Node>();
	    }
	    public Node(int _val, ArrayList<Node> _neighbors) {
	        val = _val;
	        neighbors = _neighbors;
	    }
	}

	public static void main(String[] args) {
		Node input = new Node(1);
		Node output = null;
		try {
			output = cloneGraph(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output.val);
	}
	
	/**
	 * this solution is similar to cloneGraph0
	 * but compare to cloneGraph0, it only create new Node when first read it and add all neighbors after creation
	 * 
	 * @param node
	 * @return
	 */
	public static Node cloneGraph(Node node) {
		if (node == null)
			return null;
		Map<Node, Node> m = new HashMap<>();
		return create(m, node);
	}

	public static Node create(Map<Node, Node> m, Node node) {
		Node temp = new Node(node.val);
		m.put(node, temp);
		for (Node n : node.neighbors) {
			if (m.containsKey(n))
				temp.neighbors.add(m.get(n));
			else
				temp.neighbors.add(create(m, n));
		}
		return temp;
	}

	/**
	 * use newNodes to store the created node
	 * 
	 * @param node
	 * @return
	 */
    public static Node cloneGraph0(Node node) {
        if (node == null) {
            return null;
        }

        Map<Integer, Node> newNodes = new HashMap<>();
        ArrayList<Node> newNeighbors = new ArrayList<>();
        for (Node neighbor : node.neighbors) {
            Node newOne = new Node(neighbor.val);
            newNeighbors.add(newOne);
            newNodes.put(neighbor.val, newOne);
        }
        Node result = new Node(node.val, newNeighbors);
        newNodes.put(result.val, result);

        for (Node neighbor : node.neighbors) {
            cloneGraph(neighbor, newNodes);
        }
        return result;
    }

    public static void cloneGraph(Node node, Map<Integer, Node> newNodes) {
		// System.out.println("node: " + node.val);
    	// this node may create in other loop
        Node newOne = newNodes.get(node.val);
        if (newOne == null) {
            newOne = new Node(node.val);
            newNodes.put(node.val, newOne);
        } else if (!newOne.neighbors.isEmpty()) {
        	// when neighbors is not empty, it means this node has handled or is handling in other cycle
        	// so we can return immediately to avoid infinite loop
            return;
        }

        for (Node neighbor : node.neighbors) {
            Node newNeighbor;
            if (newNodes.containsKey(neighbor.val)) {
                newNeighbor = newNodes.get(neighbor.val);
            } else {
                newNeighbor = new Node(neighbor.val);
                newNodes.put(neighbor.val, newNeighbor);
            }
            newOne.neighbors.add(newNeighbor);
            // now newOne have added neighbors
            // if it is gotten in other loop, it will return immediately
            cloneGraph(neighbor, newNodes);
        }
    }
}
