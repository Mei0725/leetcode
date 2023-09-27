package leetcode_test;

public class ConstructQuadTree {
	
	/**
	 * node of Quad-Tree.
	 *
	 */
	class Node {
	    public boolean val;
	    public boolean isLeaf;
	    public Node topLeft;
	    public Node topRight;
	    public Node bottomLeft;
	    public Node bottomRight;

	    
	    public Node() {
	        this.val = false;
	        this.isLeaf = false;
	        this.topLeft = null;
	        this.topRight = null;
	        this.bottomLeft = null;
	        this.bottomRight = null;
	    }
	    
	    public Node(boolean val, boolean isLeaf) {
	        this.val = val;
	        this.isLeaf = isLeaf;
	        this.topLeft = null;
	        this.topRight = null;
	        this.bottomLeft = null;
	        this.bottomRight = null;
	    }
	    
	    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
	        this.val = val;
	        this.isLeaf = isLeaf;
	        this.topLeft = topLeft;
	        this.topRight = topRight;
	        this.bottomLeft = bottomLeft;
	        this.bottomRight = bottomRight;
	    }
	};

	/**
	 * divide grid into 4 sub-grid and then transform them into quad-tree nodes.
	 * 
	 * @param grid
	 * @return
	 */
    public Node construct(int[][] grid) {
        return construct(grid, 0, grid.length, 0, grid.length);
    }

    /**
     * transform matrix in [x1,x2),[y1,y2) into quad-tree.
     * 
     * @param grid
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @return
     */
    public Node construct(int[][] grid, int x1, int x2, int y1, int y2) {
    	// there is only 1 node
        if (x1 + 1 == x2) {
            return new Node(grid[x1][y1] == 1, true);
        }

        int n = (x2 - x1) / 2;
        // System.out.println("result: " + output);
        Node tlNode = construct(grid, x1, x1 + n, y1, y1 + n);
        Node blNode = construct(grid, x1 + n, x2, y1, y1 + n);
        Node trNode = construct(grid, x1, x1 + n, y1 + n, y2);
        Node brNode = construct(grid, x1 + n, x2, y1 + n, y2);
        // check if 4 leaves of this node are all leaves and are same, 
        // if they are, merge them into one node
        if (tlNode.isLeaf && trNode.isLeaf && blNode.isLeaf && brNode.isLeaf && tlNode.val == trNode.val && trNode.val == blNode.val && blNode.val == brNode.val) {
            return new Node(trNode.val, true);
        }
        return new Node(true, false, tlNode, trNode, blNode, brNode);
    }
}
