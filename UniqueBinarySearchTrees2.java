package leetcode_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueBinarySearchTrees2 {
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
		int input = 4;
		List<TreeNode> output = null;
		try {
			output = generateTrees(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output.size());
	}

	/**
	 * solve this problem in DP.
	 * use List<Map<Integer, List<TreeNode>>> to sort the tree in every different counts of node
	 * for example, the list of result.get(i).get(j) means the trees that contains nums[j, j + i + 1]
	 * 
	 * @param n
	 * @return
	 */
    public static List<TreeNode> generateTrees(int n) {
    	List<Map<Integer, List<TreeNode>>> result = new ArrayList<>();
    	// num is the number of nodes that contain in the tree
    	for (int num = 1; num <= n; num++) {
//    		System.out.println("num: " + num);
    		// key is i of tree[i,j]
    		Map<Integer, List<TreeNode>> tmpResult = new HashMap<>();
    		// i is the start of trees[i,j]
    		for (int i = 1; i <= n - num + 1; i++) {
//        		System.out.println("i: " + i);
    			List<TreeNode> iNode = new ArrayList<>();
    			for (int j = i; j < i + num; j++) {
    				// j is the root of this tree
//    	    		System.out.println("j: " + j);
    				List<TreeNode> left = new ArrayList<>();
    				List<TreeNode> right = new ArrayList<>();
    				if (j - i - 1 > 0) {
    					left = result.get(j - i - 1).get(i);
    				} else {
    					left.add(null);
    				}
    				if (j < i + num - 1) {
    					right = result.get(num + i - j - 2).get(j + 1);
    				} else {
    					right.add(null);
    				}
    	    		for (TreeNode leftNode : left) {
    	    			for (TreeNode rightNode : right) {
    	    				iNode.add(new TreeNode(j, leftNode, rightNode));
    	    			}
    	    		}
    			}
    			tmpResult.put(i, iNode);
    		}
    		result.add(tmpResult);
    	}
    	
    	return result.get(n - 1).get(1);
    }

    /**
     * solve this problem by backtracking.
     * for every list<int>, choose one num as the root, and then separate the rest nums into 2 part(left & right)
     * handle the left and right parts in the same way until the list's size is 0 or 1
     * 
     * @param n
     * @return
     */
    public static List<TreeNode> generateTreesBacktracking(int n) {
    	// translate the input into list<int>
    	List<Integer> nums = new ArrayList<>();
    	for (int i = 1; i <= n; i++) {
    		nums.add(i);
    	}
    	return generateTrees(nums);
    }
    
    public static List<TreeNode> generateTrees(List<Integer> nums) {
    	List<TreeNode> result = new ArrayList<>();
    	if (nums.isEmpty()) {
    		// let result do not become empty so that it will not early break in the following cycle
    		result.add(null);
    		return result;
    	} else if (nums.size() == 1) {
    		// this case can also be handled in the following cycle
    		result.add(new TreeNode(nums.get(0)));
    		return result;
    	}
    	
    	// sort the nums in the left and right part
    	// since nums is increasing, it can be changed in this simple way
    	List<Integer> left = new ArrayList<>();
    	List<Integer> right = new ArrayList<>(nums);
    	// choose num as the right
    	for (int num : nums) {
    		right.remove(0);
    		List<TreeNode> leftNodes = generateTrees(left);
    		List<TreeNode> rightNodes = generateTrees(right);
    		for (TreeNode leftNode : leftNodes) {
    			for (TreeNode rightNode : rightNodes) {
    				result.add(new TreeNode(num, leftNode, rightNode));
    			}
    		}
    		left.add(num);
    	}
    	return result;
    }
}
