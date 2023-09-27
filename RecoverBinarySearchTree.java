package leetcode_test;

import java.util.HashMap;
import java.util.Map;

public class RecoverBinarySearchTree {
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
//		boolean output = false;
		try {
			recoverTree(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + input.val);
	}

	public static void recoverTree(TreeNode root) {
		if (root == null) {
			return;
		}
		Map<TreeNode, TreeNode[]> nodeBoard = new HashMap<>();
		getBoardNode(root, nodeBoard);
		recoverTree(root, nodeBoard);
	}

	public static boolean recoverTree(TreeNode root, Map<TreeNode, TreeNode[]> nodes) {
		if (root == null) {
			return false;
		}

		TreeNode[] left = getBoardNode(root.left, nodes);
		TreeNode[] right = getBoardNode(root.right, nodes);
		if (left != null && left[1].val > root.val 
				&& right != null && right[0].val < root.val) {
			int tmp = left[1].val;
			left[1].val = right[0].val;
			right[0].val = tmp;
			return true;
		} else if (left != null && left[1].val > root.val) {
			int tmp = root.val;
			root.val = left[1].val;
			left[1].val = tmp;
			return true;
		} else if (right != null && right[0].val < root.val) {
			int tmp = root.val;
			root.val = right[0].val;
			right[0].val = tmp;
			return true;
		} else {
			return recoverTree(root.left, nodes) || recoverTree(root.right, nodes);
		}
	}
	
	public static TreeNode[] getBoardNode(TreeNode root, Map<TreeNode, TreeNode[]> nodes) {
		if (root == null) {
			return null;
		} else if (nodes.containsKey(root)) {
			return nodes.get(root);
		}

		TreeNode[] result = new TreeNode[2];
		TreeNode[] left = getBoardNode(root.left, nodes);
		TreeNode[] right = getBoardNode(root.right, nodes);
		if (left != null && left[0].val < root.val 
				&& (right == null || right[0].val > left[0].val)) {
			result[0] = left[0];
		} else if (right != null && right[0].val < root.val 
				&& (left == null || left[0].val > right[0].val)) {
			result[0] = right[0];
		} else {
			result[0] = root;
		}
		if (left != null && left[1].val > root.val 
				&& (right == null || right[1].val < left[1].val)) {
			result[1] = left[1];
		} else if (right != null && right[1].val > root.val 
				&& (left == null || left[1].val < right[1].val)) {
			result[1] = right[1];
		} else {
			result[1] = root;
		}
		nodes.put(root, result);
		return result;
	}
	
	public static void recoverTree0(TreeNode root) {
		if (root == null) {
			return;
		}
		
		TreeNode[] left = getBoardNode(root.left);
		TreeNode[] right = getBoardNode(root.right);
		if (left[1] != null && left[1].val > root.val 
				&& right[0] != null && right[0].val < root.val) {
			int tmp = left[1].val;
			left[1].val = right[0].val;
			right[0].val = tmp;
		} else if (left[1] != null && left[1].val > root.val) {
			int tmp = root.val;
			root.val = left[1].val;
			left[1].val = tmp;
		} else if (right[0] != null && right[0].val < root.val) {
			int tmp = root.val;
			root.val = right[0].val;
			right[0].val = tmp;
		} else {
			recoverTree(root.left);
			recoverTree(root.right);
		}
	}
	
	public static TreeNode[] getBoardNode(TreeNode root) {
		TreeNode[] result = new TreeNode[2];
		if (root == null) {
			return result;
		}
		
		TreeNode[] left = getBoardNode(root.left);
		TreeNode[] right = getBoardNode(root.right);
		if (left[0] != null && left[0].val < root.val 
				&& (right[0] == null || right[0].val > left[0].val)) {
			result[0] = left[0];
		} else if (right[0] != null && right[0].val < root.val 
				&& (left[0] == null || left[0].val > right[0].val)) {
			result[0] = right[0];
		} else {
			result[0] = root;
		}
		if (left[1] != null && left[1].val > root.val 
				&& (right[1] == null || right[1].val < left[1].val)) {
			result[1] = left[1];
		} else if (right[1] != null && right[1].val > root.val 
				&& (left[1] == null || left[1].val < right[1].val)) {
			result[1] = right[1];
		} else {
			result[1] = root;
		}
		return result;
	}
	
	public static void recoverTreeError(TreeNode root) {
		TreeNode[] result = getMistakeNode(root);
		if (result[0] != null) {
			int tmp = result[0].val;
			result[0].val = root.val;
			root.val = tmp;
		}
	}
	
	public static TreeNode[] getMistakeNode(TreeNode root) {
		TreeNode[] result = new TreeNode[3];
		if (root == null) {
			return result;
		}
		
		TreeNode[] leftNodes = getMistakeNode(root.left);
		TreeNode[] rightNodes = getMistakeNode(root.right);
		if ((leftNodes[2] != null && root.val < leftNodes[2].val) && 
				(rightNodes[1] != null && root.val > rightNodes[1].val)) {
			int tmp = leftNodes[2].val;
			leftNodes[2].val = rightNodes[1].val;
			rightNodes[1].val = tmp;
		} else if (leftNodes[0] != null && rightNodes[0] != null) {
			int tmp = leftNodes[2].val;
			leftNodes[2].val = rightNodes[1].val;
			rightNodes[1].val = tmp;
		} else if (leftNodes[0] != null) {
			// if the left leave is invalid, then the mistake node must be the maxval
			if (root.val > leftNodes[2].val) {
				int tmp = root.left.val;
				root.left.val = leftNodes[0].val;
				leftNodes[0].val = tmp;
			} else {
				result[0] = root.val > leftNodes[2].val ? root : leftNodes[2];
			}
		} else if (rightNodes[0] != null) {
			if (root.val < rightNodes[1].val) {
				int tmp = root.right.val;
				root.right.val = rightNodes[0].val;
				rightNodes[0].val = tmp;
			} else {
				result[0] = root.val < rightNodes[1].val ? root : rightNodes[1];
			}
		} else {
			if (leftNodes[2] != null && root.val < leftNodes[2].val) {
				result[0] = leftNodes[2];
			} else if (rightNodes[1] != null && root.val > rightNodes[1].val) {
				result[0] = rightNodes[1];
			}
		}
		if (leftNodes[1] != null && leftNodes[1].val < root.val && 
				(rightNodes[1] == null || leftNodes[1].val < rightNodes[1].val)) {
			result[1] = leftNodes[1];
		} else if (rightNodes[1] != null && rightNodes[1].val < root.val &&
				(leftNodes[1] == null || rightNodes[1].val < leftNodes[1].val)) {
			result[1] = rightNodes[1];
		} else {
			result[1] = root;
		}
		if (leftNodes[2] != null && leftNodes[2].val > root.val && 
				(rightNodes[2] == null || leftNodes[2].val > rightNodes[2].val)) {
			result[2] = leftNodes[2];
		} else if (rightNodes[2] != null && rightNodes[2].val > root.val &&
				(leftNodes[2] == null || rightNodes[2].val > leftNodes[2].val)) {
			result[2] = rightNodes[2];
		} else {
			result[2] = root;
		}
		System.out.println("root: " + root.val);
		System.out.println("result: " + (result[0] == null ? null : result[0].val) + ",min: " + result[1].val + ",max: " + result[2].val);
		return result;
	}

}
