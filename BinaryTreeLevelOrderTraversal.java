package leetcode_test;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeLevelOrderTraversal {
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
			levelOrder(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + input.val);
	}

	/**
	 * use i to mark which level it is now
	 * 
	 * @param root
	 * @return
	 */
	public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        everyLevelOrder(root, result, 0);
        return result;
    }

	public static void everyLevelOrder(TreeNode root, List<List<Integer>> result, int i) {
        if (root == null) {
            return;
        }
        
        List<Integer> items;
        if (result.size() > i) {
            items = result.get(i);
        } else {
            items = new ArrayList<>();
            result.add(items);
        }
        items.add(root.val);
        everyLevelOrder(root.left, result, i + 1);
        everyLevelOrder(root.right, result, i + 1);
    }

}
