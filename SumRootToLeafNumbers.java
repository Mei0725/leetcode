package leetcode_test;

public class SumRootToLeafNumbers {

	/**
	 * solve by deep first search.
	 * 
	 * add the current path value into every root, and calculate value*10+node.val as the path value for its leaves.
	 * 
	 * @param root
	 * @return
	 */
    public int sumNumbers(TreeNode root) {
        return sumNumbers(root, 0);
    }

    public int sumNumbers(TreeNode root, int input) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return input * 10 + root.val;
        }

        input = input * 10 + root.val;
        return sumNumbers(root.left, input) + sumNumbers(root.right, input); 
    }
}
