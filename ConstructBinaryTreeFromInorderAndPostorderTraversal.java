package leetcode_test;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

	/**
	 * use postorder to get the root of every subtree and inorder to find out the left and right leaves
	 * 
	 * @param inorder
	 * @param postorder
	 * @return
	 */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
    	// use map to find out the root's index in inorder to save time(4ms->2ms)
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(inorder, 0, inorder.length - 1, postorder, postorder.length - 1, map);
    }

    /**
     * create the subtree in [left, right] in inorder
     * 
     * @param inorder          inorder arrays
     * @param left             the left board of inorder in this subtree
     * @param right            the right board of inorder in this subtree
     * @param postorder        postorder, used to find out the root's value
     * @param root             the index of root in postorder
     * @param map              key:value;value:index in inorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int left, int right, int[] postorder, int root, Map<Integer, Integer> map) {
        int node = postorder[root];
        TreeNode res = new TreeNode(node);
        // it means this subtree only have a node root
        if (left == right) {
            return res;
        }

        // i can also find i by search from left to right, but use map can help to save time
        int i = map.get(node);
        // if i is left, then left leave is null
        if (i != left) {
        	// get the new root index in postorder by root deleting 1(root) and (right-i)(the length of right leaf)
            res.left = buildTree(inorder, left, i - 1, postorder, root - (right - i) - 1, map);
        }
        if (i != right) {
            res.right = buildTree(inorder, i + 1, right, postorder, root - 1, map);
        }
        return res;
    }
}
