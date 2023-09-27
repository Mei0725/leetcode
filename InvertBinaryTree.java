package leetcode_test;

public class InvertBinaryTree {

	/**
	 * invert the leaves of root from top to bottom.
	 * 
	 * @param root
	 * @return
	 */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
