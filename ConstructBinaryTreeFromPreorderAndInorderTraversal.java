package leetcode_test;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
	
	/**
	 * solve the problem by divide it into 2 parts: left and right,
	 * and then get the root node in each part
	 * 
	 * @param preorder
	 * @param inorder
	 * @return
	 */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return findRoot(preorder, 0, preorder.length - 1, inorder, 0);
    }

    /**
     * get the root of preorder's preLeft-preRight,
     * then find the left nodes and right nodes by finding the root node in inorder
     * 
     * @param preorder preorder
     * @param preLeft  the left edge of special part in preorder
     * @param preRight the right edge
     * @param inorder  inorder
     * @param inLeft   the left edge of special part in inorder, it is used to reduce the time to find the index of root
     * @return
     */
    public TreeNode findRoot(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft) {
    	// these 2 cases mean it is the last leaf of a tree
        if (preLeft > preRight) {
            return null;
        } else if (preLeft == preRight) {
            return new TreeNode(preorder[preLeft]);
        }
        
        // find the root in inorder to divide all leaves into 2 parts
        TreeNode root = new TreeNode(preorder[preLeft]);
        int length = 0;
        while (preorder[preLeft] != inorder[inLeft + length]) {
            length++;
        }
        // find the root in left part and right part
        root.left = findRoot(preorder, preLeft + 1, preLeft + length, inorder, inLeft);
        root.right = findRoot(preorder, preLeft + length + 1, preRight, inorder, inLeft + length + 1);
        return root;
    }

    /**
     * this solution spends less time but more space, 
     * since it uses a map to easily find the index of root in inorder
     * 
     * @param P preorder
     * @param I inorder
     * @return
     */
    public TreeNode buildTreeLessTime(int[] P, int[] I) {
        Map<Integer, Integer> M = new HashMap<>();
        for (int i = 0; i < I.length; i++)
            M.put(I[i], i);
        return splitTree(P, M, 0, 0, I.length-1);
    }
    
    /**
     * 
     * @param P        preorder
     * @param M        a map used to easily find the index of every items in inorder
     * @param pix      the index of root in preorder
     * @param ileft    the left edge of this sub-tree in preorder
     * @param iright   the right edge of this sub-tree in preorder
     * @return
     */
    private TreeNode splitTree(int[] P, Map<Integer, Integer> M, int pix, int ileft, int iright) {
    	// rval is the root's value, imid is the index of root in inorder
        int rval = P[pix], imid = M.get(rval);
        TreeNode root = new TreeNode(rval);       
        // build the left leaf
        if (imid > ileft)
            root.left = splitTree(P, M, pix+1, ileft, imid-1);
        // build the right leaf, imid-ileft is equal to length in findRoot
        if (imid < iright)
            root.right = splitTree(P, M, pix+imid-ileft+1, imid+1, iright);
        return root;
    }
}
