package leetcode_test;

public class MinimumDepthOfBinaryTree {

	/**
	 * solve by depth-first search.
	 * 
	 * @param root
	 * @return
	 */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return minDepth(root, 1);
    }

    public int minDepth(TreeNode root, int depth) {
        if (root.left == null && root.right == null) {
            return depth;
        }
        // it means there is at least one side is not null.
        // so I can't simply get the min result of 2 side because of null side the result should be less
        int res = Integer.MAX_VALUE;
        if (root.left != null) {
            res = Math.min(res, minDepth(root.left, depth + 1));
        }
        if (root.right != null) {
            res = Math.min(res, minDepth(root.right, depth + 1));
        }
        return res;
    }
}
