package leetcode_test;

public class KthSmallestElementInaBST {

	/**
	 * check root in order left->root->right, and return the result(minus) or left k,
	 * because root.val>=0, and if there is a result, there is no need to pass k,
	 * so it can return result by -root.val to save space.
	 * 
	 * @param root
	 * @param k
	 * @return
	 */
    public int kthSmallest(TreeNode root, int k) {
        return -kthSmallestItem(root, k);
    }

    /**
     * find out the kth item in root.
     * 
     * if there is a result found in this subtree, return -result;
     * otherwise, return the left k.
     * 
     * @param root
     * @param k
     * @return
     */
    private int kthSmallestItem(TreeNode root, int k) {
        if (root == null || k <= 0) {
        	// if root is null, then this node will not influence result
        	// it k<=0, then k is the final result
            return k;
        } else if (root.left == null && root.right == null) {
        	// if means there is the only node.
        	// for node like this, it will be the result or will help to reduce k.
            if (k == 1) {
                return -root.val;
            } else {
                return k - 1;
            }
        }
        
        k = kthSmallestItem(root.left, k); 
        if (k == 1) {
        	// check if root is the result
            return -root.val;
        } else if (k <= 0) {
        	// check if result is in root.left
            return k;
        }
        // no matter result is in root.right or just use root.right to reduce k, 
        // there is no changing operation of its return
        return kthSmallestItem(root.right, k - 1);
    }
}
