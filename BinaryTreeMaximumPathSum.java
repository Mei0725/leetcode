package leetcode_test;

public class BinaryTreeMaximumPathSum {

	/**
	 * solve by dfs.
	 * check every node in a tree, and pass 2 value into the next function: 
	 *             0. the max path sum;
	 *             1. the max path sum which can be added to its root;
	 * 
	 * the value1 can be stored as a global variable to save space.
	 * 
	 * @param root
	 * @return
	 */
    public int maxPathSum(TreeNode root) {
        return maxPathSumInTwoWay(root)[0];
    }

    /**
     * check every node and return 2 value into the next function:
	 *             0. the max path sum;
	 *             1. the max path sum which can be added to its root;
     * 
     * @param root
     * @return
     */
    public int[] maxPathSumInTwoWay(TreeNode root) {
    	// if root is null, return the value smaller than the min legal one
    	// do not return Integer.MIN_VALUE because when it can be out of range when adding the left and right value together
        if (root == null) {
            return new int[]{-1001, -1001};
        }
        
        // get the max value from left, right, and the path through root
        int[] left = maxPathSumInTwoWay(root.left), right = maxPathSumInTwoWay(root.right);
        int sum0 = Math.max(left[0], right[0]), passRoot = root.val;
        // check if left node is null
        if (left[1] >= -1000) {
            passRoot += left[1];
        }
        if (right[1] >= -1000) {
            passRoot += right[1];
        }
        sum0 = Math.max(sum0, passRoot);
        
        // sum1 is the sum of a path that can be added to the root's parent
        int sum1 = root.val;
        if (left[1] > 0 && left[1] > right[1]) {
            sum1 += left[1];
        } else if (right[1] > 0 && right[1] > left[1]){
            sum1 += right[1];
        }
        // check if sum1 is the max one.
        return new int[]{Math.max(sum1, sum0), sum1};
    }
}
