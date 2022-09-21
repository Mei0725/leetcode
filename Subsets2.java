package leetcode_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets2 {

	public static void main(String[] args) {
		int[] input = {1,2,2};
//		int[] input = {0};
		List<List<Integer>> output = null;
		try {
			output = subsetsWithDup(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
	/**
	 * use backtracking to deal with this problem
	 * 
	 * @param nums
	 * @return
	 */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
    	List<List<Integer>> result = new ArrayList<>();
    	if (nums.length == 0) {
    		return result;
    	}
    	Arrays.sort(nums);
    	return subsets(nums, result);
    }
    
    public static List<List<Integer>> subsets(int[] nums, List<List<Integer>> preResult) {
    	if (nums.length == 0) {
    		return preResult;
    	}
    	List<List<Integer>> result = new ArrayList<>();
    	if (preResult.size() == 0) {
    		preResult.add(new ArrayList<>());
    	}
    	
    	// when there are same nums in arrays, for any n same num m, 
    	// it can be expressed as [], [m], [m, m]... ,the last one is an array contains n m
    	int nextId = 1;
    	while (nextId < nums.length && nums[0] == nums[nextId]) {
    		nextId++;
    	}
    	for (List<Integer> tmp : preResult) {
    		for (int i = 0; i <= nextId; i++) {
        		List<Integer> tmpAdd = new ArrayList<>(tmp);
    			int add = i;
    			while (add > 0) {
    				tmpAdd.add(nums[0]);
    				add--;
    			}
    			result.add(tmpAdd);
    		}
    	}
    	return subsets(Arrays.copyOfRange(nums, nextId, nums.length), result);
    }
}
