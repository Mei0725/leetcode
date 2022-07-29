package leetcode_test;

import java.util.HashMap;
import java.util.Map;

public class HouseRobber3 {

	public static void main(String[] args) {
		TreeNode right = new TreeNode(2);
		TreeNode input = new TreeNode(1, null, right);
//		String input = "  -42";
//		String input = "words and 987";
		int output = -1;
		try {
			output = rob(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	

	 public static class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode() {}
	      TreeNode(int val) { this.val = val; }
	      TreeNode(int val, TreeNode left, TreeNode right) {
	          this.val = val;
	          this.left = left;
	          this.right = right;
	      }
	  }
	
	/**
	 * 
	 * 
	 * @param nums
	 * @return
	 */
    public static int rob(TreeNode root) {
    	Map<Integer, Integer> results = new HashMap<>();
    	// 该做法由于函数内部迭代数量太多将导致超时
    	// 可通过优化算法解决该问题：返回值变为 int[2] ：包含/不包含当前节点的最大值
    	//                          从而将迭代次数缩减为 2（左节点/右节点结果）
    	/*if (root == null) {
    		return 0;
    	} else if (root.left == null && root.right == null) {
    		return root.val;
    	}
    	
    	int rootDe2Val = 0;
    	if (root.left != null) {
    		rootDe2Val += rob(root.left.left) + rob(root.left.right);
    	}
    	if (root.right != null) {
    		rootDe2Val += rob(root.right.left) + rob(root.right.right);
    	}
    	return Math.max(rootDe2Val + root.val, rob(root.left) + rob(root.right));*/
    	return getRobVal(root, 0, results);
    }
    
    /**
     * 通过 results 储存结果【可将 Map 优化为 Map<TreeNode, Integer>】
     * 
     * @param root 
     * @param index 
     * @param results
     * @return
     */
    public static int getRobVal(TreeNode root, int index, Map<Integer, Integer> results) {
    	if (results.get(index) != null) {
    		return results.get(index);
    	} else if (root == null) {
    		results.put(index, 0);
    		return 0;
    	} else if (root.left == null && root.right == null) {
    		results.put(index, root.val);
    		return root.val;
    	}

    	int rootDe2Val = 0;
		int leftIndex = 2 * index + 1;
		int rightIndex = 2 * index + 2;
		int leftleftIndex = 2 * leftIndex + 1;
		int leftrightIndex = 2 * leftIndex + 2;
		int rightleftIndex = 2 * rightIndex + 1;
		int rightrightIndex = 2 * rightIndex + 2;
		results.put(leftIndex, getRobVal(root.left, leftIndex, results));
		results.put(rightIndex, getRobVal(root.right, rightIndex, results));
		if (root.left != null) {
			// 算法内部只需为当前节点的 左/右节点 赋值，因为再下层节点可在上方调用 getRobVal 方法时已处理
//			results.put(leftleftIndex, getRobVal(root.left.left, leftleftIndex, results));
//			results.put(leftrightIndex, getRobVal(root.left.right, leftrightIndex, results));
			rootDe2Val += results.get(leftleftIndex) + results.get(leftrightIndex);
		}
		if (root.right != null) {
//			results.put(rightleftIndex, getRobVal(root.right.left, rightleftIndex, results));
//			results.put(rightrightIndex, getRobVal(root.right.right, rightrightIndex, results));
			rootDe2Val += results.get(rightleftIndex) + results.get(rightrightIndex);
		}
    	return Math.max(root.val + rootDe2Val, results.get(leftIndex) + results.get(rightIndex));
    }

}
