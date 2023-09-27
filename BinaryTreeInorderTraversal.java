package leetcode_test;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeInorderTraversal {
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
		TreeNode input = new TreeNode();
		List<Integer> output = null;
		try {
			output = inorderTraversal(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
	public static List<Integer> result = new ArrayList<>();

    public static List<Integer> inorderTraversal(TreeNode root) {
    	if (root == null) {
    		return result;
    	}
    	
		inorderTraversal(root.left);
    	result.add(root.val);
		inorderTraversal(root.right);
        return result;
    }
}
