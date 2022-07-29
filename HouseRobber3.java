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
    	// ���������ں����ڲ���������̫�ཫ���³�ʱ
    	// ��ͨ���Ż��㷨��������⣺����ֵ��Ϊ int[2] ������/��������ǰ�ڵ�����ֵ
    	//                          �Ӷ���������������Ϊ 2����ڵ�/�ҽڵ�����
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
     * ͨ�� results ���������ɽ� Map �Ż�Ϊ Map<TreeNode, Integer>��
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
			// �㷨�ڲ�ֻ��Ϊ��ǰ�ڵ�� ��/�ҽڵ� ��ֵ����Ϊ���²�ڵ�����Ϸ����� getRobVal ����ʱ�Ѵ���
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
