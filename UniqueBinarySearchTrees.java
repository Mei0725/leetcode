package leetcode_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * solutions in this class is as same as UniqueBinarySearchTrees2
 * but ignore the steps to create a tree
 *
 */
public class UniqueBinarySearchTrees {

	public static void main(String[] args) {
		int input = 4;
		int output = -1;
		try {
			output = numTrees(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * this solution also use DP but only sort the result of different number of node to reduce time
	 * 
	 * @param n
	 * @return
	 */
    public static int numTrees(int n) {
    	int[] result = new int[n + 1];
    	result[0] = 1;
    	result[1] = 1;
    	
    	for (int i = 2; i <= n; i++) {
    		int left = 0;
    		int right = i - 1;
    		while (right >= 0) {
    			result[i] += result[left] * result[right];
    			left++;
    			right--;
    		}
    	}
    	return result[n];
    }
    
	/**
	 * solve this problem in DP.
	 * use List<Map<Integer, List<TreeNode>>> to sort the tree in every different counts of node
	 * for example, the list of result.get(i).get(j) means the trees that contains nums[j, j + i + 1]
	 * 
	 * @param n
	 * @return
	 */
    public static int numTreesDP(int n) {
    	List<Map<Integer, Integer>> result = new ArrayList<>();
    	// num is the number of nodes that contain in the tree
    	for (int num = 1; num <= n; num++) {
//    		System.out.println("num: " + num);
    		// key is i of tree[i,j]
    		Map<Integer, Integer> tmpResult = new HashMap<>();
    		// i is the start of trees[i,j]
    		for (int i = 1; i <= n - num + 1; i++) {
//        		System.out.println("i: " + i);
    			int iNode = 0;
    			for (int j = i; j < i + num; j++) {
    				// j is the root of this tree
//    	    		System.out.println("j: " + j);
    				// left and right is the number of different types for the left and right leaves
    				int left = 1;
    				int right = 1;
    				if (j - i - 1 > 0) {
    					left = result.get(j - i - 1).get(i);
    				}
    				if (j < i + num - 1) {
    					right = result.get(num + i - j - 2).get(j + 1);
    				}
    				iNode += left * right;
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
     * when n is every large, this solution may cost every long time(2269ms)
     * 
     * @param n
     * @return
     */
    public static int numTreesBacktracking(int n) {
    	if (n == 0 || n == 1) {
    		return 1;
    	}
    	
    	// sort the nums in the left and right part
    	// since nums is increasing, it can be changed in this simple way
    	int left = 0, right = n - 1;
    	int result = 0;
    	// choose num as the right
    	while (right >= 0) {
    		result += numTreesBacktracking(left) * numTreesBacktracking(right);
    		left++;
    		right--;
    	}
    	return result;
    }
}
