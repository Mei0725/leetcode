package leetcode_test;

public class MinimumAbsoluteDifferenceInBST {

	/**
	 * solve the problem by DFS
	 * 
	 * @param root
	 * @return
	 */
    public int getMinimumDifference(TreeNode root) {
        int[] res = getMaxMinDiff(root);
        return res[0];
    }

	/**
	 * for every node root, return int[3]
	 * int[0] is the min difference in subtree
	 * int[1] is the min value in the subtree
	 * int[2] is the max value in the subtree
	 * 
	 * do not just check the near leaves because the nearest value may be far away from root
	 * such as [236,104,701,null,227,null,911]
	 * 
	 * @param root
	 * @return
	 */
    public int[] getMaxMinDiff(TreeNode root) {
        if (root == null) {
            return new int[] {1000000, 1000000, -1};
        }

        int[] left = getMaxMinDiff(root.left), right = getMaxMinDiff(root.right);
        int[] res = new int[3];
        res[0] = Math.min(left[0], right[0]);
        res[1] = Math.min(root.val, left[1]);
        res[2] = Math.max(root.val, right[2]);
        if (root.left != null) {
            res[0] = Math.min(res[0], root.val - left[2]);
        }
        if (root.right != null) {
            res[0] = Math.min(res[0], right[1] - root.val);
        }
        return res;
    }
}
