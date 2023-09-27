package leetcode_test;

public class ValidateBinarySearchTree {
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

	public static void main(String[] args) {
		TreeNode left = new TreeNode(1);
		TreeNode right = new TreeNode(3);
		TreeNode input = new TreeNode(2, left, right);
		boolean output = false;
		try {
			output = isValidBST(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
	public static boolean isValidBST(TreeNode root) {
		if (root == null) {
			return true;
		}

		int[] result = getRangeOfBST(root);
//		System.out.println("result[0]: " + result[0] + ",result[1]: " + result[1] + ",result[2]: " + result[2]);
		return result[0] == 1;
	}
	
	/**
	 * use int[3] to sort result: 
	 * int[0] means if this treeNode is valid(1 means valid)
	 * int[1] means the min value of tree which take root as its root
	 * int[2] means the max value of tree which take root as its root
	 * 
	 * @param root
	 * @return
	 */
	public static int[] getRangeOfBST(TreeNode root) {
		// generally, it is impossible to go into this if
		if (root == null) {
			return new int[]{0, 0, 0};
		}
		
		int min = root.val, max = root.val;
		if (root.left != null) {
			int[] left = getRangeOfBST(root.left);
//			System.out.println("left[0]: " + left[0] + ",left[1]: " + left[1] + ",left[2]: " + left[2]);
			// all nodes in the left should have value < root
			// which means the max value of root.left must < root
			if (left[0] == 0 || min <= left[2]) {
				return new int[]{0, min, max};
			}
			min = left[1];
		}
		if (root.right != null) {
			int[] right = getRangeOfBST(root.right);
//			System.out.println("right[0]: " + right[0] + ",right[1]: " + right[1] + ",right[2]: " + right[2]);
			// all nodes in the right should have value > root
			// which means the min value of root.left must > root
			if (right[0] == 0 || max >= right[1]) {
				return new int[]{0, min, max};
			}
			max = right[2];
		}
		return new int[]{1, min, max};
	}
}
