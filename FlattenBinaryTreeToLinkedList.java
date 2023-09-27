package leetcode_test;

public class FlattenBinaryTreeToLinkedList {

	/**
	 * rebuild every subtree by linking in order: root->root.left->root.right
	 * 
	 * @param root
	 */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flattenSubTree(root);
    }

    /**
     * return the start and end of every subtree, they can both be root
     * 
     * @param root
     * @return
     */
    public TreeNode[] flattenSubTree(TreeNode root) {
        TreeNode start = root, end = root;
        // store left and right in advance to avoid that it may change in the following operation
        TreeNode left = root.left, right = root.right;
        if (left != null) {
            TreeNode[] tmp = flattenSubTree(left);
            end.right = tmp[0];
            end = tmp[1];
            // set root.left is null if it is not null before
            root.left = null;
        }
        if (right != null) {
            TreeNode[] tmp = flattenSubTree(right);
            end.right = tmp[0];
            end = tmp[1];
        }
        return new TreeNode[] {start, end};
    }
}
